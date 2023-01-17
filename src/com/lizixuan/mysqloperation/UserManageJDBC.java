package com.lizixuan.mysqloperation;

import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class UserManageJDBC {
    Connection connection = null;
    // 防注入
    public static PreparedStatement preparedStatement;
    public void database(Vector data) {
        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "SELECT employeeId,userName,password,gender,mold, phone FROM employeeinfo";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            Vector<String> columnNames = new Vector<String>();
            columnNames.add("employeeId");
            columnNames.add("userName");
            columnNames.add("password");
            columnNames.add("gender");
            columnNames.add("mold");
            columnNames.add("phone");

            while (resultSet.next()) {
                Vector<String> vString = new Vector<String>();

                vString.addElement(resultSet.getString("employeeId"));
                vString.addElement(resultSet.getString("userName"));
                vString.addElement(resultSet.getString("password"));
                vString.addElement(resultSet.getString("gender"));
                vString.addElement(resultSet.getString("mold"));
                vString.addElement(resultSet.getString("phone"));

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