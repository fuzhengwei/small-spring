package cn.bugstack.springframework.tx.transaction.interceptor;


import cn.bugstack.springframework.aop.support.StaticMethodMatcherPointcut;
import cn.bugstack.springframework.tx.transaction.PlatformTransactionManager;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author zhangdd on 2022/2/27
 */
public abstract class TransactionAttributeSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

    @Override
    public boolean matches(Method method, Class<?> clazz) {
        if (PlatformTransactionManager.class.isAssignableFrom(clazz)) {
            return false;
        }

        TransactionAttributeSource tas = getTransactionAttributeSource();

        return null == tas || tas.getTransactionAttribute(method, clazz) != null;
    }


    //---------------------------------------------------------------------
    // Abstract methods to be implemented by subclasses start
    //---------------------------------------------------------------------

    protected abstract TransactionAttributeSource getTransactionAttributeSource();

    //---------------------------------------------------------------------
    // Abstract methods to be implemented by subclasses end
    //---------------------------------------------------------------------
}
