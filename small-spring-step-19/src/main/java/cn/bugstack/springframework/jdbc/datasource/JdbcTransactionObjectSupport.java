package cn.bugstack.springframework.jdbc.datasource;

/**
 * @author zhangdd on 2022/2/25
 */
public abstract class JdbcTransactionObjectSupport {

    private ConnectionHolder connectionHolder;



    public void setConnectionHolder(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }


    public ConnectionHolder getConnectionHolder() {
        return connectionHolder;
    }

    public boolean hasConnectionHolder() {
        return null != this.connectionHolder;
    }


}
