package com.lizheng.aspect.aopStudy;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class ProxyStudy {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new AopService());
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setInterfaces(AopInterface.class);
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("bf");
            }
        });
        AopInterface proxy = (AopInterface)proxyFactory.getProxy();
        proxy.test();
    }
}
