package cn.bugstack.springframework.tx.transaction.support;

/**
 * @author zhangdd on 2022/2/22
 */
public class DefaultTransactionStatus extends AbstractTransactionStatus {

    private final Object transaction;

    private final boolean newTransaction;


    public DefaultTransactionStatus(Object transaction, boolean newTransaction) {

        this.transaction = transaction;
        this.newTransaction = newTransaction;
    }

    public Object getTransaction() {
        return transaction;
    }

    public boolean hasTransaction() {
        return this.transaction != null;
    }

    @Override
    public boolean isNewTransaction() {
        return hasTransaction() && this.newTransaction;
    }


}
