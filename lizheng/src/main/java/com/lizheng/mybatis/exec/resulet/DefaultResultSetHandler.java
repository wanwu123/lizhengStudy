package com.lizheng.mybatis.exec.resulet;


import com.lizheng.mybatis.binding.MapperMethod;
import com.lizheng.util.StringUtils;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultResultSetHandler {

    public   <T> T handle(PreparedStatement pstmt, MapperMethod mapperMethod, Object o) throws Exception{
        Object resultObj = new DefaultObjectFactory().create(mapperMethod.getType());
        ResultSet rs = pstmt.getResultSet();
        if (rs.next()) {
            int i = 0;
            for (Field field : resultObj.getClass().getDeclaredFields()) {
                setValue(resultObj, field, rs ,i);
            }
        }
        return (T) resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs, int i) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field,rs));
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(StringUtils.camelToUnderline(field.getName()));
        }
        if(String.class == type){
            return rs.getString(StringUtils.camelToUnderline(field.getName()));
        }
        if(Long.class==type){
            return  rs.getLong(StringUtils.camelToUnderline(field.getName()));
        }
        return rs.getString(StringUtils.camelToUnderline(field.getName()));
    }


}
