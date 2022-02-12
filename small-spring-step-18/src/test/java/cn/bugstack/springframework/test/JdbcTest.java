package cn.bugstack.springframework.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
     * ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
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
    public void ddlTest() throws SQLException {

        boolean execute = statement.execute("        CREATE TABLE `user` (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `username` varchar(100) DEFAULT NULL,\n" +
                "        PRIMARY KEY (`id`),\n" +
                "        UNIQUE KEY `user_id_uindex` (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci");
        System.out.println(execute);
    }

    @Test
    public void dmlTest() throws SQLException {
        statement.executeUpdate("insert into user(username)values (4)");

        statement.executeUpdate("update user set username='小明' where id=4");

        statement.executeUpdate("delete from user where id=5");
    }

    @Test
    public void dqlTest() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from user");
        int row = 1;
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String username = resultSet.getString("username");
            System.out.printf("第%d行数据:id:%d,username:%s%n", row++, id, username);
        }
    }

    @Test
    public void batchStatementDMLTest() throws SQLException {
        String sql1 = "insert into user(username) values('小王')";
        String sql2 = "insert into user(username) values('小刘')";
        String sql3 = "update  user set username='小刘刘' where username='小刘'";
        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);
        int[] result = statement.executeBatch();
        for (int i : result) {
            System.out.printf("执行结果:%d\n", i);
        }
    }

    @Test
    public void preparedStatementDMLTest() throws SQLException {
        String sql = "insert into user(username) values(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "小李");
        int result = preparedStatement.executeUpdate();
        System.out.println(result);
    }

    @Test
    public void preparedStatementDQLTest() throws SQLException {
        String sql = "select * from user where id=? and username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "小张");


        ResultSet resultSet = preparedStatement.executeQuery();
        int row = 1;
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String username = resultSet.getString("username");
            System.out.printf("第%d行数据:id:%d,username:%s%n", row++, id, username);
        }
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
