package cn.bugstack.springframework.test.converter;

import cn.bugstack.springframework.core.convert.converter.Converter;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }

}
