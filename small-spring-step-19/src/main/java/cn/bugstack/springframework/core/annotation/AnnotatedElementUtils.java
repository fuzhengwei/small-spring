package cn.bugstack.springframework.core.annotation;

import cn.bugstack.springframework.core.BridgeMethodResolver;
import cn.hutool.core.collection.CollUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author zhangdd on 2022/2/26
 */
public class AnnotatedElementUtils {

    private static final Annotation[] EMPTY_ANNOTATION_ARRAY = new Annotation[0];


    public static AnnotationAttributes findMergedAnnotationAttributes(AnnotatedElement element,
                                                                      Class<? extends Annotation> annotationType, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
        return searchWithFindSemantics(element, annotationType, null, new MergedAnnotationAttributesProcessor(classValuesAsString, nestedAnnotationsAsMap));
    }

    private static <T> T searchWithFindSemantics(AnnotatedElement element,
                                                 Class<? extends Annotation> annotationType,
                                                 String annotationName, Processor<T> processor) {
        return searchWithFindSemantics(element, (null != annotationType ? Collections.singleton(annotationType) : Collections.emptySet()),
                annotationName, null, processor);
    }

    private static <T> T searchWithFindSemantics(AnnotatedElement element,
                                                 Set<Class<? extends Annotation>> annotationType,
                                                 String annotationName,
                                                 Class<? extends Annotation> containerType,
                                                 Processor<T> processor) {
        if (containerType != null && !processor.aggregates()) {
            throw new IllegalArgumentException("Search for repeatable annotations must supply an aggregating Processor");
        }

        return searchWithFindSemantics(element, annotationType, annotationName, containerType, processor, new HashSet<>(), 0);

    }

    private static <T> T searchWithFindSemantics(AnnotatedElement element,
                                                 Set<Class<? extends Annotation>> annotationTypes, String annotationName,
                                                 Class<? extends Annotation> containerType, Processor<T> processor,
                                                 Set<AnnotatedElement> visited, int metaDepth) {
        if (visited.add(element)) {
            try {
                // Locally declared annotations (ignoring @Inherited)
                Annotation[] annotations = AnnotationUtils.getDeclaredAnnotations(element);
                if (annotations.length > 0) {
                    List<T> aggregatedResults = (processor.aggregates() ? new ArrayList<>() : null);

                    // Search in local annotations
                    for (Annotation annotation : annotations) {
                        Class<? extends Annotation> currentAnnotationType = annotation.annotationType();
                        if (!AnnotationUtils.isInJavaLangAnnotationPackage(currentAnnotationType)) {
                            if (annotationTypes.contains(currentAnnotationType) ||
                                    currentAnnotationType.getName().equals(annotationName) ||
                                    processor.alwaysProcesses()) {
                                T result = processor.process(element, annotation, metaDepth);
                                if (result != null) {
                                    if (aggregatedResults != null && metaDepth == 0) {
                                        aggregatedResults.add(result);
                                    } else {
                                        return result;
                                    }
                                }
                            }
                            // Repeatable annotations in container?
                            else if (currentAnnotationType == containerType) {
                                for (Annotation contained : getRawAnnotationsFromContainer(element, annotation)) {
                                    T result = processor.process(element, contained, metaDepth);
                                    if (aggregatedResults != null && result != null) {
                                        // No need to post-process since repeatable annotations within a
                                        // container cannot be composed annotations.
                                        aggregatedResults.add(result);
                                    }
                                }
                            }
                        }
                    }

                    // Recursively search in meta-annotations
                    for (Annotation annotation : annotations) {
                        Class<? extends Annotation> currentAnnotationType = annotation.annotationType();
                        if (!AnnotationUtils.hasPlainJavaAnnotationsOnly(currentAnnotationType)) {
                            T result = searchWithFindSemantics(currentAnnotationType, annotationTypes, annotationName,
                                    containerType, processor, visited, metaDepth + 1);
                            if (result != null) {
                                processor.postProcess(currentAnnotationType, annotation, result);
                                if (aggregatedResults != null && metaDepth == 0) {
                                    aggregatedResults.add(result);
                                } else {
                                    return result;
                                }
                            }
                        }
                    }


                    if (!CollUtil.isEmpty(aggregatedResults)) {
                        // Prepend to support top-down ordering within class hierarchies
                        processor.getAggregatedResults().addAll(0, aggregatedResults);
                    }
                }

                if (element instanceof Method) {
                    Method method = (Method) element;
                    T result;

                    // Search on possibly bridged method
                    Method resolvedMethod = BridgeMethodResolver.findBridgedMethod(method);
                    if (resolvedMethod != method) {
                        result = searchWithFindSemantics(resolvedMethod, annotationTypes, annotationName,
                                containerType, processor, visited, metaDepth);
                        if (result != null) {
                            return result;
                        }
                    }

                    // Search on methods in interfaces declared locally
                    Class<?>[] ifcs = method.getDeclaringClass().getInterfaces();
                    if (ifcs.length > 0) {
                        result = searchOnInterfaces(method, annotationTypes, annotationName,
                                containerType, processor, visited, metaDepth, ifcs);
                        if (result != null) {
                            return result;
                        }
                    }

                    // Search on methods in class hierarchy and interface hierarchy
                    Class<?> clazz = method.getDeclaringClass();
                    while (true) {
                        clazz = clazz.getSuperclass();
                        if (clazz == null || clazz == Object.class) {
                            break;
                        }
                        Set<Method> annotatedMethods = AnnotationUtils.getAnnotatedMethodsInBaseType(clazz);
                        if (!annotatedMethods.isEmpty()) {
                            for (Method annotatedMethod : annotatedMethods) {
                                if (AnnotationUtils.isOverride(method, annotatedMethod)) {
                                    Method resolvedSuperMethod = BridgeMethodResolver.findBridgedMethod(annotatedMethod);
                                    result = searchWithFindSemantics(resolvedSuperMethod, annotationTypes,
                                            annotationName, containerType, processor, visited, metaDepth);
                                    if (result != null) {
                                        return result;
                                    }
                                }
                            }
                        }
                        // Search on interfaces declared on superclass
                        result = searchOnInterfaces(method, annotationTypes, annotationName,
                                containerType, processor, visited, metaDepth, clazz.getInterfaces());
                        if (result != null) {
                            return result;
                        }
                    }
                } else if (element instanceof Class) {
                    Class<?> clazz = (Class<?>) element;
                    if (!Annotation.class.isAssignableFrom(clazz)) {
                        // Search on interfaces
                        for (Class<?> ifc : clazz.getInterfaces()) {
                            T result = searchWithFindSemantics(ifc, annotationTypes, annotationName,
                                    containerType, processor, visited, metaDepth);
                            if (result != null) {
                                return result;
                            }
                        }
                        // Search on superclass
                        Class<?> superclass = clazz.getSuperclass();
                        if (superclass != null && superclass != Object.class) {
                            T result = searchWithFindSemantics(superclass, annotationTypes, annotationName,
                                    containerType, processor, visited, metaDepth);
                            if (result != null) {
                                return result;
                            }
                        }
                    }
                }
            } catch (Throwable ex) {
                AnnotationUtils.handleIntrospectionFailure(element, ex);
            }
        }
        return null;
    }

    private static <T> T searchOnInterfaces(Method method, Set<Class<? extends Annotation>> annotationTypes,
                                            String annotationName, Class<? extends Annotation> containerType,
                                            Processor<T> processor, Set<AnnotatedElement> visited, int metaDepth, Class<?>[] ifcs) {

        for (Class<?> ifc : ifcs) {
            Set<Method> annotatedMethods = AnnotationUtils.getAnnotatedMethodsInBaseType(ifc);
            if (!annotatedMethods.isEmpty()) {
                for (Method annotatedMethod : annotatedMethods) {
                    if (AnnotationUtils.isOverride(method, annotatedMethod)) {
                        T result = searchWithFindSemantics(annotatedMethod, annotationTypes, annotationName,
                                containerType, processor, visited, metaDepth);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }

        return null;
    }

    private static <A extends Annotation> A[] getRawAnnotationsFromContainer(AnnotatedElement element, Annotation container) {

        try {
            A[] value = (A[]) AnnotationUtils.getValue(container);
            if (value != null) {
                return value;
            }
        } catch (Throwable ex) {
            AnnotationUtils.handleIntrospectionFailure(element, ex);
        }
        // Unable to read value from repeating annotation container -> ignore it.
        return (A[]) EMPTY_ANNOTATION_ARRAY;
    }


    public static AnnotatedElement forAnnotations(final Annotation... annotations) {


        return new AnnotatedElement() {
            @Override
            public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == annotationClass) {
                        return (T) annotation;
                    }
                }
                return null;
            }

            @Override
            public Annotation[] getAnnotations() {
                return annotations;
            }

            @Override
            public Annotation[] getDeclaredAnnotations() {
                return annotations;
            }
        };
    }

    private static class MergedAnnotationAttributesProcessor implements Processor<AnnotationAttributes> {

        private final boolean classValuesAsString;

        private final boolean nestedAnnotationsAsMap;

        private final boolean aggregates;

        private final List<AnnotationAttributes> aggregatedResults;

        MergedAnnotationAttributesProcessor() {
            this(false, false, false);
        }

        MergedAnnotationAttributesProcessor(boolean classValuesAsString, boolean nestedAnnotationsAsMap) {
            this(classValuesAsString, nestedAnnotationsAsMap, false);
        }

        MergedAnnotationAttributesProcessor(boolean classValuesAsString, boolean nestedAnnotationsAsMap,
                                            boolean aggregates) {

            this.classValuesAsString = classValuesAsString;
            this.nestedAnnotationsAsMap = nestedAnnotationsAsMap;
            this.aggregates = aggregates;
            this.aggregatedResults = (aggregates ? new ArrayList<>() : Collections.emptyList());
        }

        @Override
        public boolean alwaysProcesses() {
            return false;
        }

        @Override
        public boolean aggregates() {
            return this.aggregates;
        }

        @Override
        public List<AnnotationAttributes> getAggregatedResults() {
            return this.aggregatedResults;
        }

        @Override
        public AnnotationAttributes process(AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
            return AnnotationUtils.retrieveAnnotationAttributes(annotatedElement, annotation,
                    this.classValuesAsString, this.nestedAnnotationsAsMap);
        }

        @Override
        public void postProcess(AnnotatedElement element, Annotation annotation, AnnotationAttributes attributes) {
            annotation = AnnotationUtils.synthesizeAnnotation(annotation, element);
            Class<? extends Annotation> targetAnnotationType = attributes.annotationType();

            // Track which attribute values have already been replaced so that we can short
            // circuit the search algorithms.
            Set<String> valuesAlreadyReplaced = new HashSet<>();

            for (Method attributeMethod : AnnotationUtils.getAttributeMethods(annotation.annotationType())) {
                String attributeName = attributeMethod.getName();
                String attributeOverrideName = AnnotationUtils.getAttributeOverrideName(attributeMethod, targetAnnotationType);

                // Explicit annotation attribute override declared via @AliasFor
                if (attributeOverrideName != null) {
                    if (valuesAlreadyReplaced.contains(attributeOverrideName)) {
                        continue;
                    }

                    List<String> targetAttributeNames = new ArrayList<>();
                    targetAttributeNames.add(attributeOverrideName);
                    valuesAlreadyReplaced.add(attributeOverrideName);

                    // Ensure all aliased attributes in the target annotation are overridden. (SPR-14069)
                    List<String> aliases = AnnotationUtils.getAttributeAliasMap(targetAnnotationType).get(attributeOverrideName);
                    if (aliases != null) {
                        for (String alias : aliases) {
                            if (!valuesAlreadyReplaced.contains(alias)) {
                                targetAttributeNames.add(alias);
                                valuesAlreadyReplaced.add(alias);
                            }
                        }
                    }

                    overrideAttributes(element, annotation, attributes, attributeName, targetAttributeNames);
                }
                // Implicit annotation attribute override based on convention
                else if (!AnnotationUtils.VALUE.equals(attributeName) && attributes.containsKey(attributeName)) {
                    overrideAttribute(element, annotation, attributes, attributeName, attributeName);
                }
            }
        }

        private void overrideAttributes(AnnotatedElement element, Annotation annotation,
                                        AnnotationAttributes attributes, String sourceAttributeName, List<String> targetAttributeNames) {

            Object adaptedValue = getAdaptedValue(element, annotation, sourceAttributeName);

            for (String targetAttributeName : targetAttributeNames) {
                attributes.put(targetAttributeName, adaptedValue);
            }
        }

        private void overrideAttribute(AnnotatedElement element, Annotation annotation,
                                       AnnotationAttributes attributes, String sourceAttributeName, String targetAttributeName) {

            attributes.put(targetAttributeName, getAdaptedValue(element, annotation, sourceAttributeName));
        }


        private Object getAdaptedValue(
                AnnotatedElement element, Annotation annotation, String sourceAttributeName) {

            Object value = AnnotationUtils.getValue(annotation, sourceAttributeName);
            return AnnotationUtils.adaptValue(element, value, this.classValuesAsString, this.nestedAnnotationsAsMap);
        }
    }

    private interface Processor<T> {

        T process(AnnotatedElement annotatedElement, Annotation annotation, int metaDepth);

        void postProcess(AnnotatedElement annotatedElement, Annotation annotation, T result);

        boolean alwaysProcesses();

        boolean aggregates();

        List<T> getAggregatedResults();
    }
}
