package com.lizheng.bean.po;


import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;
    private String orderId;
    private Date cTime;
    private Date uTime;
}
