package com.lizheng.service.impl;

import com.google.zxing.WriterException;
import com.lizheng.service.QrCodeService;
import com.lizheng.util.QrCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Stack;


@Service
public class QrCodeServiceImpl implements QrCodeService {
    public static LambdaTest lambdaTest;

    @Resource
    private QrCodeUtil qrCodeUtil;
    @Override
    public BufferedImage getQrCodeImage(String content) throws WriterException {
        return qrCodeUtil.qrEncode(content);
    }
    public LambdaTest vo(){
        return this::sou;
    }

    public void sou(UserStart userStart){
        System.out.println("LambdaTest start");
        HaStart haStart = new HaStart();
        haStart.onStart(userStart);
        Integer.bitCount(1);
    }




    public static ListNode reverseList(ListNode head) {
        ListNode listNode = null;
        ListNode temp = head;
        while(temp != null){
            ListNode next = temp.next;
            temp.next = listNode;
            listNode = temp;
            temp = next;
        }
        return listNode;
    }
    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
   }
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            if (a != 0){
                nums[i] = 0;
                nums[index] = a;
                index ++;
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3,2,3};
        int i = majorityElement(arr);

    }
    public static int majorityElement(int[] nums) {
        int count = 1;
        int num = nums[0];
        if (nums.length != 1){
            int n = nums.length%2 + nums.length/2;
            for (int i = 1; i < nums.length; i++) {
                if (num == nums[i]){
                    count++;
                    if (count >= n){
                        return num;
                    }
                }else {
                    if (--count == 0){
                        count = 1;
                        num = nums[i];
                    }
                }
            }
        }
        return num;
    }
}
