package jp.acerstech.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ApplicationLogger {


    @Around("execution(* jp.acerstech.demo.controller.*.*(..))")
    public Object logApplication(ProceedingJoinPoint joinPoint) throws  Throwable {

        StopWatch  stopWatch = new StopWatch();
        stopWatch.start();
        Object result;

        try{
            result = joinPoint.proceed();
        }finally {
            stopWatch.stop();
            Signature signature = joinPoint.getSignature();
            log.info("{}.{}  completed in {} ms",
                    signature.getDeclaringTypeName(),
                    signature.getName(),
                    stopWatch.getTotalTimeMillis());
        }
        return result;
    }

}
