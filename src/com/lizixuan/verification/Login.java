package com.lizixuan.verification;

import com.lizixuan.mysqloperation.SearchUserPasswordJDBC;

import java.util.ArrayList;

public class Login {
    public boolean contains; // 用户名是否存在
    public String password; // 用户密码
    public void loginVer(String userName){
        SearchUserPasswordJDBC searchUP = new SearchUserPasswordJDBC();
        searchUP.database();
        ArrayList<String> userId = searchUP.userId;
        ArrayList<String> userPW = searchUP.userPW;

        // 在用名中查找是否有与之匹配的用户
        contains = userId.contains(userName);
        if (contains == true){
            // 获取该用户名对应的下标
            int indexOf = userId.indexOf(userName);

            // 获取密码
            password = userPW.get(indexOf);
        }
    }
}
