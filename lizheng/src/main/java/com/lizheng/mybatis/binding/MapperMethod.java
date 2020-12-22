package com.lizheng.mybatis.binding;

public class MapperMethod<T> {

    private String sql;//  select * from blog where id = %d
    private Class<T> type;//bat.ke.qq.com.Blog

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
