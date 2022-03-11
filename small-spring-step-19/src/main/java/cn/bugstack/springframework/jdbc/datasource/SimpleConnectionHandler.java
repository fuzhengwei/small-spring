package cn.bugstack.springframework.jdbc.datasource;

import cn.hutool.core.lang.Assert;

import java.sql.Connection;

/**
 * @author zhangdd on 2022/2/9
 */
public class SimpleConnectionHandler implements ConnectionHandler {

    private final Connection connection;

    public SimpleConnectionHandler(Connection connection) {
        Assert.notNull(connection, "Connection must not be null");
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

}
