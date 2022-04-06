package com.gadelev.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("@annotation(com.gadelev.aspect.Loggable)")
    public void logCityByRequest() {

    }

    @Around("logCityByRequest()")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object request = proceedingJoinPoint.getArgs()[0];

        Object result = proceedingJoinPoint.proceed();

        LOGGER.info("Request id : {}", request.toString());

        return result;
    }
}