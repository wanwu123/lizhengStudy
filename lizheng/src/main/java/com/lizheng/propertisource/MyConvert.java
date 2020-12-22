package com.lizheng.propertisource;

import com.alibaba.fastjson.JSON;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public class MyConvert implements Converter<String,Map<String,List<String>>> {
    @Override
    public Map<String, List<String>> convert(String source) {
        return JSON.parseObject(source,Map.class);
    }
}
