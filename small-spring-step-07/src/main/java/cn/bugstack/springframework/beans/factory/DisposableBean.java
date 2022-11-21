package cn.bugstack.springframework.beans.factory;

/**
 * Interface to be implemented by beans that want to release resources
 * on destruction. A BeanFactory is supposed to invoke the destroy
 * method if it disposes a cached singleton. An application context
 * is supposed to dispose all of its singletons on close.
 *
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 来自于对开源项目的学习；
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
