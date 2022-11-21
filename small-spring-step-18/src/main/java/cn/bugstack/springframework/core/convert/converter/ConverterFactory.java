package cn.bugstack.springframework.core.convert.converter;

/**
 * A factory for "ranged" converters that can convert objects from S to subtypes of R.
 *
 * 类型转换工厂
 *
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface ConverterFactory<S, R>{

    /**
     * Get the converter to convert from S to target type T, where T is also an instance of R.
     * @param <T> the target type
     * @param targetType the target type to convert to
     * @return a converter from S to T
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
