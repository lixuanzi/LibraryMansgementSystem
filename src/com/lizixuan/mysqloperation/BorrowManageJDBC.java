package com.lizixuan.mysqloperation;

import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BorrowManageJDBC {
    Connection connection = null;
    // 防注入
    public static PreparedStatement preparedStatement;
    public void database(Vector data) {
        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "SELECT borrowId,borrowers,bookName,lendingLate,returnDate FROM borrowinfo";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            Vector<String> columnNames = new Vector<String>();
            columnNames.add("borrowId");
            columnNames.add("borrowers");
            columnNames.add("bookName");
            columnNames.add("lendingLate");
            columnNames.add("returnDate");

            while (resultSet.next()) {
                Vector<String> vString = new Vector<String>();

                vString.addElement(resultSet.getString("borrowId"));
                vString.addElement(resultSet.getString("borrowers"));
                vString.addElement(resultSet.getString("bookName"));
                vString.addElement(resultSet.getString("lendingLate"));
                vString.addElement(resultSet.getString("returnDate"));

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