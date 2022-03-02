package cn.bugstack.springframework.tx.transaction.interceptor;

import java.io.Serializable;

/**
 * @author zhangdd on 2022/2/26
 */
public class RollbackRuleAttribute implements Serializable {

    private final String exceptionName;

    public RollbackRuleAttribute(Class<?> clazz) {
        this.exceptionName = clazz.getName();
    }
}
