package com.lizheng.mybatis.session;


import com.lizheng.mybatis.binding.MapperMethod;
import com.lizheng.mybatis.binding.MapperProxy;
import com.lizheng.mybatis.exec.Executor;

import java.lang.reflect.Proxy;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }
    @Override
    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type},new MapperProxy<>(this,type));
    }
    @Override
    public <T> T selectOne(MapperMethod mapperMethod, Object parameter) {
        return executor.query(mapperMethod,parameter);
    }

    public  Configuration getConfiguration(){
        return configuration;
    }
}
