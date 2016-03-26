package com.ps.beans.ctr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class SimpleBeanImpl implements SimpleBean{

    private Logger logger = LoggerFactory.getLogger(SimpleBean.class);

    public SimpleBeanImpl() {
        logger.info("[SimpleBeanImpl instantiation]");
    }
}
