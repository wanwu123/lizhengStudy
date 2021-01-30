package com.lizheng.bean.po;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Order implements Cloneable{
    private Long id;
    private String orderId;
    private Date cTime;
    private Date uTime;

    @Override
    protected Order clone() throws CloneNotSupportedException {
        return (Order)super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Order order = null;
        Order order1 = Optional.of(order).orElse(null);
        order.setId(1L);
    }
}
