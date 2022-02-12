package cn.bugstack.springframework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhangdd on 2022/2/10
 */
public interface ResultSetExtractor<T> {

    T extractData(ResultSet rs) throws SQLException;
}
