package kr.chis.cismsaorder.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author InSeok
 * Date : 2020-07-22
 * Remark : AOP를 사용하여 Controller 클래스의 로그를 남기는 클래스
 */
//@Component
@Slf4j
//@Aspect
public class RequestLoggingAspect {


    @Around("execution(* kr.chis.cismsaorder..*Controller.*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {


        long begin = System.currentTimeMillis();
        var retVal = pjp.proceed(); // 메서드 호출 자체를 감쌈
        long durationtime = System.currentTimeMillis() - begin;

        log.info("result String {} " ,retVal.toString());
        log.info("AOP Log:{}ms" ,durationtime );
        log.info("Get Signature : {} ",pjp.getSignature().getName());
        log.info("Class : {} " , pjp.getTarget().getClass().getName());
        log.info("Args : {} " , pjp.getArgs());




        return retVal;

    }
}
