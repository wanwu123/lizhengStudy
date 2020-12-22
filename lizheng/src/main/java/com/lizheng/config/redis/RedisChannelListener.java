package com.lizheng.config.redis;

import com.lizheng.config.cache.LocalCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RedisChannelListener implements MessageListener {

    @Resource
    private LocalCache localCache;

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        log.info("sub message :) channel[cleanNoStockCache] !");
        String productId = new String(message.getBody(), StandardCharsets.UTF_8);
        localCache.remove("miaosha:stock:cache:" + productId);
    }

}
