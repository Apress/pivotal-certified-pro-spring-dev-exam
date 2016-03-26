package com.ps.beans.ctr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class ComplexBeanImpl implements ComplexBean {

    private Logger logger = LoggerFactory.getLogger(ComplexBeanImpl.class);

    private SimpleBean simpleBean;

    private boolean isComplex;

    public ComplexBeanImpl(SimpleBean simpleBean) {
        logger.info("[ComplexBeanImpl instantiation]");
        this.simpleBean = simpleBean;
    }

    public ComplexBeanImpl(SimpleBean simpleBean, boolean isComplex) {
        this.simpleBean = simpleBean;
        this.isComplex = isComplex;
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public boolean isComplex() {
        return isComplex;
    }
}
