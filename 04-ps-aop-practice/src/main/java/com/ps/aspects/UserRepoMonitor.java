package com.ps.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 6/2/16.
 */
@Aspect
@Component
public class UserRepoMonitor {

    private static long findByIdCount =0;

    private static final Logger logger = Logger.getLogger(UserRepoMonitor.class);

    //@Around("execution(public * com.ps.repos.*.*Repo+.find*(..))")
    public Object monitorFind(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Intercepting call of: " + methodName);
        long t1 = System.currentTimeMillis();
        try {
            //put a pause here so we can register an execution time
            Thread.sleep(1000L);
            return joinPoint.proceed();
        } finally {
            long t2 = System.currentTimeMillis();
            logger.info(" ---> Execution of " + methodName + " took: "+ (t2-t1)/1000 + " ms.");
        }
    }

    private Long id;
    private String pass;

    @Pointcut("execution(public * com.ps.repos.*.*Repo+.update*(..)) && args(id, pass)")
    public void monitorUpdate(Long id, String pass){};


    @Before("monitorUpdate(id, pass)")
    public void intercept(Long id, String pass) throws Throwable {
        logger.info(" ---> Intercepting Update Call");
        this.id = id;
        this.pass = pass;
        logger.info(" ---> Intercepted values: " + id + ", "+ pass);
    }


    @Before("execution(public * com.ps.repos.*.JdbcTemplateUserRepo+.findById(..))")
    public void beforeFindById(JoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + methodName + " is about to be called");
    }

    //this is a very precise breakpoint
    @After("execution(@org.springframework.stereotype.Repository * com.ps.repos.*.*Repo.findById))")
    public void afterFindById(JoinPoint joinPoint) throws Throwable {
        ++findByIdCount;
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + methodName + " was called "+ findByIdCount + " times.");
    }
}
