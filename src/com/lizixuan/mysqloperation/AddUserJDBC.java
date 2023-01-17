package com.lizixuan.mysqloperation;


import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 添加图书
public class AddUserJDBC {

    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void addUserInfoToDatabase(String employeeId,String userName,String password,String gender,String mold,String phone) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "INSERT into employeeinfo(employeeId,userName,password,gender,mold,phone) VALUES(?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,employeeId);
            preparedStatement.setString(2,userName);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,gender);
            preparedStatement.setString(5,mold);
            preparedStatement.setString(6,phone);

            // 判断是否插入成功 result 〉 0 表示插入成功
            int result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                JDBCUtils.close(connection,preparedStatement);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
    public void addUserInfoToDatabase(String employeeId,String userName,String password,String gender,String phone) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "INSERT into employeeinfo(employeeId,userName,password,gender,phone) VALUES(?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,employeeId);
            preparedStatement.setString(2,userName);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,gender);
            preparedStatement.setString(5,phone);

            // 判断是否插入成功 result 〉 0 表示插入成功
            int result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                JDBCUtils.close(connection,preparedStatement);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
}
