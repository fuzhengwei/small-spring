package cn.bugstack.springframework.test;


import cn.bugstack.springframework.aop.AdvisedSupport;
import cn.bugstack.springframework.aop.TargetSource;
import cn.bugstack.springframework.aop.framework.Cglib2AopProxy;
import cn.bugstack.springframework.context.support.ClassPathXmlApplicationContext;
import cn.bugstack.springframework.jdbc.datasource.DataSourceTransactionManager;
import cn.bugstack.springframework.jdbc.support.JdbcTemplate;
import cn.bugstack.springframework.test.service.JdbcService;
import cn.bugstack.springframework.test.service.impl.JdbcServiceImpl;
import cn.bugstack.springframework.tx.transaction.annotation.AnnotationTransactionAttributeSource;
import cn.bugstack.springframework.tx.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor;
import cn.bugstack.springframework.tx.transaction.interceptor.TransactionInterceptor;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangdd on 2022/1/28
 */
public class ApiTest {

    private JdbcTemplate jdbcTemplate;

//    private JdbcService jdbcService;

    private DataSource dataSource;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        dataSource = applicationContext.getBean(DruidDataSource.class);

//        jdbcService = applicationContext.getBean(JdbcServiceImpl.class);
    }

    @Test
    public void jdbcWithTransaction() {

        JdbcService jdbcService = new JdbcServiceImpl();

        AnnotationTransactionAttributeSource transactionAttributeSource = new AnnotationTransactionAttributeSource();
        transactionAttributeSource.findTransactionAttribute(jdbcService.getClass());


        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

        TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, transactionAttributeSource);


        BeanFactoryTransactionAttributeSourceAdvisor btas = new BeanFactoryTransactionAttributeSourceAdvisor();
        btas.setAdvice(interceptor);


        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(jdbcService));
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(btas.getPointcut().getMethodMatcher());
        advisedSupport.setProxyTargetClass(false);

        JdbcService proxyCglib = (JdbcServiceImpl) new Cglib2AopProxy(advisedSupport).getProxy();


        proxyCglib.saveData(jdbcTemplate);
    }


}

