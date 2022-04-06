package com.gadelev.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspectWithoutAnnotaton {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspectWithoutAnnotaton.class);

    @Pointcut("execution(* com.gadelev..*.*.*(..))")
    public void logCity() {
    }

    @Around("logCity()")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getName();
        Object result = proceedingJoinPoint.proceed();

        LOGGER.info("User use this controller : {}", className);

        return result;
    }
}
