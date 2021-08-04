package cn.bugstack.springframework.util;

/**
 * Simple strategy interface for resolving a String value.
 * Used by {@link cn.bugstack.springframework.beans.factory.config.ConfigurableBeanFactory}.
 * <p>
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public interface StringValueResolver {

    String resolveStringValue(String strVal);

}
