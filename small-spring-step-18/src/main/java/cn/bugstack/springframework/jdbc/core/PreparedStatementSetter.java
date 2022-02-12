package cn.bugstack.springframework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhangdd on 2022/2/12
 */
public interface PreparedStatementSetter {

    void setValues(PreparedStatement ps) throws SQLException;
}
