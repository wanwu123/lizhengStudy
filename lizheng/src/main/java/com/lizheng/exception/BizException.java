package com.lizheng.exception;


public class BizException extends RuntimeException {
    private static final long serialVersionUID = 3583566093089790852L;

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

}
