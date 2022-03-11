package cn.bugstack.springframework.tx.transaction.interceptor;


import cn.bugstack.springframework.aop.ClassFilter;
import cn.bugstack.springframework.aop.Pointcut;
import cn.bugstack.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @author zhangdd on 2022/2/27
 */
public class BeanFactoryTransactionAttributeSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private TransactionAttributeSource transactionAttributeSource;

    private final TransactionAttributeSourcePointcut pointcut=new TransactionAttributeSourcePointcut() {
        @Override
        protected TransactionAttributeSource getTransactionAttributeSource() {
            return transactionAttributeSource;
        }
    };

    public void setTransactionAttributeSource(TransactionAttributeSource transactionAttributeSource) {
        this.transactionAttributeSource = transactionAttributeSource;
    }

    public void setClassFilter(ClassFilter classFilter){
        this.pointcut.setClassFilter(classFilter);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
