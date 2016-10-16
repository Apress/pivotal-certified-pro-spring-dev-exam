package com.ps.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by iuliana.cosmina on 10/11/16.
 */
@Component
public class ConfirmationReceiver {

    private Logger logger = LoggerFactory.getLogger(ConfirmationReceiver.class);

    @JmsListener(destination = "confirmationQueue", containerFactory = "connectionFactory")
    public void receiveConfirmation(Confirmation confirmation) {
        logger.info(" >>  Received confirmation: " + confirmation);

    }
}