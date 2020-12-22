package com.lizheng.config.redis;




import com.lizheng.config.bloomfilter.DistributedBloomFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Redis Common Template
 * @author bohaiguang
 */
public class RedisUtil {
    @Resource
    private DistributedBloomFilter distributedBloomFilter;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private static final Long RELEASE_SUCCESS = 1L;
    private static String RELASE_LOCK_WITH_EXPECTED_VALUE = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
            "return redis.call('del', KEYS[1]) else return 0 end";

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public <T> T execute(RedisAction<T> redisAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return redisAction.action(jedis);
        } catch (JedisException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean tryLock(final String lockKey){
        String set = set(lockKey, lockKey, "NX", "PX", 10000);
        if ("OK".equals(set)){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateBloomId(final String key,List<Long> ids){
        // 使用Redis批处理方式获取用户信息
        del(key);
        return execute(jedis -> {
            jedis.pipelined();
            // 管道
            Pipeline pipelined = jedis.pipelined();
            for (Long offset : ids) {
                pipelined.setbit(key, offset,true);
            }
            pipelined.sync();
            return true;
        });
    }


    public boolean updateBloom(final String key,List<String> ids){
        // 使用Redis批处理方式获取用户信息
        del(key);
        for (String userId : ids) {
            distributedBloomFilter.put(userId,key);
        }
        return true;
    }


    public Boolean setBits(final String key,List<Long> offsets){
// 使用Redis批处理方式获取用户信息
        return execute(jedis -> {
            jedis.pipelined();
            // 管道
            Pipeline pipelined = jedis.pipelined();
            for (Long offset : offsets) {
                pipelined.setbit(key, offset,true);
            }
            pipelined.sync();
            return true;
        });
    }


    public List<Boolean> getBits(final String key,List<Long> offsets){
        // 使用Redis批处理方式获取用户信息
        return execute(jedis -> {
            jedis.pipelined();
            // 管道
            Pipeline pipelined = jedis.pipelined();
            for (Long offset : offsets) {
                pipelined.getbit(key, offset);
            }
            List<Object> objects = pipelined.syncAndReturnAll();
            List<Boolean> collect = objects.stream().map(o -> Boolean.parseBoolean(o.toString())).collect(Collectors.toList());
            return collect;
        });
    }

    public String set(final String key, final String value) {
        return execute(jedis -> jedis.set(key, value));
    }

    public String set(final String key, final String value, final String nxxx, final String expx, final long time) {
        return execute(jedis -> jedis.set(key, value, nxxx, expx, time));
    }

    public String get(final String key) {
        return execute(jedis -> jedis.get(key));
    }

    public Boolean exists(final String key) {
        return execute(jedis -> jedis.exists(key));
    }

    public Long persist(final String key) {
        return execute(jedis -> jedis.persist(key));
    }

    public String type(final String key) {
        return execute(jedis -> jedis.type(key));
    }

    public Long expire(final String key, final int seconds) {
        return execute(jedis -> jedis.expire(key, seconds));
    }

    public Long expireAt(final String key, final long unixTime) {
        return execute(jedis -> jedis.expireAt(key, unixTime));
    }

    public Long ttl(final String key) {
        return execute(jedis -> jedis.ttl(key));
    }

    public Boolean setbit(final String key, final long offset, final boolean value) {
        return execute(jedis -> jedis.setbit(key, offset, value));
    }

    public Boolean setbit(final String key, final long offset, final String value) {
        return execute(jedis -> jedis.setbit(key, offset, value));
    }

    public Boolean getbit(final String key, final long offset) {
        return execute(jedis -> jedis.getbit(key, offset));
    }

    public Long setrange(final String key, final long offset, final String value) {
        return execute(jedis -> jedis.setrange(key, offset, value));
    }

    public String getrange(final String key, final long startOffset, final long endOffset) {
        return execute(jedis -> jedis.getrange(key, startOffset, endOffset));
    }

    public String getSet(final String key, final String value) {
        return execute(jedis -> jedis.getSet(key, value));
    }

    public Long setnx(final String key, final String value) {
        return execute(jedis -> jedis.setnx(key, value));
    }

    public String setnx(final String key, final String value, final long milliseconds) {
        return execute(jedis -> jedis.set(key, value, "NX", "PX", milliseconds));
    }

    public String setex(final String key, final int seconds, final String value) {
        return execute(jedis -> jedis.setex(key, seconds, value));
    }

    public Long decrBy(final String key, final long integer) {
        return execute(jedis -> jedis.decrBy(key, integer));
    }

    public Long decr(final String key) {
        return execute(jedis -> jedis.decr(key));
    }

    public Long incrBy(final String key, final long integer) {
        return execute(jedis -> jedis.incrBy(key, integer));
    }

    public Long incr(final String key) {
        return execute(jedis -> jedis.incr(key));
    }

    public Long append(final String key, final String value) {
        return execute(jedis -> jedis.append(key, value));
    }

    public String substr(final String key, final int start, final int end) {
        return execute(jedis -> jedis.substr(key, start, end));
    }

    public Long hset(final String key, final String field, final String value) {
        return execute(jedis -> jedis.hset(key, field, value));
    }

    public String hget(final String key, final String field) {
        return execute(jedis -> jedis.hget(key, field));
    }

    public Long hsetnx(final String key, final String field, final String value) {
        return execute(jedis -> jedis.hsetnx(key, field, value));
    }

    public String hmset(final String key, final Map<String, String> hash) {
        return execute(jedis -> jedis.hmset(key, hash));
    }

    public List<String> hmget(final String key, final String... fields) {
        return execute(jedis -> jedis.hmget(key, fields));
    }

    public Long hincrBy(final String key, final String field, final long value) {
        return execute(jedis -> jedis.hincrBy(key, field, value));
    }

    public Boolean hexists(final String key, final String field) {
        return execute(jedis -> jedis.hexists(key, field));
    }

    public Long hdel(final String key, final String... field) {
        return execute(jedis -> jedis.hdel(key, field));
    }

    public Long hlen(final String key) {
        return execute(jedis -> jedis.hlen(key));
    }

    public Set<String> hkeys(final String key) {
        return execute(jedis -> jedis.hkeys(key));
    }

    public List<String> hvals(final String key) {
        return execute(jedis -> jedis.hvals(key));
    }

    public Map<String, String> hgetAll(final String key) {
        return execute(jedis -> jedis.hgetAll(key));
    }

    public Long rpush(final String key, final String... string) {
        return execute(jedis -> jedis.rpush(key, string));
    }

    public Long lpush(final String key, final String... string) {
        return execute(jedis -> jedis.lpush(key, string));
    }

    public Long llen(final String key) {
        return execute(jedis -> jedis.llen(key));
    }

    public List<String> lrange(final String key, final long start, final long end) {
        return execute(jedis -> jedis.lrange(key, start, end));
    }

    public String ltrim(final String key, final long start, final long end) {
        return execute(jedis -> jedis.ltrim(key, start, end));
    }

    public String lindex(final String key, final long index) {
        return execute(jedis -> jedis.lindex(key, index));
    }

    public String lset(final String key, final long index, final String value) {
        return execute(jedis -> jedis.lset(key, index, value));
    }

    public Long lrem(final String key, final long count, final String value) {
        return execute(jedis -> jedis.lrem(key, count, value));
    }

    public String lpop(final String key) {
        return execute(jedis -> jedis.lpop(key));
    }

    public String rpop(final String key) {
        return execute(jedis -> jedis.rpop(key));
    }

    public Long sadd(final String key, final String... member) {
        return execute(jedis -> jedis.sadd(key, member));
    }

    public Set<String> smembers(final String key) {
        return execute(jedis -> jedis.smembers(key));
    }

    public Long srem(final String key, final String... member) {
        return execute(jedis -> jedis.srem(key, member));
    }

    public String spop(final String key) {
        return execute(jedis -> jedis.spop(key));
    }

    public Long scard(final String key) {
        return execute(jedis -> jedis.scard(key));
    }

    public Boolean sismember(final String key, final String member) {
        return execute(jedis -> jedis.sismember(key, member));
    }

    public String srandmember(final String key) {
        return execute(jedis -> jedis.srandmember(key));
    }

    public List<String> srandmember(final String key, final int count) {
        return execute(jedis -> jedis.srandmember(key, count));
    }

    public Long strlen(final String key) {
        return execute(jedis -> jedis.strlen(key));
    }

    public Long zadd(final String key, final double score, final String member) {
        return execute(new RedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.zadd(key, score, member);
            }
        });
    }

    public Long zadd(final String key, final Map<String, Double> scoreMembers) {
        return execute(new RedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.zadd(key, scoreMembers);
            }
        });
    }

    public Set<String> zrange(final String key, final long start, final long end) {
        return execute(new RedisAction<Set<String>>() {
            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrange(key, start, end);
            }
        });
    }

    public Long zrem(final String key, final String... member) {
        return execute(new RedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.zrem(key, member);
            }
        });
    }

    public Double zincrby(final String key, final double score, final String member) {
        return execute(jedis -> jedis.zincrby(key, score, member));
    }

    public Long zrank(final String key, final String member) {
        return execute(jedis -> jedis.zrank(key, member));
    }

    public Long zrevrank(final String key, final String member) {
        return execute(jedis -> jedis.zrevrank(key, member));
    }

    public Set<String> zrevrange(final String key, final long start, final long end) {
        return execute(jedis -> jedis.zrevrange(key, start, end));
    }

    public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
        return execute(jedis -> jedis.zrangeWithScores(key, start, end));
    }

    public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
        return execute(jedis -> jedis.zrevrangeWithScores(key, start, end));
    }

    public Long zcard(final String key) {
        return execute(jedis -> jedis.zcard(key));
    }

    public Double zscore(final String key, final String member) {
        return execute(jedis -> jedis.zscore(key, member));
    }

    public List<String> sort(final String key) {
        return execute(jedis -> jedis.sort(key));
    }

    public List<String> sort(final String key, final SortingParams sortingParameters) {
        return execute(jedis -> jedis.sort(key, sortingParameters));
    }

    public Long zcount(final String key, final double min, final double max) {
        return execute(jedis -> jedis.zcount(key, min, max));
    }

    public Long zcount(final String key, final String min, final String max) {
        return execute(jedis -> jedis.zcount(key, min, max));
    }

    public Long zremrangeByRank(final String key, final long start, final long end) {
        return execute(jedis -> jedis.zremrangeByRank(key, start, end));
    }

    public Long zremrangeByScore(final String key, final double start, final double end) {
        return execute(jedis -> jedis.zremrangeByScore(key, start, end));
    }

    public Long zremrangeByScore(final String key, final String start, final String end) {
        return execute(jedis -> jedis.zremrangeByScore(key, start, end));
    }

    public Long zlexcount(final String key, final String min, final String max) {
        return execute(jedis -> jedis.zlexcount(key, min, max));
    }

    public Set<String> zrangeByLex(final String key, final String min, final String max) {
        return execute(jedis -> jedis.zrangeByLex(key, min, max));
    }

    public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset, final int count) {
        return execute(jedis -> jedis.zrangeByLex(key, min, max, offset, count));
    }

    public Long zremrangeByLex(final String key, final String min, final String max) {
        return execute(jedis -> jedis.zremrangeByLex(key, min, max));
    }

    public Long linsert(final String key, final BinaryClient.LIST_POSITION where, final String pivot, final String value) {
        return execute(jedis -> jedis.linsert(key, where, pivot, value));
    }

    public Long lpushx(final String key, final String... string) {
        return execute(jedis -> jedis.lpushx(key, string));
    }

    public Long rpushx(final String key, final String... string) {
        return execute(jedis -> jedis.rpushx(key, string));
    }

    public List<String> blpop(final int timeout, final String key) {
        return execute(new RedisAction<List<String>>() {
            @Override
            public List<String> action(Jedis jedis) {
                return jedis.blpop(timeout, key);
            }
        });
    }

    public List<String> brpop(final int timeout, final String key) {
        return execute(jedis -> jedis.brpop(timeout, key));
    }

    public Long del(final String key) {
        return execute(jedis -> jedis.del(key));
    }

    public String echo(final String string) {
        return execute(jedis -> jedis.echo(string));
    }

    public Long move(final String key, final int dbIndex) {
        return execute(jedis -> jedis.move(key, dbIndex));
    }

    public Long bitcount(final String key) {
        return execute(jedis -> jedis.bitcount(key));
    }

    public Long bitcount(final String key, final long start, final long end) {
        return execute(jedis -> jedis.bitcount(key, start, end));
    }

    public Long pfadd(final String key, final String... elements) {
        return execute(jedis -> jedis.pfadd(key, elements));
    }

    public long pfcount(final String key) {
        return execute(jedis -> jedis.pfcount(key));
    }

    public String releaseLock(final String key, final String expectedV) {
        return execute(jedis -> {
            Object result = jedis.eval(RELASE_LOCK_WITH_EXPECTED_VALUE,
                    Collections.singletonList(key), Collections.singletonList(expectedV));
            if (RELEASE_SUCCESS.equals(result)) {
                return "OK";
            }
            return "";
        });
    }
    public Long  piplpush(final String key, final List<String> data) {
        return execute(jedis -> {
            Pipeline pipelined = jedis.pipelined();
            for(String s:data) {
                Response<Long> key1 = pipelined.lpush("key", s);
            }
            try {
                pipelined.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }


}
