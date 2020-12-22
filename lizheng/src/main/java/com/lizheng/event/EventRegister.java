package com.lizheng.event;


import org.springframework.stereotype.Component;

//发布事件



@Component
public class EventRegister {


    public void pushEvent(WebEvent event){
        System.out.println("准备发布消息");
        if (event instanceof hahaEvent){
            System.out.println("hahaevent");
        }else if(event instanceof Event){
            System.out.println("event");
        }
        AppContxt.applicationContext.publishEvent(event);
        System.out.println("发布消息成功");
    }
    public aaa a(){
        return this::test;
    }

    private void test(String a) {
        System.out.println(a);
    }
}
