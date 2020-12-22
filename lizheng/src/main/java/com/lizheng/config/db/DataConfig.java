package com.lizheng.config.db;


import com.alibaba.druid.pool.DruidDataSource;
import com.lizheng.mybatisInceptor.TimeIncepter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataConfig.PACKAGE+".db1",sqlSessionFactoryRef = "lizhengFactory")
public class DataConfig {

    static final String PACKAGE = "com.lizheng.mapper";

    static final String MAPPER_LOCATION_DB1 = "classpath:mapperXml/db1/*.xml";



    @Bean("db1")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/xxx?useAffectedRows=true&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }






    @Primary
    @Bean("lizhengFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION_DB1));
        sessionFactory.setPlugins(new TimeIncepter());
        return sessionFactory.getObject();
    }

//    @Bean("dbmg1")
//    @Primary
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }

}
