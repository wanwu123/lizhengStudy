package com.lizheng.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Jackson配置类
 * @Date: 2019/5/20
 **/
@Configuration
public class JacksonConfig {

    @Autowired
    RestTemplateBuilder restTemplateBuilider;

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 字段保留，将null值转为""
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
//        {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator,
//                                  SerializerProvider serializerProvider)
//                    throws IOException, JsonProcessingException
//            {
//                jsonGenerator.writeString("");
//            }
//        });
        return objectMapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilider.build();
    }

}
