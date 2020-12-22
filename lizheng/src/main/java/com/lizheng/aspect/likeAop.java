package com.lizheng.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

/**
 * controller 入参处理切面
 */
@Component
@Aspect
@Slf4j
public class likeAop {

    @Pointcut(value = "execution(* com.lizheng.controller..*(..))")
    public void logPointcut() {
    }

    /**
     * SQL注入处理器，与 @Like 注解联动，放置like语句发生SQL注入问题
     */
    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long l = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long l1 = System.currentTimeMillis();
        log.info("excute==>"+(l1-l));

        /*Object[] args = pjp.getArgs();
        Parameter[] parameters = ((MethodSignature) pjp.getSignature()).getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            if (arg == null) {// 不需要处理 null 参数
                continue;
            }
            if (parameter.getType() == String.class) {// 如果参数是 String 类型
                if (parameter.isAnnotationPresent(Like.class)) {
                    args[i] = ((String) arg).replace("%", "\\%").replace("_", "\\_");
                }
            } else {// 如果参数是其他 Object 类型, 目前暂不支持对 @Like 更深层次的遍历，深层次遍历注意处理循环依赖问题
                for (Field field : arg.getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(Like.class) && field.getType() == String.class) {
                        field.setAccessible(true);
                        try {
                            String str = (String) field.get(arg);
                            if (str != null) {
                                field.set(arg, str.replace("%", "\\%").replace("_", "\\_"));
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }*/
        return proceed ;
    }

}