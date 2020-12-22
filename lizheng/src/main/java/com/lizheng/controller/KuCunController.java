package com.lizheng.controller;


import com.lizheng.bean.result.ResponsePo;
import com.lizheng.service.KuCunService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/kuCun")
public class KuCunController {

    @Resource
    private KuCunService kuCunService;

    @GetMapping(value = "/kucun1")
    public ResponsePo<Object> kucun1(@RequestParam("Id")Long id) {
       return kuCunService.kucun1(id);
    }

    @GetMapping(value = "/kucun2")
    public ResponsePo<Object> kucun2(@RequestParam("Id")Long id,@RequestParam("num")Integer num) {
        return kuCunService.kucun2(id,num);
    }

}
