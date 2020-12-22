package com.lizheng.event.luban.util;



//假设现在公司让你开发一个文件操作帮助类
//定义一个文件读写方法   读取某个文件 写到某个类里面去

//但是  有可能会有需要记录文件读取进度条的需求



import com.lizheng.event.luban.evnt.AEvent;
import com.lizheng.event.luban.evnt.BEvent;
import com.lizheng.event.luban.evnt.FileUploadEvent;
import com.lizheng.event.luban.manage.ListenerManage;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

//有时候调用文件读取不需要进度条
//有时候需要进度条 如何实现？
public class FileUtil {





    public static int READ_SIZE= 100;


    public  static  void fileWrite(InputStream is, OutputStream os) throws Exception{
        fileWrite(is,os,null);
    }

    public static void fileWrite(InputStream is, OutputStream os,FileListener fileListener) throws Exception{
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos  = new BufferedOutputStream(os);
        //文件总大小
        int fileSize = is.available();
        //一共读取了多少
        int readSize = 0;
        byte[] b = new byte[READ_SIZE];
        boolean f = true;
        while (f){
            //文件实在小于第一次读的时候
            if (fileSize<READ_SIZE){
                byte[] bytes = new byte[fileSize];
                bis.read(bytes);
                bos.write(bytes);
                readSize = fileSize;
                f = false;
//                break;
                //当你是最后一次读的时候
            }else if(fileSize<readSize+READ_SIZE){
                byte[] bytes = new byte[fileSize-readSize];
                readSize = fileSize;
                bis.read(bytes);
                bos.write(bytes);
                f = false;
//                break;
            }else{
                bis.read(b);
                readSize +=READ_SIZE;
                bos.write(b);
            }



            if (fileListener!=null){
                fileListener.updateLoad(fileSize,readSize);
            }


           ListenerManage.pushEvent(new FileUploadEvent(fileSize,readSize));
        }
        bis.close();
        bos.close();
    }


  static  Map<String,Double>  map ;
    public static void main(String[] args) throws Exception {
       /* ListenerManage.addListener(new FileUploadListener());
        ListenerManage.addListener(new AAListener());
        ListenerManage.addListener(new BListener());
        ListenerManage.addListener(new AAListener());*/

        Scanner scanner = new Scanner(System.in);


        while (true){
            System.out.println("你要发布什么事件:");
            String scan = scanner.next();
            if (scan.equals("A")){
                ListenerManage.pushEvent(new AEvent());
            }else{
                ListenerManage.pushEvent(new BEvent());
            }
        }


//        File file = new File("c://写我.txt");
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        fileWrite(new FileInputStream(new File("c://读我！！！！！.txt")), new FileOutputStream(file));
    }


}
