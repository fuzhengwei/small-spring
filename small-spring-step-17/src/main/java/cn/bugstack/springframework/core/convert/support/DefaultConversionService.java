package cn.bugstack.springframework.core.convert.support;

import cn.bugstack.springframework.core.convert.converter.ConverterRegistry;

/**
 * A specialization of {@link GenericConversionService} configured by default
 * with converters appropriate for most environments.
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}
