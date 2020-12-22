package com.lizheng.event;


import com.lizheng.bean.po.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserS {
    public void register(Object o){
        if (o instanceof hahaEvent){
            System.out.println("初始化用户"+o);
            System.out.println("hahaevent");
            pushEvent(o);
            register(new Event("13121926522",new User(),1));
        }else if(o instanceof Event){
            System.out.println("event");
            pushEvent(o);
            System.out.println("结束初始化用户"+o);
        }

    }


    public void pushEvent(Object o){
        System.out.println("准备发布消息");
        System.out.println(o.getClass().toString());
       /* if (event instanceof hahaEvent){

        }else if(event instanceof Event){
            System.out.println("event");
        }*/
        AppContxt.applicationContext.publishEvent(o);
        System.out.println("发布消息成功");
    }
}

@Component
class Email implements ApplicationListener<hahaEvent> {
    @Override
    public void onApplicationEvent(hahaEvent hahaEvent) {
        System.out.println("用户"+hahaEvent+"发送email");
    }
}



@Component
class QQ{
    @EventListener
    public void onApplicationEvent(Event event) {
        System.out.println("用户"+event+"发送qq");
    }
}


@Configuration
class AsyncEventConfig {

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

}