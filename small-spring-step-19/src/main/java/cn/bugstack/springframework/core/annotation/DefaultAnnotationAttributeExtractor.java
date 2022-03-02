package cn.bugstack.springframework.core.annotation;


import cn.bugstack.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author zhangdd on 2022/2/27
 */
class DefaultAnnotationAttributeExtractor extends AbstractAliasAwareAnnotationAttributeExtractor<Annotation> {

    /**
     * Construct a new {@code DefaultAnnotationAttributeExtractor}.
     * @param annotation the annotation to synthesize; never {@code null}
     * @param annotatedElement the element that is annotated with the supplied
     * annotation; may be {@code null} if unknown
     */
    DefaultAnnotationAttributeExtractor(Annotation annotation, Object annotatedElement) {
        super(annotation.annotationType(), annotatedElement, annotation);
    }


    @Override
    
    protected Object getRawAttributeValue(Method attributeMethod) {
        ReflectionUtils.makeAccessible(attributeMethod);
        return ReflectionUtils.invokeMethod(attributeMethod, getSource());
    }

    @Override
    
    protected Object getRawAttributeValue(String attributeName) {
        Method attributeMethod = ReflectionUtils.findMethod(getAnnotationType(), attributeName);
        return (attributeMethod != null ? getRawAttributeValue(attributeMethod) : null);
    }

}
