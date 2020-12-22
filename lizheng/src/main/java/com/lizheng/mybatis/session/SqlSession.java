package com.lizheng.mybatis.session;


import com.lizheng.mybatis.binding.MapperMethod;

public interface SqlSession {


    /**
     * Retrieve a single row mapped from the statement key.
     * @param <T> the returned object type
     * @param statement
     * @return Mapped object
     */
    <T> T selectOne(MapperMethod mapperMethod, Object parameter);

    public <T> T getMapper(Class<T> type);


}
