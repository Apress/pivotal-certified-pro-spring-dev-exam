package com.ps.aspects;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by iuliana.cosmina on 6/5/16.
 */
public class PointcutContainer {

    @Pointcut("execution( * com.ps.repos.*.*UserRepo+.update*(Long, String))")
    public void repoUpdate() {
    }

    @Pointcut("execution (* com.ps.services.*Service+.update*(Long, String)))")
    public void serviceUpdate() {
    }
}
