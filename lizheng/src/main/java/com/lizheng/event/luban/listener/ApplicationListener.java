package com.lizheng.event.luban.listener;

import com.lizheng.event.luban.evnt.ApplicationEvnt;

public interface ApplicationListener< E extends ApplicationEvnt> {


//    void  onEvnts(String ... a);

    void onEvnt(E e);
}
