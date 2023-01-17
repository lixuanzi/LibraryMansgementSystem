package com.lizixuan.mysqloperation;

import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BookManageJDBC {
    Connection connection = null;
    // 防注入
    public static PreparedStatement preparedStatement;
    public void database(Vector data) {
        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "SELECT bookId,bookName,author,price,synopsis FROM bookinfo";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            Vector<String> columnNames = new Vector<String>();
            columnNames.add("bookId");
            columnNames.add("bookName");
            columnNames.add("author");
            columnNames.add("price");
            columnNames.add("synopsis");

            while (resultSet.next()) {
                Vector<String> vString = new Vector<String>();

                vString.addElement(resultSet.getString("bookId"));
                vString.addElement(resultSet.getString("bookName"));
                vString.addElement(resultSet.getString("author"));
                vString.addElement(resultSet.getString("price"));
                vString.addElement(resultSet.getString("synopsis"));

                data.add(vString);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection,preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}