package com.lizixuan.mysqloperation;


import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 添加图书
public class AddBookJDBC {

    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void addBookInfoToDatabase(String  bookID,String bookName,String author,String price,String synopsis) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "INSERT into bookinfo(bookId,bookName,author,price,synopsis) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,bookID);
            preparedStatement.setString(2,bookName);
            preparedStatement.setString(3,author);
            preparedStatement.setString(4,price);
            preparedStatement.setString(5,synopsis);

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
