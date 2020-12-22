package com.lizheng.config.ps;

import com.lizheng.propertisource.MapKey;
import com.lizheng.propertisource.RandomKeyPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.PostConstruct;

@Configuration
public class PsConfig {
    @Autowired
    private ConfigurableEnvironment env;

    @PostConstruct
    public void init() throws Exception {
        env.getPropertySources().addFirst(new RandomKeyPropertySource());
//        env.getPropertySources().addFirst(new MapKey());
    }
}