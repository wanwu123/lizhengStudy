package com.lizheng.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    /*
     * 打开数据库
     */
    private static String driver;//连接数据库的驱动
    private static String url;
    private static String username;
    private static String password;

    static {
      driver="com.mysql.cj.jdbc.Driver";//需要的数据库驱动
      url="jdbc:mysql://localhost:3306/xxx?useAffectedRows=true&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";//数据库名路径
      username="root";
      password="123456";
    }
    public static Connection open()
    {
      try {
        Class.forName(driver);
        return  DriverManager.getConnection(url,username, password);
      } catch (Exception e) {
        System.out.println("数据库连接失败！");
        e.printStackTrace();
      }//加载驱动

      return null;
    }

    /*
     * 关闭数据库
     */
    public static void close(Connection conn)
    {
      if(conn!=null)
      {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }


  }
