package com.sst.demo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.mysql.jdbc.Driver;

public class Hello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("come into the server...........");


        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //客户端 HttpUtils并没有写request方法是post ,但服务器端可自动识别
        String method = request.getMethod();
        System.out.println("request method :" + method);
        //Driver p;

        PrintWriter out = response.getWriter();
        //声明Connection对象
        Connection con = null;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://mysql-test.aws.lab:3306/data&autoReconnect=true&failOverReadOnly=false";
        //String url = "jdbc:mysql://localhost:3306/sqltestdb";
        //MySQL配置时的用户名
        String user = "data";
        //MySQL配置时的密码
        String password = "data";
        //遍历查询结果集

        //System.out.println("成功加载MySQL驱动程序");
        //加载驱动程序

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(!con.isClosed())

                out.print("connect to Mysql success !");
        else
                out.print("connect to Mysql failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //1.getConnection()方法，连接MySQL数据库！！



        out.flush();
        out.close();

    }
}