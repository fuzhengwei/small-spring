package cn.bugstack.springframework.jdbc.core;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdd on 2022/2/9
 */
public interface JdbcOperations {

    <T> T execute(StatementCallback<T> action);

    void execute(String sql);

    //---------------------------------------------------------------------
    // query
    //---------------------------------------------------------------------

    <T> T query(String sql, ResultSetExtractor<T> res);

    <T> T query(String sql, Object[] args, ResultSetExtractor<T> rse);

    <T> List<T> query(String sql, RowMapper<T> rowMapper);

    <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper);

    <T> T query(String sql, PreparedStatementSetter pss, ResultSetExtractor<T> rse);

    //---------------------------------------------------------------------
    // queryForList
    //---------------------------------------------------------------------

    List<Map<String, Object>> queryForList(String sql);

    /**
     * 查询数据库表中某一个字段
     */
    <T> List<T> queryForList(String sql, Class<T> elementType);

    <T> List<T> queryForList(String sql, Class<T> elementType, Object... args);

    List<Map<String, Object>> queryForList(String sql, Object... args);

    //---------------------------------------------------------------------
    // queryForObject
    //---------------------------------------------------------------------

    <T> T queryForObject(String sql, RowMapper<T> rowMapper);

    <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper);

    /**
     * 查询数据库表中 某一条记录的 某一个字段
     */
    <T> T queryForObject(String sql, Class<T> requiredType);

    //---------------------------------------------------------------------
    // queryForMap
    //---------------------------------------------------------------------

    Map<String, Object> queryForMap(String sql);

    Map<String, Object> queryForMap(String sql, Object... args);


}
