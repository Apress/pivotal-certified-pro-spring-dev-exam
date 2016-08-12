package com.ps.beans.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class SimpleSingleton {
    private Logger logger = LoggerFactory.getLogger(SimpleSingleton.class);

    private static SimpleSingleton instance = new SimpleSingleton();

    private SimpleSingleton() {
        logger.info(">> Creating single instance.");
    }

    public static SimpleSingleton getInstance(){
        return instance;
    }
}
