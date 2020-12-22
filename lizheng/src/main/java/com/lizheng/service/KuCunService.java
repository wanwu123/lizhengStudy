package com.lizheng.service;

import com.lizheng.bean.result.ResponsePo;

public interface KuCunService {
    ResponsePo<Object> kucun1(Long id);

    ResponsePo<Object> kucun2(Long id, Integer num);
}
