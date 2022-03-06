package cn.bugstack.springframework.tx.transaction.support;

import cn.hutool.core.lang.Assert;

/**
 * @author zhangdd on 2022/3/6
 */
public class TransactionSynchronizationUtils {

    static Object unwrapResourceIfNecessary(Object resource) {
        Assert.notNull(resource, "Resource must not be null");
        Object resourceRef = resource;


        return resourceRef;
    }
}
