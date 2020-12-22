package com.lizheng.bean.po;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User {
    private Long id;
    @NotNull(message = "name空")
    private String name;
    private String age;
    private String qrCode;
}
