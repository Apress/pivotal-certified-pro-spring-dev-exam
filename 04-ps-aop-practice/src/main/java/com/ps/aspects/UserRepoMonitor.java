package com.ps.aspects;

import com.ps.exception.UnexpectedException;
import com.ps.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 6/2/16.
 */
//TODO 21. Declare this class as an aspect
public class UserRepoMonitor {

    private static final Logger logger = Logger.getLogger(UserRepoMonitor.class);

    /*TODO 26. Declare this method as a Before advice and use as pointcut expression the expression
     associated with the "repoUpdate" from the "PointcutContainer" class */
    public void beforeServiceUpdate(UserService service, Long id, String pass) throws Throwable {
        logger.info(" ---> Target object " + service.getClass());

        if (StringUtils.indexOfAny(pass, new String[]{"$", "#", "$", "%"}) != -1) {
            throw new IllegalArgumentException("Text for " + id + " contains weird characters!");
        }
    }

    /*TODO 22. Declare this method as a AfterReturning advice and create a pointcut expression that matches any method
     with the name starting with "update" that is defined in a class with the name containing "Service" */
    public void afterServiceUpdate(JoinPoint joinPoint, int result) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(result == 0) {
            logger.info(" ---> Update method " + className + "." + methodName + " performed as expected.");
        }
    }

    /*TODO 23. Declare this method as a AfterThrowing advice and create a pointcut expression that matches any method
     named updateUsername that is defined in a class with the name containing "Service" */
    public void afterBadUpdate(JoinPoint joinPoint, Exception e) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(e instanceof DuplicateKeyException) {
            logger.info(" ---> Update method " + className + "." + methodName + " failed. Existing username found.");
        } else {
            throw new UnexpectedException(" Ooops!", e);
        }
    }

    @Before("com.ps.aspects.PointcutContainer.repoUpdate()")
    public void beforeRepoUpdate(JoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " is about to be called");
    }

    /*TODO 24. Declare this method as an Around advice and create a pointcut expression that matches any method
     with the name starting with "find" that is defined in a class with the name containing "Repo" */
    public Object monitorFind(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Intercepting call of: " + methodName);
        long t1 = System.currentTimeMillis();
        try {
            return null; //TODO 25. Call the target method
        } finally {
            long t2 = System.currentTimeMillis();
            logger.info(" ---> Execution of " + methodName + " took: " + (t2 - t1) / 1000 + " ms.");
        }
    }

    private static long findByIdCount = 0;

    @After("execution(public * com.ps.repos.*.JdbcTemplateUserRepo+.updateUsername(..))")
    public void afterFindById(JoinPoint joinPoint) throws Throwable {
        ++findByIdCount;
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + methodName + " was called " + findByIdCount + " times.");
    }
}
