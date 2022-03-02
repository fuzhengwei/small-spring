package cn.bugstack.springframework.jdbc.datasource;

import java.sql.Connection;

/**
 * @author zhangdd on 2022/2/9
 */
public interface ConnectionHandler {

    Connection getConnection();

    default void releaseConnection(Connection con) {

    }

}
