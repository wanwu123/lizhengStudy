package com.lizheng.mybatis.exec;


import com.lizheng.mybatis.binding.MapperMethod;

public interface Executor {

    <T> T query(MapperMethod mapperMethod, Object parameter) ;
}
