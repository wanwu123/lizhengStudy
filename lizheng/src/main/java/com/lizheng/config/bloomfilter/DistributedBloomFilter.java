package com.lizheng.config.bloomfilter;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import com.lizheng.config.redis.RedisUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("bloom.filter")
@Component
public class DistributedBloomFilter {

    //预计插入量
    private long expectedInsertions;

    //可接受的错误率
    private double fpp;

    @Resource
    private RedisUtil redisUtil;


    //bit数组长度
    private long numBits;
    //hash函数数量
    private int numHashFunctions ;

    public long getExpectedInsertions() {
        return expectedInsertions;
    }

    public void setExpectedInsertions(long expectedInsertions) {
        this.expectedInsertions = expectedInsertions;
    }

    public void setFpp(double fpp) {
        this.fpp = fpp;
    }

    public double getFpp() {
        return fpp;
    }

    @PostConstruct
    public void init(){
        this.numBits = optimalNumOfBits(expectedInsertions, fpp);
        this.numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
    }

    //计算hash函数个数
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    //计算bit数组长度
    private long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * 判断keys是否存在于集合  是返回true  否则返回false
     */
    public boolean isExist(String key,String redisKey) {
        List<Long> indexs = getIndexs(key);
        /*for (long index : indexs) {
            Boolean getbit = redisUtil.getbit(redisKey, index);
            if (!getbit){
                return false;
            }
        }
        return true;*/
        List<Boolean> bits = redisUtil.getBits(redisKey, indexs);
        return !bits.contains(false);
    }

    /**
     * 将key存入redis bitmap
     */
    public void put(String key,String redisKey) {
        List<Long> indexs = getIndexs(key);
        /*for (long index : indexs) {
            redisUtil.setbit(redisKey,index,true);
        }*/
        redisUtil.setBits(redisKey,indexs);
    }

    /**
     * 根据key获取bitmap下标      一个hash函数对   20+1  20+2
     */
    private List<Long> getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        List<Long> result = new ArrayList<>(numHashFunctions);
        for (int i = 0; i < numHashFunctions; i++) {  //numHashFunctions  hash函数的数量
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result.add(combinedHash % numBits);
        }
        return result;
    }

    /**
     * 获取一个hash值
     */
    private long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }
}
