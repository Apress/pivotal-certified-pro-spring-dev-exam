package com.ps.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * Created by iuliana.cosmina on 3/28/16.
 * Sample class used to make bean creation staged understanding easy
 */
public class ComplexBean /*implements InitializingBean*/{
    private Logger logger = LoggerFactory.getLogger(ComplexBean.class);

    private SimpleBean simpleBean1;
    private SimpleBean simpleBean2;

    public ComplexBean(SimpleBean simpleBean1) {
        logger.info("Stage 1: Calling the constructor.");
        this.simpleBean1 = simpleBean1;
    }

    public void setSimpleBean2(SimpleBean simpleBean2) {
        logger.info("Stage 2: Calling the setter.");
        this.simpleBean2 = simpleBean2;
    }

    /*@Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Stage 4: Calling afterPropertiesSet.");
    }*/

    /**
     * The initialization method.
     * Just for fun: it instantiates the simpleBean2 only if the current time is even.
     */
    @PostConstruct
    private void initMethod() {
        logger.info("Stage 4: Calling the initMethod.");
        long ct = System.currentTimeMillis();
        if (ct % 2 == 0) {
            simpleBean2 = new SimpleBean();
        }
    }

    /**
     * Destroy method
     */
   // @PostConstruct
    private void destroyMethod(){
        logger.info(" --> Calling the destroyMethod.");
        simpleBean1 = null;
        simpleBean2 = null;
    }
}
