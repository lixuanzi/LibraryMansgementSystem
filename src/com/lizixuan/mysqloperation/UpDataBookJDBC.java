package com.lizixuan.mysqloperation;


import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 添加图书
public class UpDataBookJDBC {

    public int result;

    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void upDataBookInfoToDatabase(String bookID,String bookName,String author,String price,String synopsis) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE bookinfo SET  bookName = ? , author= ?, price= ?, synopsis= ? WHERE bookId=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,bookName);
            preparedStatement.setString(2,author);
            preparedStatement.setString(3,price);
            preparedStatement.setString(4,synopsis);
            preparedStatement.setString(5,bookID);


            // 判断是否更新成功 result 〉 0 表示插入成功
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
