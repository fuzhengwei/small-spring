package cn.bugstack.springframework.context;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 *
 * 实现此接口，既能感知到所属的 ApplicationContext
 *
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
    