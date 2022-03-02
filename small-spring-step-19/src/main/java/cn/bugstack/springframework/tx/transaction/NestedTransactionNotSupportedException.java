package cn.bugstack.springframework.tx.transaction;

/**
 * @author zhangdd on 2022/2/22
 */
public class NestedTransactionNotSupportedException extends CannotCreateTransactionException{
    public NestedTransactionNotSupportedException(String message) {
        super(message);
    }

    public NestedTransactionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
