package com.salpreh.aopex.aspects;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
public class LogTimeAspect {

    @Pointcut("@annotation(com.salpreh.aopex.annotations.LogTime)")
    public void logTime() {}

    @Around("logTime() && execution(* *(..))")
    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
        Instant startTs = Instant.now();
        Object r = pjp.proceed();

        log.info("Execution time of {}: {}s", pjp.getSignature().getName(),
                Duration.between(startTs, Instant.now()).toMillis() / 1000.0);

        return r;
    }
}
