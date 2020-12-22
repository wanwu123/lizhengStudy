package com.lizheng.mybatis;

import java.lang.reflect.Proxy;

public class Sqlsession {

    public <T> T getMapper(Class<T> tClass){
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{tClass}, new MapperProxyHandler());
        return (T)o;
    }
}
