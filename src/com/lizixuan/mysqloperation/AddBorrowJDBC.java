package com.lizixuan.mysqloperation;


import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 添加图书
public class AddBorrowJDBC {

    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void addBorrowInfoToDatabase(String borrowId,String borrowers,String bookName,String lendingLate,String returnDate) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();

            String sql = "INSERT into borrowinfo(borrowId,borrowers,bookName,lendingLate,returnDate) VALUES(?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,borrowId);
            preparedStatement.setString(2,borrowers);
            preparedStatement.setString(3,bookName);
            preparedStatement.setString(4,lendingLate);
            preparedStatement.setString(5,returnDate);

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
