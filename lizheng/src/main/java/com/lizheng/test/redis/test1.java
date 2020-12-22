package com.lizheng.test.redis;

import com.lizheng.config.redis.RedisUtil;
import redis.clients.jedis.*;



import java.util.UUID;

public class test1 {

    public static final int a= 10;
    public static  int b= 10;
    public static void main(String[] args) throws Exception {

        /*int i = 16384;
        int aaa = JedisClusterCRC16.getCRC16("aaa");
        int slot = aaa%i;
        System.out.println(slot);
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("127.0.0.1",7000),1000,1000,1000,"lizheng",new JedisPoolConfig());
        jedisCluster.set("aaa","5000");
        String aaa1 = jedisCluster.get("aaa");
        System.out.println(aaa1);
        jedisCluster.close();                             */
        Jedis jedis = new Jedis("127.0.0.1",6379);
       /* SetParams setParams = new SetParams();
        setParams.ex(6);
        setParams.nx();    */
        String s = UUID.randomUUID().toString();
        RedisUtil redisUtil = new RedisUtil();
        jedis.set("locked",s,"NX","PX",6000);
        System.out.println(jedis.get("locked"));
    }


}
