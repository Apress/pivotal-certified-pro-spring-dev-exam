package com.ps.beans.ctr;

import com.ps.beans.ComplexBean;
import com.ps.beans.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class ComplexBean2Impl implements ComplexBean {
    private Logger logger = LoggerFactory.getLogger(ComplexBeanImpl.class);

    private SimpleBean simpleBean1;
    private SimpleBean simpleBean2;

    public ComplexBean2Impl(SimpleBean simpleBean1, SimpleBean simpleBean2) {
        logger.info("[ComplexBean2Impl instantiation]");
        this.simpleBean1 = simpleBean1;
        this.simpleBean2 = simpleBean2;
    }

    public SimpleBean getSimpleBean1() {
        return simpleBean1;
    }

    public SimpleBean getSimpleBean2() {
        return simpleBean2;
    }
}
