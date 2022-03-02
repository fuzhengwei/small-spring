package cn.bugstack.springframework.tx.transaction;

/**
 * @author zhangdd on 2022/2/21
 */
public interface PlatformTransactionManager {

    TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;

}
