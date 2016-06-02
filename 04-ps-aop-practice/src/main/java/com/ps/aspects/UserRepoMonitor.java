package com.ps.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by iuliana.cosmina on 6/2/16.
 */
@Aspect
public class UserRepoMonitor {

    private static final Logger logger = Logger.getLogger(UserRepoMonitor.class);

    @Around("execution(public * com.ps.repos.*.*Repo+.find*(..))")
    public Object monitorFind(ProceedingJoinPoint repositoryMethod) throws Throwable {
        String methodName = repositoryMethod.getSignature().getName();
        logger.info(" ---> Intercepting call of: " + methodName);
        long t1 = System.currentTimeMillis();
        try {
            //put a pause here so we can register an execution time
            Thread.sleep(1000L);
            return repositoryMethod.proceed();
        } finally {
            long t2 = System.currentTimeMillis();
            logger.info(" ---> Execution of " + methodName + " took: "+ (t2-t1)/1000 + " ms.");
        }
    }

    //@Before("execution(public * com.ps.repos.*.*Repo+.update*(..))")
    public Object monitorUpdate(ProceedingJoinPoint repositoryMethod) throws Throwable {
        String methodName = repositoryMethod.getSignature().getName();
        logger.info(" ---> Intercepting call of: " + methodName);
        long t1 = System.currentTimeMillis();
        try {
            //put a pause here so we can register an execution time
            Thread.sleep(1000L);
            return repositoryMethod.proceed();
        } finally {
            long t2 = System.currentTimeMillis();
            logger.info(" ---> Execution of " + methodName + " took: "+ (t2-t1)/1000 + " ms.");
        }
    }
}
