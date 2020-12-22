package com.lizheng.test.str;

public class testString {

    public void main(String[] args) {
        String a = "1";
        String a1 = "1";
        String a2 = a+a1;
        String a3 = "11";
        System.out.println(a3 == a2);
        System.out.println(a3.equals(a2));
    }

    public static void test1(){
        String a = "1";
        String a2 = "1";
    }

    public static void test2(){
        String a = new String("12");
        String a2 = new String("13");
    }
}
