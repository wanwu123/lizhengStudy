package com.lizheng.service.impl;

import com.lizheng.mapper.db1.OrderMapper;
import com.lizheng.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void insertOrder() {
        orderMapper.insertOrder();
    }
}
