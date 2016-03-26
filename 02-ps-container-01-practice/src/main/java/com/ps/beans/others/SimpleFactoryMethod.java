package com.ps.beans.others;

import com.ps.beans.SimpleBean;
import com.ps.beans.SimpleBeanImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 * Class used to create a bean using the factory method
 */
public class SimpleFactoryMethod {

    private static Logger logger = LoggerFactory.getLogger(SimpleFactoryMethod.class);

    /**
     * the factory method that will be called by the container
     * @return
     */
    public static SimpleBean getSimpleBean() {
        logger.info(">> Instantiating a SimpleBeanImpl.");
        return new SimpleBeanImpl();
    }

}
