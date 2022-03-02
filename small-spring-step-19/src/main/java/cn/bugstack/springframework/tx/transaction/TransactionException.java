package cn.bugstack.springframework.tx.transaction;

/**
 * @author zhangdd on 2022/2/22
 */
public class TransactionException extends RuntimeException{

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
