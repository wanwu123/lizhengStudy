package com.lizheng.bean.po;

/**
 * @author didi
 */

public enum ErrorCode {
    FAILED_REQUEST(500, "调用失败"),

    /**
     * 状态码信息
     */
    SUCCESS_REQUEST(0, "调用成功")

;

    private final int code;
    private String desc;

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
