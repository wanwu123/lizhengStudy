package com.lizheng.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AnnotationAop {

    @Pointcut(value = "@annotation(com.lizheng.annotation.TestAopAnnotation)")
    public void testAnotaion() {

    }

    /**
     * SQL注入处理器，与 @Like 注解联动，放置like语句发生SQL注入问题
     */
    @Around("testAnotaion()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("AnnotationAop");
        Object proceed = pjp.proceed();
        return proceed ;
    }
}
