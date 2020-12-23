package com.lizheng.controller;


import com.alibaba.fastjson.JSON;
import com.lizheng.annotation.TestAopAnnotation;
import com.lizheng.bean.result.UserTemplete;
import com.lizheng.config.bloomfilter.DistributedBloomFilter;
import com.lizheng.config.redis.RedisUtil;
import com.lizheng.constants.RedisConstants;
import com.lizheng.bean.po.Po;
import com.lizheng.bean.result.ResponsePo;
import com.lizheng.bean.po.User;
import com.lizheng.event.*;
import com.lizheng.mapper.db1.UserMapper;
import com.lizheng.service.UserService;
import com.lizheng.util.SoutUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Unsafe;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RestController
public class aController {
    private static String redisName = "user:";

    @Autowired
    private DistributedBloomFilter distributedBloomFilter;

    @Resource
    UserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    EventRegister eventRegister;

    @Resource
    UserS userS;

    @Value("${randomKey.key}")
    private String key;


    @Resource
    private Po po;

    @Autowired
    private SoutUtil soutUtil;

    @Autowired
    private UserService userService;





    @TestAopAnnotation
    @GetMapping("a")
    public Object a(Long id)
    {
            userService.up(id);
            return key;
    }


    @GetMapping("b")
    public Object b(Long id)
    {
        long time = System.currentTimeMillis() + 3000L;
        redisUtil.zadd("test",time,id.toString());
        return id;
    }


    @GetMapping("n")
    public Object n()
    {
        UserTemplete userTemplete = userMapper.selectJoin();
        return userTemplete;
    }

    @GetMapping("/lizhengStart")
    public Object lizhengStart(){
        return soutUtil.getCofig();
    }

    @GetMapping("/listener")
    public Object getListener(){
        /*EventRegister eventRegister = new EventRegister();
        WebEvent webEvent = new WebEvent(1,new User(),10);
        eventRegister.register(webEvent,new User(),10);*/
        userS.register(new hahaEvent("13121926522",1));
        System.out.println("-----------");
        userS.register(new Event("13121926522",new User(),1));
        aaa a = eventRegister.a();
        System.out.println("12312312312123213123123");
        a.test("66121266");
        return eventRegister.toString();
    }

    /**
     * 缓存穿透
     * @param id
     * @param userName
     * @return
     */
    @GetMapping("/getUser")
    public ResponsePo<Object> getListener(@RequestParam(value = "id",required = false) Long id,
                                          @RequestParam(value = "userName",required = false) String userName){
        ResponsePo<Object> responsePo = new ResponsePo<>();
        if (userName == null){
            String redisUser = redisName.concat(id.toString());
            String userInfo = redisUtil.get(redisUser);
            if (StringUtils.isNotBlank(userInfo)){
                try {
                    User user = JSON.parseObject(userInfo, User.class);
                    return responsePo.create("命中缓存",user);
                }catch (Exception e){
                    return responsePo.create("命中缓存",userInfo);
                }
            }else {
//            boolean get = myBloomFilter.bloomFilter.mightContain(id);
                boolean get = redisUtil.getbit(RedisConstants.REDIS_USER_ID,id);
                if (get){
                    User user = userMapper.selectUser(id);
                    if (user==null){
                        redisUtil.setex(redisUser,10,"查询无果");
                        return responsePo.create("命中数据库","查询无果");
                    }else {
                        redisUtil.setex(redisUser,100,JSON.toJSONString(user));
                        return responsePo.create("命中数据库",user);
                    }
                }else {
                    return responsePo.create("命中布隆过滤器","查询无果");
                }
            }
        }else {
            boolean exist = distributedBloomFilter.isExist(userName, RedisConstants.REDIS_USER_NAME);
            if (exist){
                User user = userMapper.selectUserByName(userName);
                return responsePo.create("命中数据库",user);
            }else{
                return responsePo.create("命中布隆过滤器","查询无果");
            }
        }
    }
    /**
     * 缓存击穿
     * @param id
     * @return
     */
    @GetMapping("/getUserCache")
    public ResponsePo<Object> getUserCache(@RequestParam(value = "id",required = false) Long id) throws Exception{
        ResponsePo<Object> responsePo = new ResponsePo<>();
        String redisUser = redisName.concat(id.toString());
        // 查询缓存
        String userInfo = redisUtil.get(redisUser);
        if (StringUtils.isNotBlank(userInfo)) {
            User user = JSON.parseObject(userInfo, User.class);
            return responsePo.create("命中缓存", user);
        }
        String lockKey = "lock:".concat(redisUser);
        boolean b = redisUtil.tryLock(lockKey);
        if (b){
            // 再次查询缓存
            try {
                if (StringUtils.isNotBlank(redisUtil.get(redisUser))) {
                    User user = JSON.parseObject(userInfo, User.class);
                    return responsePo.create("命中缓存", user);
                } else {
                    boolean get = redisUtil.getbit(RedisConstants.REDIS_USER_ID, id);
                    if (get) {
                        User user = userMapper.selectUser(id);
                        if (user == null) {
                            redisUtil.setex(redisUser, 10, "查询无果");
                            return responsePo.create("命中数据库", "查询无果");
                        } else {
                            redisUtil.setex(redisUser, 100, JSON.toJSONString(user));
                            return responsePo.create("命中数据库", user);
                        }
                    } else {
                        return responsePo.create("命中布隆过滤器", "查询无果");
                    }
                }
            }finally {
                redisUtil.del(lockKey);
            }
        }else {
            Thread.sleep(50);
            return getUserCache(id);
        }
    }

    @Test
    public void test(){
        System.out.println(Integer.highestOneBit(1));
        System.out.println(Integer.highestOneBit(2));
        System.out.println(Integer.highestOneBit(3));
        System.out.println(Integer.highestOneBit(4));
        System.out.println(Integer.highestOneBit(5));
        //highestOneBit 找到小于等于的2的幂次方数字
        System.out.println(Integer.highestOneBit(1<<1)); //2
        System.out.println(Integer.highestOneBit(2<<1)); // 4
        System.out.println(Integer.highestOneBit(3<<1)); // 6
        System.out.println(Integer.highestOneBit(4<<1)); // 8
        System.out.println(Integer.highestOneBit(5<<1)); // 10

        // 取余
        int lenth = 32;
        int num = 2211;
        int lenth2 = 16;
        int num2 = 2142;
        System.out.println(num & (lenth-1));
        System.out.println(num % lenth);
        System.out.println(num2 & (lenth2-1));
        System.out.println(num2 % lenth2);
        HashMap<Integer, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1,1);
        objectObjectHashMap.put(2,2);
        Iterator<Integer> iterator = objectObjectHashMap.keySet().iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next == 1){
//                objectObjectHashMap.remove(next);
                //modlecount 快速失败
                iterator.remove();
            }
        }
        Unsafe unsafe = Unsafe.getUnsafe();
//        unsafe.compareAndSwapInt()
        System.out.println(objectObjectHashMap.get(null));
    }

}
