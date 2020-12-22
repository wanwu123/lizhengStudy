package com.lizheng.test.redis;

import redis.clients.jedis.JedisPubSub;

public class test2 {
    public static void main(String[] args) throws Exception {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("onMessage" + "---" + channel + ":" + message);
            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("onSubscribe" + "---" + channel + ":" + subscribedChannels);

            }
        };

    }
}
