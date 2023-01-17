package com.lizixuan.mysqloperation;


import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 添加图书
public class UpDataBorrowJDBC {

    public int result;

    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void upDataBorrowInfoToDatabase(String borrowId,String borrowers,String bookName,String lendingLate,String returnDate) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE borrowinfo SET borrowers= ?, bookName= ?, lendingLate= ? ,returnDate= ? WHERE borrowId=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,borrowers);
            preparedStatement.setString(2,bookName);
            preparedStatement.setString(3,lendingLate);
            preparedStatement.setString(4,returnDate);
            preparedStatement.setString(5,borrowId);


            // 判断是否更新成功 result 〉 0 表示插入成功
            result = preparedStatement.executeUpdate();


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
