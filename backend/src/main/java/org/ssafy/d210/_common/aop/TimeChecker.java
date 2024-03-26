package org.ssafy.d210._common.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // 해당 클래스가 Aspect임을 나타냄 Aspect = 자신이 실행될 위치(pointcut)을 알고 있는 부가기능(Advice) 클래스를 말함.
@Component
@RequiredArgsConstructor
@Slf4j

// 1. Service의 실행 속도를 체크하는 Time Checker
public class TimeChecker {
    private double beforeTime = 0L;
    private double afterTime = 0L;

    @Before("execution(* org..service..*(..))")
    public void beforeMethod() {
        beforeTime = System.currentTimeMillis();
        log.info("✧･ﾟ: *✧･ﾟ:* 코드 실행 이전 *:･ﾟ✧*:･ﾟ✧");
    }

    @After("execution(* org..service..*(..))")
    public void afterMethod(final JoinPoint joinPoint) {

        afterTime = System.currentTimeMillis();
        double timeTaken = (afterTime - beforeTime)/1000.0;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();


        log.info("현재 시각: {} ☆～（ゝ。∂）", afterTime);
        if(timeTaken < 10){
            log.info("✧･ﾟ: *✧･ﾟ:* {} 서비스 처리 성공 in {} sec *:･ﾟ✧*:･ﾟ✧", method.getName(), timeTaken);
        } else {
            log.warn("✧･ﾟ: *✧･ﾟ:* {} 서비스 처리 성공 in {} sec  *:･ﾟ✧*:･ﾟ✧", method.getName(), timeTaken);
        }
    }


}
