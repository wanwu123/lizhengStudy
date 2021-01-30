package com.lizheng.bean.po;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(indexName = "testdoct", type = "testbean")
public class User {
    private Long id;
    @NotNull(message = "name空")
    private String name;
    private String age;
    private String qrCode;
}
