package cn.bugstack.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
