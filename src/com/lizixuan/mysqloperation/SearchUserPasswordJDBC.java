package com.lizixuan.mysqloperation;

import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SearchUserPasswordJDBC {
    public ArrayList<String> userId;
    public ArrayList<String> userPW;
    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void database() {
        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "SELECT userName,password FROM employeeinfo";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            userId = new ArrayList<>();
            userPW = new ArrayList<>();

            while (resultSet.next()) {
                userId.add(resultSet.getString(1));
                userPW.add(resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}