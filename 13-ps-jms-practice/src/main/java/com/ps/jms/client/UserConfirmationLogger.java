package com.ps.jms.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by iuliana.cosmina on 10/9/16.
 */
public class UserConfirmationLogger {

    private static final Log logger = LogFactory.getLog(UserConfirmationLogger.class);

    private List<Confirmation> confirmations = new ArrayList<>();

    public void log(Confirmation confirmation) {
        this.confirmations.add(confirmation);
        if (logger.isInfoEnabled()) {
            logger.info("Received confirmation: " + confirmation);
        }
    }

    public List<Confirmation> getConfirmations() {
        return Collections.unmodifiableList(confirmations);
    }
}
