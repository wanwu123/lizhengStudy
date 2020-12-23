package com.lizheng.service.schedule.userBloomFilterDate;


import com.lizheng.bean.po.User;
import com.lizheng.config.redis.RedisUtil;
import com.lizheng.constants.RedisConstants;
import com.lizheng.mapper.db1.UserMapper;
import com.lizheng.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Tuple;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RedisStatusManager {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void deleteCaseSet(){
        log.info("RedisStatusManager update start");
        Set<Tuple> result;
        boolean flag = true;
        while (flag){
            //Filter expired record
            result = redisUtil.zrangeWithScores("test", 0, 0);
            Tuple tuple = result.stream()
                    .filter(e -> e != null && System.currentTimeMillis() >= e.getScore())
                    .findFirst().orElse(null);
            if (tuple != null) {
                String element = tuple.getElement();
                //Remove from set if expired
                userService.up(Long.parseLong(element));
                redisUtil.zrem("test", element);
            }else {
                flag = false;
            }
        }
        log.info("RedisStatusManager update end");
    }
}
