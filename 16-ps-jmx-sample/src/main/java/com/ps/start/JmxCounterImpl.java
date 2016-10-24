package com.ps.start;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;

/**
 * Created by iuliana.cosmina on 10/18/16.
 */
@Component
@ManagedResource(description = "Sample JMX managed resource", objectName="bean:name=jmxCounter")
public class JmxCounterImpl implements JmxCounter {

    private int counter =0;

    @ManagedOperation(description = "Increment the counter")
    @Override
    public int add() {
        return ++counter;
    }

    @ManagedAttribute(description = "The counter")
    @Override
    public int getCount() {
        return counter;
    }
}
