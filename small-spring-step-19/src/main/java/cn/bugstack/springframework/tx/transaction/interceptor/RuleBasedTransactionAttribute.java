package cn.bugstack.springframework.tx.transaction.interceptor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangdd on 2022/2/26
 */
public class RuleBasedTransactionAttribute extends DefaultTransactionAttribute implements Serializable {

    private List<RollbackRuleAttribute> rollbackRules;

    public RuleBasedTransactionAttribute() {
        super();
    }

    public void setRollbackRules(List<RollbackRuleAttribute> rollbackRules) {
        this.rollbackRules = rollbackRules;
    }
}
