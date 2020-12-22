package com.lizheng.config.redis;

import redis.clients.jedis.Jedis;

public interface RedisAction<T> {
    T action(Jedis jedis);
}