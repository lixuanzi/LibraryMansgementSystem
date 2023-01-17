package com.lizixuan.mysqloperation;

import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class SearchBorrowJDBC {
    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public Map<String, String> database(String valueAt) {
        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "SELECT borrowId,borrowers,bookName,lendingLate,returnDate FROM borrowinfo WHERE borrowId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, valueAt);

            HashMap<String, String> map = new HashMap<>();
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String borrowId = result.getString(1);
                String borrowers = result.getString(2);
                String bookName = result.getString(3);
                String lendingLate = result.getString(4);
                String returnDate = result.getString(5);
                map.put("borrowId", borrowId);
                map.put("borrowers", borrowers);
                map.put("bookName", bookName);
                map.put("lendingLate", lendingLate);
                map.put("returnDate", returnDate);
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