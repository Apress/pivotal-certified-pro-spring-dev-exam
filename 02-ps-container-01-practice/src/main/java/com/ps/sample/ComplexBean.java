package com.ps.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by iuliana.cosmina on 3/28/16.
 * Sample class used to make bean creation staged understanding easy.
 * This class contains all the methods used to initialize and detroy a bean altogether, so you can the
 * order they are called in.
 */
public class ComplexBean {
    private Logger logger = LoggerFactory.getLogger(ComplexBean.class);

    private SimpleBean simpleBean1;
    private SimpleBean simpleBean2;

    public ComplexBean(SimpleBean simpleBean1) {
        logger.info(" --> Stage 1: Calling the constructor.");
        this.simpleBean1 = simpleBean1;
    }

    @Required
    public void setSimpleBean2(SimpleBean simpleBean2) {
        logger.info(" --> Stage 2: Calling the setter.");
        this.simpleBean2 = simpleBean2;
    }

    public SimpleBean getSimpleBean1() {
        return simpleBean1;
    }

    /**
     * The initialization method.
     * Just for fun: it instantiates the simpleBean2 only if the current time is even.
     */
    private void initMethod() {
        logger.info(" --> Stage 3: Calling the initMethod.");
        long ct = System.currentTimeMillis();
        if (ct % 2 == 0) {
            simpleBean2 = new SimpleBean();
        }
    }
    /**
     * Destroy method
     */
    private boolean destroyMethod() {
        logger.info(" --> Calling the destroyMethod.");
        simpleBean1 = null;
        simpleBean2 = null;
        return true;
    }

}
