package com.lizheng.test.str;

public class testStr {
    public static void main(String[] args) {
        System.out.println(B_t.str);
    }
}

class A_t{

    public static String str = "aa";
    static {
        System.out.println("aa");
    }

}

class B_t extends A_t{

    static {
        str += '1';
        System.out.println("bbb");
    }


}