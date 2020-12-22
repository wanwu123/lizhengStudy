package com.lizheng.test.face;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Face {
    /**
     * 1、寻找主数
     *     给定一个无序整型数组，长度为n(n＞0)，已知其中有某个数出现了＞n/2次。
     *     找出该数。你的算法时间复杂度和空间复杂度分别是多少？
     *     请实现： int majorityElement(int input[], int n);
     */
    public static void main(String[] args) {
        int[] arr = {1,2,2,3,6};
        majorityElement(arr,2);

    }
    public static int majorityElement(int input[], int n){
        int count = n / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= input.length; i++) {
            Integer integer = map.get(i);
            if (integer == null){
                map.put(i,1);
            }else {
                Integer num = map.get(i);
                num++;
                if (num == count){
                    return i;
                }
                map.put(i,num);
            }
        }
        return 0;
    }

    /**
     *     和为S的最长连续子序列
     *
     *     给定数组A[N] 和整数S，寻找i、j(i ≤ j)满足S = A[i] + A[i+1] + … + A[j]，
     *     输出满足条件的最大的长度max(j - i)，若找不到，输出0，你的算法复杂度是多少？
     *
     *     实现： int longestSumSSubArray(int A[], int length, int S)；
     */
    @Test
    public void test1(){
        int[] arr = {1,2,3,4,5};
        System.out.println(longestSumSSubArray(arr, 5, 15));
    }

    public static int longestSumSSubArray(int A[], int length, int S){
        int res = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j  < length; j++) {
                int temp = 0;
                for (int k = i; k <= j; k++) {
                    temp += A[k];
                }
                if (S == temp){
                    int value = j - i;
                    if (res < value){
                        res = value;
                    }
                }
            }
        }
        return res;
    }
}
