package com.lizheng.mybatisInceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;



@Slf4j
@Intercepts({@Signature(type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class})})
public class TimeIncepter implements Interceptor {

    // 拦截器逻辑
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        //反射工具
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //mybatis反射调用
        String sqlId = metaObject.getValue("delegate.mappedStatement.id").toString();
        long start = System.currentTimeMillis();
        String sql = statementHandler.getBoundSql().getSql();
        Object proceed = invocation.proceed();
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        System.out.println(parameterHandler.getParameterObject());
        long end = System.currentTimeMillis();
        log.info("sqlId={}||sql={}||time={}",sqlId,sql,end-start);
        return proceed;
    }

    //放入配置文件
    @Override
    public void setProperties(Properties properties) {

    }

    //返回一个动态代理后的对象 target = StatementHandler
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
