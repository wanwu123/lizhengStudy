package com.lizheng.mybatis.session;


import java.io.IOException;
import java.io.InputStream;


public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setInputStream(inputStream);
        configuration.loadConfigurations();
        return new SqlSessionFactory(configuration);
    }


}
