package com.lizheng.mybatis.session;


import com.lizheng.mybatis.exec.Executor;
import com.lizheng.mybatis.exec.SimpleExecutor;

public class SqlSessionFactory {
    Configuration configuration;

    SqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
        Executor executor = new SimpleExecutor();
        return new DefaultSqlSession(configuration, executor);


    }


}
