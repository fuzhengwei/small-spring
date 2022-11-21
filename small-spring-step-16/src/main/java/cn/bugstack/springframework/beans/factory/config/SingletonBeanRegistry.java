package cn.bugstack.springframework.beans.factory.config;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * <p>
 * 单例注册表
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

}
                                                