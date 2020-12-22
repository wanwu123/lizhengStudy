package com.lizheng.mybatis.exec;


import com.lizheng.mybatis.binding.MapperMethod;
import com.lizheng.mybatis.exec.statement.StatementHandler;

/***
 * 交个 StatementHandler 处理
 */
public class SimpleExecutor implements Executor {


    @Override
    public <T> T query(MapperMethod mapperMethod, Object parameter) {
        StatementHandler statementHandler=new StatementHandler();
        try {
            return statementHandler.query(mapperMethod,parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
