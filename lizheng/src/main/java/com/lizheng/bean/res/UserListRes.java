package com.lizheng.bean.res;

import lombok.Data;

/**
 * @author lizheng
 */
@Data
public class UserListRes {


    /**
     * 骑手id
     */
    private Long riderId;

    /**
     * 企业名称
     */
    private String company;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 骑手姓名
     */
    private String riderName;

    private String qrCode;


}