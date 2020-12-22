package com.lizheng.mybatis;

import com.lizheng.annotation.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxyHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Select annotation = method.getAnnotation(Select.class);
        String sql = annotation.value();
        return sql + args[0];
    }
}
