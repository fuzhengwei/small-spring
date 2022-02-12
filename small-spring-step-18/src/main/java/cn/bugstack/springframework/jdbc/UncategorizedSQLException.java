package cn.bugstack.springframework.jdbc;

/**
 * @author zhangdd on 2022/2/9
 */
public class UncategorizedSQLException extends RuntimeException{


    public UncategorizedSQLException(String message) {
        super(message);
    }

    public UncategorizedSQLException(String task,String sql, Throwable cause) {
        super(sql, cause);
    }
}
