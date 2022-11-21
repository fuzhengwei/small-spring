package cn.bugstack.springframework.test;

import cn.bugstack.springframework.test.service.JdbcService;
import cn.bugstack.springframework.test.service.impl.JdbcServiceImpl;
import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.sql.*;

/**
 * @author zhangdd on 2022/2/10
 */
public class JdbcTest {

    /**
     * CREATE TABLE `user` (
     * `id` int NOT NULL AUTO_INCREMENT,
     * `username` varchar(100) DEFAULT NULL,
     * PRIMARY KEY (`id`),
     * UNIQUE KEY `user_id_uindex` (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
     * <p>
     * CREATE TABLE `teacher` (
     * `id` int NOT NULL AUTO_INCREMENT,
     * `teacher_name` varchar(50) NOT NULL,
     * `phone` varchar(20) DEFAULT NULL,
     * PRIMARY KEY (`id`),
     * UNIQUE KEY `teacher_id_uindex` (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
     */
    private DruidDataSource dataSource;
    private Connection connection;
    private Statement statement;

    @Before
    public void init() throws SQLException {
        dataSource = new DruidDataSource();
        dataSource.setDriver(new Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/zhangdd?useSSL=false");
        dataSource.setPassword("12345678");
        dataSource.setUsername("root");

        connection = dataSource.getConnection().getConnection();

        statement = connection.createStatement();

    }

    @Test
    public void saveDataWithoutTranslation() throws SQLException {

        statement.execute("insert into teacher(teacher_name) values ('李老师')");

        statement.execute("insert into user(id,username) values(1,'重复数据')");

    }

    @Test
    public void saveDataWithTranslation() throws SQLException {

        connection.setAutoCommit(false);
        try {
            statement.execute("insert into teacher(teacher_name) values ('王老师')");

            statement.execute("insert into user(id,username) values(1,'重复数据')");
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }

//        connection.commit();
        System.out.println("=====");
    }

    @Test
    public void saveDataWithTranslationProxy() throws SQLException {
        JdbcService jdbcService=new JdbcServiceImpl(statement);

        TransactionProxy transactionProxy=new TransactionProxy(connection,jdbcService);

        JdbcService jdbcServiceProxy = (JdbcService) Proxy.newProxyInstance(jdbcService.getClass().getClassLoader(),
                jdbcService.getClass().getInterfaces(), transactionProxy);

        jdbcServiceProxy.saveDataWithTranslation();
    }


    @After
    public void destroy() throws SQLException {
        if (null != statement) {
            statement.close();
        }
        if (null != connection) {
            connection.close();
        }
        if (null != dataSource) {
            dataSource.close();
        }
    }
}
