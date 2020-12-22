package com.lizheng.config.redis.dataInit;

import com.lizheng.bean.po.User;
import com.lizheng.config.redis.RedisUtil;
import com.lizheng.constants.RedisConstants;
import com.lizheng.mapper.db1.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserInit {

    @Resource
    private UserMapper userMapper;

    @Resource
    RedisUtil redisUtil;

    @PostConstruct
    public void init(){
        List<User> users = userMapper.selectAllUser();
        List<Long> ids = users.stream().map(User::getId).collect(Collectors.toList());
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        //初始化用户id
        redisUtil.setBits(RedisConstants.REDIS_USER_ID,ids);
        //初始化用户姓名
        redisUtil.updateBloom(RedisConstants.REDIS_USER_NAME,names);
    }
}