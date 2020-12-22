package com.lizheng.util.zklock;

public interface ZKLock {

    boolean lock(String lockpath);

    boolean unlock(String lockpath);

}
