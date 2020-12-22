package com.lizheng.event;

import org.springframework.boot.autoconfigure.AutoConfigurationImportEvent;
import org.springframework.boot.autoconfigure.AutoConfigurationImportListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AutoConfigurationImportListenerDemo implements AutoConfigurationImportListener {
    @Override
    public void onAutoConfigurationImportEvent(AutoConfigurationImportEvent event) {
        System.out.println(event.getExclusions());
        System.out.println(event.getCandidateConfigurations());
        System.out.println(event.getSource());
        System.out.println("扩展拉");
    }
}
