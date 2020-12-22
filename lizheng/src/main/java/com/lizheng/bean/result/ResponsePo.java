package com.lizheng.bean.result;

import java.util.HashMap;

public class ResponsePo<T> extends HashMap<String,Object> {
    private static final String codeNUM = "code";
    private static final String dataNUM = "data";
    private static final String msgNUM = "msg";

    public ResponsePo<T> create(String msg,T data){
        ResponsePo<T> tResponsePo = new ResponsePo();
        tResponsePo.put(msgNUM,msg);
        tResponsePo.put(codeNUM,"200");
        tResponsePo.put(dataNUM,data);
        return tResponsePo;
    }



}
