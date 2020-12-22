package com.lizheng.config.bloomfilter;

import com.lizheng.bean.po.User;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.lizheng.mapper.db1.UserMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lizheng
 */
//@Component
public class MyBloomFilter {
    @Resource
    private UserMapper userMapper;

    public BloomFilter<Long> bloomFilter;

//    @PostConstruct
    public void init(){
        List<User> jaguarUsers = userMapper.selectAllUser();
        List<Long> ids = jaguarUsers.stream().map(User::getId).collect(Collectors.toList());
        bloomFilter = BloomFilter
                .create(Funnels.longFunnel(), ids.size(), 0.001);
        for (Long id : ids) {
            bloomFilter.put(id);
        }
    }
}
