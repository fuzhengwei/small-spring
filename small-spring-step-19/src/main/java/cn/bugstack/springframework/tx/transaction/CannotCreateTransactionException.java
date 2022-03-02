package cn.bugstack.springframework.tx.transaction;

/**
 * @author zhangdd on 2022/2/22
 */
public class CannotCreateTransactionException extends TransactionException{


    public CannotCreateTransactionException(String message) {
        super(message);
    }

    public CannotCreateTransactionException(String message, Throwable cause) {
        super(message, cause);
    }


}
