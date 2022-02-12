package cn.bugstack.springframework.jdbc;

import java.sql.SQLException;

/**
 * @author zhangdd on 2022/2/9
 */
public class CannotGetJdbcConnectionException extends RuntimeException {

    public CannotGetJdbcConnectionException(String message) {
        super(message);
    }

    public CannotGetJdbcConnectionException(String message, SQLException ex) {
        super(message, ex);
    }
}
