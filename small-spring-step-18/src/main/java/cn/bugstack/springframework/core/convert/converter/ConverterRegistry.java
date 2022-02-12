package cn.bugstack.springframework.core.convert.converter;

/**
 * For registering converters with a type conversion system.
 *
 * 类型转换注册接口
 *
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public interface ConverterRegistry {

    /**
     * Add a plain converter to this registry.
     * The convertible source/target type pair is derived from the Converter's parameterized types.
     * @throws IllegalArgumentException if the parameterized types could not be resolved
     */
    void addConverter(Converter<?, ?> converter);

    /**
     * Add a generic converter to this registry.
     */
    void addConverter(GenericConverter converter);

    /**
     * Add a ranged converter factory to this registry.
     * The convertible source/target type pair is derived from the ConverterFactory's parameterized types.
     * @throws IllegalArgumentException if the parameterized types could not be resolved
     */
    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

}
