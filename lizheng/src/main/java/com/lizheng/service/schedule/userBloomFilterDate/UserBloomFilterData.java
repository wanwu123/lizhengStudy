package com.lizheng.service.schedule.userBloomFilterDate;


import com.lizheng.bean.po.User;
import com.lizheng.config.redis.RedisUtil;
import com.lizheng.constants.RedisConstants;
import com.lizheng.mapper.db1.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserBloomFilterData {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    @Scheduled(cron = "0 * * * * ? ")
    public void deleteCaseSet(){
        log.info("user:bloomfilter update start");
        List<User> jaguarUsers = userMapper.selectAllUser();
        List<Long> ids = jaguarUsers.stream().map(User::getId).collect(Collectors.toList());
        List<String> names = jaguarUsers.stream().map(User::getName).collect(Collectors.toList());
        redisUtil.updateBloom(RedisConstants.REDIS_USER_NAME,names);
        redisUtil.updateBloomId(RedisConstants.REDIS_USER_ID,ids);
        log.info("user:bloomfilter update end");
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal(2).subtract(null));
    }

}

