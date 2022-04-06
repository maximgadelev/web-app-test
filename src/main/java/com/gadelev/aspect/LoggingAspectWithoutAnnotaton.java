package com.gadelev.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class LoggingAspectWithoutAnnotaton {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspectWithoutAnnotaton.class);

    @Pointcut("execution(* com.gadelev..*.*.*(..))")
    public void logByTime() {
    }

    @Around("logByTime()")
    public Object logAllMethodsForTIme(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getName();
        Object result = proceedingJoinPoint.proceed();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        LOGGER.info("User use this controller : {} in {}", className,date);

        return result;
    }
}
