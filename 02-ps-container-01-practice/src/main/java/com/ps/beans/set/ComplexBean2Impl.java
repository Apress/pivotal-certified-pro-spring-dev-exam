package com.ps.beans.set;

import com.ps.beans.ComplexBean;
import com.ps.beans.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class ComplexBean2Impl implements ComplexBean {

    private Logger logger = LoggerFactory.getLogger(ComplexBean2Impl.class);

    private SimpleBean simpleBean;

    private boolean complex;

    public ComplexBean2Impl(SimpleBean simpleBean)
    {
        logger.info("[ComplexBean2Impl instantiation]");
        this.simpleBean = simpleBean;
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public boolean isComplex() {
        return complex;
    }

    public void setComplex(boolean complex) {
        logger.info("[Injecting dependency complex]");
        this.complex = complex;
    }
}
