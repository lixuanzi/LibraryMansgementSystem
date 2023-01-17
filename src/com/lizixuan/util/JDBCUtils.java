package com.lizixuan.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    // 声明常量
    private static String url;
    private static String user;
    private static String password;

    // 静态代码块加载驱动
    static {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");
            // 加载配置对象
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

        } catch (Exception e) {
            System.out.println("连接失败");;
        }
    }

    // 单列设计模式
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    // 关闭资源
    public static void close(Connection connection, Statement statement) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(resultSet!=null){
            resultSet.close();
        }
    }
}

