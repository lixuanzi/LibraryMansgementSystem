package com.lizixuan.mysqloperation;

import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class SearchUserJDBC {
    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public Map<String, String> database(String valueAt) {
        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "SELECT employeeId,userName,password,gender,mold, phone FROM employeeinfo WHERE employeeId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, valueAt);

            HashMap<String, String> map = new HashMap<>();
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String employeeId = result.getString(1);
                String userName = result.getString(2);
                String password = result.getString(3);
                String gender = result.getString(4);
                String mold = result.getString(5);
                String phone = result.getString(6);
                map.put("employeeId", employeeId);
                map.put("userName", userName);
                map.put("password", password);
                map.put("gender", gender);
                map.put("mold", mold);
                map.put("phone", phone);
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