package com.lizheng.test.spring;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class testCode {
    static int anInt = 1;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(testCode.class);
    }

}
