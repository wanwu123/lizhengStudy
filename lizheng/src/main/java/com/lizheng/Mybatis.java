package com.lizheng;


import com.lizheng.bean.po.User;
import com.lizheng.mapper.db1.UserMapper;
import com.lizheng.mybatis.Sqlsession;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Mybatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectUser(1L));
        Sqlsession sqlsession = new Sqlsession();
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        Object user = mapper.selectId(1L);
    }
}
