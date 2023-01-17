package com.lizixuan.mysqloperation;

import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserJDBC {
    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void deleteUserInfoById(String  employeeId) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM employeeinfo WHERE employeeId=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,employeeId);

            // 判断是否删除成功 result 〉 0 表示插入成功
            int result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                JDBCUtils.close(connection,preparedStatement);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
