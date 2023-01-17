package com.lizixuan.mysqloperation;

import com.lizixuan.component.UpDataBookDialog;
import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchBookJDBC {
    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public Map<String, String> database(String valueAt) {
        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "SELECT bookId,bookName,author,price,synopsis FROM bookinfo WHERE bookId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, valueAt);

            HashMap<String, String> map = new HashMap<>();
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String bookIdThis = result.getString(1);
                String bookNameThis = result.getString(2);
                String authorThis = result.getString(3);
                String priceThis = result.getString(4);
                String synopsisThis = result.getString(5);
                map.put("bookIdThis", bookIdThis);
                map.put("bookNameThis", bookNameThis);
                map.put("authorThis", authorThis);
                map.put("priceThis", priceThis);
                map.put("synopsisThis", synopsisThis);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                JDBCUtils.close(connection, preparedStatement);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}