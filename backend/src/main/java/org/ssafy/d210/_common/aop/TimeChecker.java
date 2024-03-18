package org.ssafy.d210._common.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

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
        log.info("코드 실행 이전");
    }

    @After("execution(* org..service..*(..))")
    public void afterMethod() {
        afterTime = System.currentTimeMillis();
        double timeTaken = (afterTime - beforeTime)/1000.0;

        if(timeTaken < 10){
            log.info("코드 실행 시간은? {}초", timeTaken);
        } else if (timeTaken <20) {
            log.warn("코드 실행 시간은? {}초", timeTaken);
        } else  {
            log.error("코드 실행 시간은? {}초",timeTaken);
        }
    }


}
