package com.lizheng.propertisource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import java.util.Random;


@Slf4j
public class RandomKeyPropertySource extends PropertySource<Random> {
    public static final String RANDOM_PROPERTY_SOURCE_NAME = "randomKey";

    private static final String PREFIX = "randomKey.";

    //生成长度小于字符串的随机数字从而生成随机字符串
    private static String CONSTANT_STRING = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public RandomKeyPropertySource(String name) {
        super(name, new Random());
    }

    public RandomKeyPropertySource(){
        this(RANDOM_PROPERTY_SOURCE_NAME);
    }

    @Override
    public Object getProperty(String name) {
        if (!name.startsWith(PREFIX)) {
            return null;
        }
        if (log.isTraceEnabled()) {
            log.trace("Generating random property for '" + name + "'");
        }
        return getRandomValue(name.substring(PREFIX.length()));
    }
    private String getRandomValue(String type) {
        //默认生成64位的随机字符串
        if (type.equals("key")) {
            return randomString(64);
        }
        //指定了字符串长度，生成指定长度字符串
        String range = getRange(type, "key");
        if (range != null) {
            return getNextKeyRange(range);
        }
        return null;
    }
    //拷贝源码方法
    private String getRange(String type, String prefix) {
        if (type.startsWith(prefix)) {
            int startIndex = prefix.length() + 1;
            if (type.length() > startIndex) {
                return type.substring(startIndex, type.length() - 1);
            }
        }
        return null;
    }
    //做了简单修改，实际上只能范围只能指定一个固定的值，而不能是一个区间
    private String getNextKeyRange(String range) {
        String[] tokens = StringUtils.commaDelimitedListToStringArray(range);
        int start = Integer.parseInt(tokens[0]);
        if (tokens.length == 1) {
            return randomString(start);
        }
        return null;
    }
    //实际生成随机字符串的方法
    private String randomString(int length){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++){
            int number = getSource().nextInt(CONSTANT_STRING.length());
            sb.append(CONSTANT_STRING.charAt(number));
        }
        return sb.toString();
    }
    //这个方法应该没用，也照葫芦画瓢保留了
    public static void addToEnvironment(ConfigurableEnvironment environment) {
        environment.getPropertySources().addAfter(
                StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                new RandomValuePropertySource(RANDOM_PROPERTY_SOURCE_NAME));
        log.trace("RandomValuePropertySource add to Environment");
    }
}
