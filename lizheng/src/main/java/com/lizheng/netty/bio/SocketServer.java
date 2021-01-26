package com.lizheng.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8222);
        while (true) {
            System.out.println("等待链接");
            //阻塞
            Socket accept = serverSocket.accept();
            System.out.println("有链接了");
            handler(accept);
            //可以支持多个客户端
            /*new Thread(() -> {
                try{
                    handler(accept);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }).start();*/
        }
    }

    private static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备read。。");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        if (read != -1) {
            System.out.println("接收到客户端的数据："+new String(bytes,0, read));
        }
        clientSocket.getOutputStream().write("Hello".getBytes());
        clientSocket.getOutputStream().flush();
    }
}