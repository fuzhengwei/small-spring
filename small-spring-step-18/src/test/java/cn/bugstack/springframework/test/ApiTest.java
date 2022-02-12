package cn.bugstack.springframework.test;


import cn.bugstack.springframework.context.support.ClassPathXmlApplicationContext;
import cn.bugstack.springframework.jdbc.support.JdbcTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdd on 2022/1/28
 */
public class ApiTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }


    @Test
    public void executeSqlTest() {

        jdbcTemplate.execute("        CREATE TABLE `user` (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `username` varchar(100) DEFAULT NULL,\n" +
                "        PRIMARY KEY (`id`),\n" +
                "        UNIQUE KEY `user_id_uindex` (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci");
    }


    @Test
    public void queryForListTest() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("select * from user");
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            Map<String, Object> objectMap = allResult.get(i);
            System.out.println(objectMap);
        }
    }

    @Test
    public void queryListWithColumnClassTypeTest() {
        List<String> allResult = jdbcTemplate.queryForList("select username from user", String.class);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            String username = allResult.get(i);
            System.out.println(username);
        }
    }

    @Test
    public void queryListWithColumnClassTypeWithArgTest() {
        List<String> allResult = jdbcTemplate.queryForList("select username from user where id=?", String.class, 1);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            String username = allResult.get(i);
            System.out.println(username);
        }
    }

    @Test
    public void queryListWithArgTest() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("select * from user where id=?", 1);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            Map<String, Object> row = allResult.get(i);
            System.out.println(row);
        }
    }

    @Test
    public void queryObjectTest() {
        String username = jdbcTemplate.queryForObject("select username from user where id=1", String.class);
        System.out.println(username);
    }

    @Test
    public void queryMapTest() {
        Map<String, Object> row = jdbcTemplate.queryForMap("select * from user where id=1");
        System.out.println(row);
    }

    @Test
    public void queryMapWithArgTest() {
        Map<String, Object> row = jdbcTemplate.queryForMap("select * from user where id=?", 1);
        System.out.println(row);
    }
}
