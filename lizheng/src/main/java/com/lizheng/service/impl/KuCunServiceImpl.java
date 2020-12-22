package com.lizheng.service.impl;

import com.lizheng.bean.po.KuCun;
import com.lizheng.bean.result.ResponsePo;
import com.lizheng.mapper.db1.KuCunMapper;
import com.lizheng.service.KuCunService;
import com.lizheng.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class KuCunServiceImpl implements KuCunService {
    @Resource
    private KuCunMapper kuCunMapper;

    @Resource
    private OrderService orderService;

    @Override
    public ResponsePo<Object> kucun2(Long id, Integer num) {
        ResponsePo<Object> responsePo = new ResponsePo<>();
        int a = kuCunMapper.update2(id,num);
        if (a == 0){
            return responsePo.create("下单error",a);
        }
        return responsePo.create("下单成功",a);
    }

    @Override
    public ResponsePo<Object> kucun1(Long id) {
        ResponsePo<Object> responsePo = new ResponsePo<>();
        KuCun kuCun = kuCunMapper.selectById(id);
        Integer num = kuCun.getNum();
        if (num<1){
            return responsePo.create("库存不足",num);
        }
        kuCunMapper.count_one(id);
        orderService.insertOrder();
        return responsePo.create("库存成功减去剩余",num-1);
    }
}
