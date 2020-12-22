package com.lizheng.propertisource;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;



public class MapKey extends PropertySource {

    @Value("${aaa}")
    private String aaa = "{one:'1', two:'1', three:'1'}";


    public static final String RANDOM_PROPERTY_SOURCE_NAME = "AAA";

    private static final String PREFIX = "mk.";

    public MapKey(String name) {
        super(name);
    }

    public MapKey(){
        this(RANDOM_PROPERTY_SOURCE_NAME);
    }


    @Override
    public Map<String, String> getProperty(String name) {
        if (!name.startsWith(PREFIX)) {
            return null;
        }
        if (name.substring(PREFIX.length()).equals("aaa")){
            Map<String,String> map = JSON.parseObject(aaa, Map.class);
            return map;
        }
        return null;
    }
}

