package com.lizheng.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.lizheng.mybatisInceptor.TimeIncepter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = DataConfig.PACKAGE+".db2",sqlSessionFactoryRef = "lizhengFactory2")
public class DataCofing2 {
    static final String MAPPER_LOCATION_DB2 = "classpath:mapperXml/db2/*.xml";

    @Bean("db2")
    public DataSource dataSource2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/xxx?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean("lizhengFactory2")
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource2());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION_DB2));
        sessionFactory.setPlugins(new TimeIncepter());
        return sessionFactory.getObject();
    }

    @Bean("dbmg2")
    public DataSourceTransactionManager transactionManager2() {
        return new DataSourceTransactionManager(dataSource2());
    }
}
