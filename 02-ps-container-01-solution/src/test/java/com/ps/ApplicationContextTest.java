package com.ps;

import com.ps.another.quiz.AnotherQuizBean;
import com.ps.cfg.Appconfig;
import com.ps.quiz.QuizBean;
import com.ps.sample.ComplexBean;
import com.ps.sample.SimpleBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/23/16.
 */
public class ApplicationContextTest {

    private Logger logger = LoggerFactory.getLogger(ApplicationContextTest.class);

    @Test
    public void testDataSource1() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");
        logger.info(" >> init done.");
        DataSource dataSource1 = ctx.getBean("dataSource1", DataSource.class);
        assertNotNull(dataSource1);
        logger.info(" >> usage done.");
        ctx.close();
    }

    @Test
    public void testBeanCreation() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-01.xml");

        ComplexBean complexBean = ctx.getBean(ComplexBean.class);
        assertNotNull(complexBean);
        ctx.close();
    }

    @Test
    public void testBeanScope() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-02.xml");

        SimpleBean sb01 = ctx.getBean("simpleBean3", SimpleBean.class);
        SimpleBean sb02 = ctx.getBean("simpleBean3", SimpleBean.class);
        assertNotEquals(sb01, sb02);
        ctx.close();
    }


    @Test
    public void testQuiz() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(Appconfig.class);

        QuizBean quizBean = ctx.getBean(QuizBean.class);
        AnotherQuizBean anotherQuizBean = ctx.getBean(AnotherQuizBean.class);
        assertNotNull(quizBean);
        assertNotNull(anotherQuizBean);
        ctx.close();
    }

}
