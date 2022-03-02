package cn.bugstack.springframework.core;

import cn.bugstack.springframework.util.ClassUtils;
import cn.hutool.core.lang.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author zhangdd on 2022/2/27
 */
public class MethodParameter {

    private volatile Class<?> containingClass;

    private final Executable executable;

    private final int parameterIndex;

    private int nestingLevel = 1;

    private volatile Type genericParameterType;

    private volatile Class<?> parameterType;

    Map<Integer, Integer> typeIndexesPerLevel;

    public MethodParameter(Method method, int parameterIndex) {
        this(method, parameterIndex, 1);
    }

    public MethodParameter(Constructor<?> constructor, int parameterIndex) {
        this(constructor, parameterIndex, 1);
    }

    public MethodParameter(Constructor<?> constructor, int parameterIndex, int nestingLevel) {
        Assert.notNull(constructor, "Constructor must not be null");
        this.executable = constructor;
        this.parameterIndex = validateIndex(constructor, parameterIndex);
        this.nestingLevel = nestingLevel;
    }

    public MethodParameter(Method method, int parameterIndex, int nestingLevel) {
        Assert.notNull(method, "Method must not be null");
        this.executable = method;
        this.parameterIndex = validateIndex(method, parameterIndex);
        this.nestingLevel = nestingLevel;
    }

    void setContainingClass(Class<?> containingClass) {
        this.containingClass = containingClass;
    }

    private static int validateIndex(Executable executable, int parameterIndex) {
        int count = executable.getParameterCount();
        if (parameterIndex<-1 || parameterIndex>=count){
            throw new IllegalArgumentException("Parameter index needs to be between -1 and " + (count - 1));
        }
        return parameterIndex;
    }

    public Class<?> getContainingClass() {
        Class<?> containingClass = this.containingClass;
        return (containingClass != null ? containingClass : getDeclaringClass());
    }

    public Class<?> getDeclaringClass() {
        return this.executable.getDeclaringClass();
    }

    public Method getMethod() {
        return (this.executable instanceof Method ? (Method) this.executable : null);
    }

    public Executable getExecutable() {
        return this.executable;
    }

    public int getParameterIndex() {
        return this.parameterIndex;
    }

    public int getNestingLevel() {
        return this.nestingLevel;
    }

    public Type getGenericParameterType() {
        Type paramType = this.genericParameterType;
        if (paramType == null) {
            if (this.parameterIndex < 0) {
                Method method = getMethod();
                paramType = (method != null ? method.getGenericReturnType() : void.class);
            }
            else {
                Type[] genericParameterTypes = this.executable.getGenericParameterTypes();
                int index = this.parameterIndex;
                if (this.executable instanceof Constructor &&
                        ClassUtils.isInnerClass(this.executable.getDeclaringClass()) &&
                        genericParameterTypes.length == this.executable.getParameterCount() - 1) {
                    // Bug in javac: type array excludes enclosing instance parameter
                    // for inner classes with at least one generic constructor parameter,
                    // so access it with the actual parameter index lowered by 1
                    index = this.parameterIndex - 1;
                }
                paramType = (index >= 0 && index < genericParameterTypes.length ?
                        genericParameterTypes[index] : getParameterType());
            }
            this.genericParameterType = paramType;
        }
        return paramType;
    }

    public Class<?> getParameterType() {
        Class<?> paramType = this.parameterType;
        if (paramType == null) {
            if (this.parameterIndex < 0) {
                Method method = getMethod();
                paramType = (method != null ? method.getReturnType() : void.class);
            }
            else {
                paramType = this.executable.getParameterTypes()[this.parameterIndex];
            }
            this.parameterType = paramType;
        }
        return paramType;
    }

}
