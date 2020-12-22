package com.lizheng.bean.result;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.lizheng.bean.po.ErrorCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTemplate<T> {
    private Integer errno = ErrorCode.SUCCESS_REQUEST.getCode();

    private String errmsg = ErrorCode.SUCCESS_REQUEST.getDesc();

    private T data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void SetEnumCode(ErrorCode code) {
        this.errno = code.getCode();
        this.errmsg = code.getDesc();
    }

    public ResponseTemplate() {
    }

    public ResponseTemplate(Integer errno, String errmsg, T data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }

    public ResponseTemplate(ErrorCode errno, T data) {
        this.errno = errno.getCode();
        this.errmsg = errno.getDesc();
        this.data = data;
    }

    public static ResponseTemplate buildSuccessResult() {
        return new ResponseTemplate(ErrorCode.SUCCESS_REQUEST, null);
    }

    public static <U> ResponseTemplate<U> buildSuccessResult(U data) {
        return new ResponseTemplate(ErrorCode.SUCCESS_REQUEST, data);
    }

    public static ResponseTemplate buildFailResult(ErrorCode errorCode) {
        return new ResponseTemplate(errorCode.getCode(), errorCode.getDesc(), null);
    }

    public static <U> ResponseTemplate<U> buildFailResult(Integer errno, String msg, U data) {
        return new ResponseTemplate(errno, msg, data);
    }

    public static <U> ResponseTemplate<U> of(ErrorCode error) {
        return new ResponseTemplate(error.getCode(), error.getDesc(), null);
    }

    public static <U> ResponseTemplate<U> of() {
        return new ResponseTemplate<>(0, "", null);
    }

    public static <U> ResponseTemplate<U> buildResult(ErrorCode error, U data) {

        return new ResponseTemplate(error.getCode(), error.getDesc(), data);
    }

    public static ResponseTemplate buildResponseWithBindingErrors(BindingResult bindingResult) {
        Map<String, String> errmsg = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errmsg.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseTemplate.buildResult(ErrorCode.FAILED_REQUEST, errmsg);
    }
}
