package Logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingController {

    @Pointcut("execution(* com.assignment.billapplication.controller.*.*(..))")
    public void loggingPointCut(){}

    @Before("loggingPointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("=====> Before @"+ methodSignature);
    }

    @After("loggingPointCut()")
    public void AfterAdvice(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("=====> After @"+ methodSignature);
    }



    //        Object[] args = joinPoint.getArgs();

// Method signature + args >> using joinPoint
    // Pointcut > before > afterReturning (pointcut + returning) > AfterThrowing(pointcut , throwing) throwable >



}



