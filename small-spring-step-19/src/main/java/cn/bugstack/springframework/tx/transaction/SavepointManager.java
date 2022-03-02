package cn.bugstack.springframework.tx.transaction;

/**
 * @author zhangdd on 2022/2/22
 */
public interface SavepointManager {

    Object createSavepoint() throws TransactionException;

    void rollbackToSavepoint(Object savepoint) throws TransactionException;

    void releaseSavepoint(Object savepoint) throws TransactionException;
}
