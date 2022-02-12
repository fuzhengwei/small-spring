package cn.bugstack.springframework.jdbc.support;

import cn.bugstack.springframework.jdbc.UncategorizedSQLException;
import cn.bugstack.springframework.jdbc.core.*;
import cn.bugstack.springframework.jdbc.datasource.DataSourceUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author zhangdd on 2022/2/9
 */
public class JdbcTemplate extends JdbcAccessor implements JdbcOperations {


    private int fetchSize = -1;

    private int maxRows = -1;

    private int queryTimeout = -1;


    public JdbcTemplate() {
    }

    public JdbcTemplate(DataSource dataSource) {
        setDataSource(dataSource);
        afterPropertiesSet();
    }


    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    private <T> T execute(StatementCallback<T> action, boolean closeResources) {
        Connection con = DataSourceUtils.getConnection(obtainDataSource());

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            applyStatementSettings(stmt);

            return action.doInStatement(stmt);

        } catch (SQLException e) {
            String sql = getSql(action);
            JdbcUtils.closeStatement(stmt);
            stmt = null;
            throw translateException("ConnectionCallback", sql, e);
        } finally {
            if (closeResources) {
                JdbcUtils.closeStatement(stmt);
            }
        }
    }

    private <T> T execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action, boolean closeResources) {

        Assert.notNull(psc, "PreparedStatementCreator must not be null");
        Assert.notNull(action, "Callback object must not be null");


        Connection con = DataSourceUtils.getConnection(obtainDataSource());
        PreparedStatement ps = null;
        try {
            ps = psc.createPreparedStatement(con);
            applyStatementSettings(ps);
            T result = action.doInPreparedStatement(ps);
            return result;
        } catch (SQLException ex) {

            String sql = getSql(psc);
            psc = null;
            JdbcUtils.closeStatement(ps);
            ps = null;
            DataSourceUtils.releaseConnection(con, getDataSource());
            con = null;
            throw translateException("PreparedStatementCallback", sql, ex);
        } finally {
            if (closeResources) {

                JdbcUtils.closeStatement(ps);
                DataSourceUtils.releaseConnection(con, getDataSource());
            }
        }
    }

    public <T> T query(
            PreparedStatementCreator psc, final PreparedStatementSetter pss, final ResultSetExtractor<T> rse) {

        Assert.notNull(rse, "ResultSetExtractor must not be null");

        return execute(psc, new PreparedStatementCallback<T>() {
            @Override
            public T doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ResultSet rs = null;
                try {
                    if (pss != null) {
                        pss.setValues(ps);
                    }
                    rs = ps.executeQuery();
                    return rse.extractData(rs);
                } finally {
                    JdbcUtils.closeResultSet(rs);
                }
            }
        }, true);
    }

    protected void applyStatementSettings(Statement stat) throws SQLException {
        int fetchSize = getFetchSize();
        if (fetchSize != -1) {
            stat.setFetchSize(fetchSize);
        }
        int maxRows = getMaxRows();
        if (maxRows != -1) {
            stat.setMaxRows(maxRows);
        }

    }

    protected UncategorizedSQLException translateException(String task, String sql, SQLException ex) {
        return new UncategorizedSQLException(task, sql, ex);
    }

    private static String getSql(Object sqlProvider) {
        if (sqlProvider instanceof SqlProvider) {
            return ((SqlProvider) sqlProvider).getSql();
        } else {
            return null;
        }
    }

    @Override
    public <T> T execute(StatementCallback<T> action) {
        return execute(action, true);
    }

    @Override
    public void execute(String sql) {

        class ExecuteStatementCallback implements StatementCallback<Object>, SqlProvider {

            @Override
            public Object doInStatement(Statement statement) throws SQLException {
                statement.execute(sql);
                return null;
            }

            @Override
            public String getSql() {
                return sql;
            }
        }
        execute(new ExecuteStatementCallback(), true);
    }

    @Override
    public <T> T query(String sql, ResultSetExtractor<T> res) {
        Assert.notNull(sql, "SQL must not be null");
        Assert.notNull(res, "ResultSetExtractor must be null");

        class QueryStatementCallback implements StatementCallback<T>, SqlProvider {

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public T doInStatement(Statement statement) throws SQLException {
                ResultSet rs = statement.executeQuery(sql);
                return res.extractData(rs);
            }
        }

        return execute(new QueryStatementCallback(), true);
    }

    @Override
    public <T> T query(String sql, Object[] args, ResultSetExtractor<T> rse) {
        return query(sql, newArgPreparedStatementSetter(args), rse);
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return result(query(sql, new RowMapperResultSetExtractor<>(rowMapper)));
    }

    @Override
    public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        return result(query(sql, args, new RowMapperResultSetExtractor<>(rowMapper)));
    }

    @Override
    public <T> T query(String sql, PreparedStatementSetter pss, ResultSetExtractor<T> rse) {
        return query(new SimplePreparedStatementCreator(sql), pss, rse);
    }

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper) {
        List<T> results = query(sql, rowMapper);

        if (CollUtil.isEmpty(results)) {
            throw new RuntimeException("Incorrect result size: expected 1, actual 0");
        }
        if (results.size() > 1) {
            throw new RuntimeException("Incorrect result size: expected 1, actual " + results.size());
        }
        return results.iterator().next();
    }

    @Override
    public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) {
        List<T> results = query(sql, args, new RowMapperResultSetExtractor<>(rowMapper, 1));

        if (CollUtil.isEmpty(results)) {
            throw new RuntimeException("Incorrect result size: expected 1, actual 0");
        }
        if (results.size() > 1) {
            throw new RuntimeException("Incorrect result size: expected 1, actual " + results.size());
        }
        return results.iterator().next();
    }

    @Override
    public <T> T queryForObject(String sql, Class<T> requiredType) {
        return queryForObject(sql, getSingleColumnRowMapper(requiredType));
    }

    @Override
    public Map<String, Object> queryForMap(String sql) {
        return result(queryForObject(sql, getColumnMapRowMapper()));
    }

    @Override
    public Map<String, Object> queryForMap(String sql, Object... args) {
        return result(queryForObject(sql, args, getColumnMapRowMapper()));
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql) {
        return query(sql, getColumnMapRowMapper());
    }

    @Override
    public <T> List<T> queryForList(String sql, Class<T> elementType) {
        return query(sql, getSingleColumnRowMapper(elementType));
    }

    @Override
    public <T> List<T> queryForList(String sql, Class<T> elementType, Object... args) {
        return query(sql, args, getSingleColumnRowMapper(elementType));
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        return query(sql, args, getColumnMapRowMapper());
    }

    private static <T> T result(T result) {
        Assert.state(null != result, "No result");
        return result;
    }

    protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
        return new ColumnMapRowMapper();
    }

    protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
        return new SingleColumnRowMapper<>(requiredType);
    }

    protected PreparedStatementSetter newArgPreparedStatementSetter(Object[] args) {
        return new ArgumentPreparedStatementSetter(args);
    }

    private static class SimplePreparedStatementCreator implements PreparedStatementCreator, SqlProvider {

        private final String sql;

        public SimplePreparedStatementCreator(String sql) {
            this.sql = sql;
        }


        @Override
        public String getSql() {
            return this.sql;
        }

        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            return con.prepareStatement(this.sql);
        }
    }
}
