package com.lizheng.mybatis.exec.statement;




import com.lizheng.mybatis.binding.MapperMethod;
import com.lizheng.mybatis.exec.resulet.DefaultResultSetHandler;
import com.lizheng.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;


/***
 * 数据里链接
 * JDBC
 *
 */
public class StatementHandler {

    DefaultResultSetHandler resultSetHandler;

    public StatementHandler() {
        this.resultSetHandler = new DefaultResultSetHandler();
    }

    public <T> T query(MapperMethod mapperMethod, Object parameter) throws Exception {
        Connection connection = DbUtil.open();
        String sql = String.format(mapperMethod.getSql(), Integer.valueOf((String) parameter));
        System.out.println(sql+"======");
        PreparedStatement preparedStatement =  connection.prepareStatement(sql);

        preparedStatement.execute();

        return resultSetHandler.handle(preparedStatement, mapperMethod, parameter);


    }


}
