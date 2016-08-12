package com.ps.beans.set;

import com.ps.beans.ComplexBean;
import com.ps.beans.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class ComplexBeanImpl implements ComplexBean {

    private Logger logger = LoggerFactory.getLogger(ComplexBeanImpl.class);

    private SimpleBean simpleBean;

    private boolean complex;

    /**
     * Default no-argument constructor, not really necessary, just added here to  print a log entry,
     * in order to see in the java console when objects of this type are instantiated
     */
    public ComplexBeanImpl() {
        logger.info("[ComplexBeanImpl instantiation]");
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public boolean isComplex() {
        return complex;
    }

    public void setSimpleBean(SimpleBean simpleBean) {
        logger.info("[Injecting dependency simpleBean]");
        this.simpleBean = simpleBean;
    }

    public void setComplex(boolean complex) {
        logger.info("[Injecting dependency complex]");
        this.complex = complex;
    }
}
