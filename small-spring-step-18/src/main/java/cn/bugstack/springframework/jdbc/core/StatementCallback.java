package cn.bugstack.springframework.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zhangdd on 2022/2/9
 */
public interface StatementCallback<T> {

    T doInStatement(Statement statement) throws SQLException;
}
