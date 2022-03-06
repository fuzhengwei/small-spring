package cn.bugstack.springframework.jdbc.datasource;

import cn.bugstack.springframework.beans.factory.InitializingBean;
import cn.bugstack.springframework.tx.transaction.CannotCreateTransactionException;
import cn.bugstack.springframework.tx.transaction.TransactionDefinition;
import cn.bugstack.springframework.tx.transaction.TransactionException;
import cn.bugstack.springframework.tx.transaction.support.AbstractPlatformTransactionManager;
import cn.bugstack.springframework.tx.transaction.support.DefaultTransactionStatus;
import cn.bugstack.springframework.tx.transaction.support.TransactionSynchronizationManager;
import cn.hutool.core.lang.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zhangdd on 2022/2/24
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager
        implements InitializingBean {

    private DataSource dataSource;


    public DataSourceTransactionManager() {

    }

    public DataSourceTransactionManager(DataSource dataSource) {
        setDataSource(dataSource);
        afterPropertiesSet();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        Assert.notNull(dataSource, "No DataSource set");
        return dataSource;
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        DataSourceTransactionObject txObject = new DataSourceTransactionObject();
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(obtainDataSource());
        txObject.setConnectionHolder(conHolder, false);
        return txObject;
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection con = txObject.getConnectionHolder().getConnection();
        try {
            con.commit();
        } catch (SQLException e) {
            throw new TransactionException("Could not commit JDBC transaction", e);
        }
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection con = txObject.getConnectionHolder().getConnection();
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new TransactionException("Could not roll back JDBC transaction", e);
        }
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
        Connection con = null;
        try {
            Connection newCon = obtainDataSource().getConnection();
            txObject.setConnectionHolder(new ConnectionHolder(newCon), true);

            con = txObject.getConnectionHolder().getConnection();
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prepareTransactionalConnection(con, definition);

            TransactionSynchronizationManager.bindResource(obtainDataSource(), txObject.getConnectionHolder());

        } catch (SQLException e) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            txObject.setConnectionHolder(null, false);
            throw new CannotCreateTransactionException("Could not open JDBC Connection for transaction", e);
        }

    }

    @Override
    public void afterPropertiesSet() {
        if (null == getDataSource()) {
            throw new IllegalArgumentException("Property 'datasource' is required");
        }
    }

    protected void prepareTransactionalConnection(Connection con, TransactionDefinition definition) throws SQLException {
        if (definition.isReadOnly()) {
            try (Statement stmt = con.createStatement()) {
//                stmt.execute("set transaction read only");
            }
        }
    }

    public static class DataSourceTransactionObject extends JdbcTransactionObjectSupport {
        private boolean newConnectionHolder;
        private boolean mustRestoreAutoCommit;

        public void setConnectionHolder(ConnectionHolder connectionHolder, boolean newConnectionHolder) {
            super.setConnectionHolder(connectionHolder);
            this.newConnectionHolder = newConnectionHolder;
        }
    }
}
