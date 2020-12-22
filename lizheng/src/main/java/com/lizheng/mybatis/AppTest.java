package com.lizheng.mybatis;


import com.lizheng.bean.po.User;
import com.lizheng.mapper.db1.UserMapper;
import com.lizheng.mybatis.session.SqlSession;
import com.lizheng.mybatis.session.SqlSessionFactory;
import com.lizheng.mybatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * Unit test for simple App.
 */

public class AppTest
{
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User blog = userMapper.getUser(1L);
        System.out.println(blog);


    }
}
