package com.lizixuan.mysqloperation;


import com.lizixuan.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 添加图书
public class UpDataUserJDBC {

    public int result;

    // 数据库连接变量 （联系）
    public static Connection connection;

    // 防注入（预处理语句；编报表）
    public static PreparedStatement preparedStatement;

    public void upDataBookInfoToDatabase(String employeeId,String userName,String password,String gender,String mold,String phone) {

        try {
            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE employeeinfo SET  userName = ? , password= ?, gender= ?, mold= ? ,gender= ?,phone= ?WHERE employeeId=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,gender);
            preparedStatement.setString(4,mold);
            preparedStatement.setString(5,gender);
            preparedStatement.setString(6,phone);
            preparedStatement.setString(7,employeeId);


            // 判断是否更新成功 result 〉 0 表示插入成功
            result = preparedStatement.executeUpdate();


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
