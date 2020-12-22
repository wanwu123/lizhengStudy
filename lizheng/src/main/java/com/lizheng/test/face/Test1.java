package com.lizheng.test.face;


import org.junit.Test;

public class Test1 {
    public static Test1 test1;

    public static Test1 getInstance(){
        if (test1 == null){
            synchronized (test1){
                if (test1 == null){
                    test1 = new Test1();
                }
            }
        }
        return test1;
    }

    @Test
    public void test1(){
        int[] arr = new int[]{1, 0, 2, 0, 3};
        int index = arr.length-1;
        for (int i = arr.length-1; i >= 0; i--) {
            if (arr[i] != 0){
                int temp = arr[index];
                arr[index--] = arr[i];
                arr[i] = temp;
            }
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

}

/**
 * 模拟一个接口，对list数据进行缓存，当list为空的时候读取数据库，不为空就返回结果
 */
