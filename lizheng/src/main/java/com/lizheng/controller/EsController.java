package com.lizheng.controller;


import com.lizheng.annotation.TestAopAnnotation;
import com.lizheng.bean.po.User;
import com.lizheng.mapper.es.UserEs;
import org.checkerframework.checker.units.qual.A;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("es")
public class EsController {

    @Autowired
    private UserEs userEs;

    @TestAopAnnotation
    @GetMapping("c")
    public Object c(User user)
    {
        userEs.save(user);
        return user;
    }

    @TestAopAnnotation
    @GetMapping("r")
    public Object r(User user)
    {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "lizheng");
        Iterable<User> all = userEs.findAll();
        return all;
    }

    @TestAopAnnotation
    @GetMapping("u")
    public Object u(User user)
    {
        userEs.deleteById(user.getId());
        userEs.save(user);
        return user;
    }

    @TestAopAnnotation
    @GetMapping("d")
    public Object d(User user)
    {
        userEs.deleteById(user.getId());
        return user;
    }

}
