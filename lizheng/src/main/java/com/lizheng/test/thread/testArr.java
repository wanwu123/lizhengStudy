package com.lizheng.test.thread;

import com.lizheng.test.thread.testS;
import com.lizheng.test.thread.testSync;
import org.openjdk.jol.info.ClassLayout;

public class testArr {
  public static boolean a = true;
  public static void main(String[] args) {
    te();
    System.out.println(ClassLayout.parseClass(testSync.class));
    System.out.println(ClassLayout.parseInstance(testSync.class));
    testS testS = new testS();
    System.out.println(testS);
    System.out.println(testS);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    a = false;

  }

  public static void te(){
    new Thread(()->{
      while (a){

      }
    }
    ).start();
  }
}
