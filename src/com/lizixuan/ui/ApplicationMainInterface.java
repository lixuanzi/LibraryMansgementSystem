package com.lizixuan.ui;

import com.lizixuan.component.BackGroundPanel;
import com.lizixuan.util.JDBCUtils;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;
import com.lizixuan.verification.Login;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationMainInterface {
    JFrame jFrame = new JFrame("图书管理系统");

    final int WIDTH = 600;
    final int HEIGHT = 400;

    // 组装视图
    public void init() throws Exception {
        // 设置窗口居中
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        // 设置大小固定
        jFrame.setResizable(false);
        jFrame.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("favicon.png"))));

        // 设置窗口内容
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("bg.jpg"))));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);
        // 组装登录
        Box vBox = Box.createVerticalBox();
        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        // 组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        JPasswordField pField = new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        // 组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        JButton regisBtn = new JButton("注册");
        JButton JDBCBtn = new JButton("测试数据库连接");

        // 对登录按钮监听
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的内容
                String username = uField.getText().trim();
                String password = pField.getText().trim();

                // 初始化 Login
                Login login = new Login();
                login.loginVer(username);
                if (username.equals("admin") && password.equals("123456")) {
                    JOptionPane.showMessageDialog(jFrame, "恭喜，尊贵的超级用户 Admin 登录成功！");
                    try {
                        new ManagerInterface().init("Admin");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    jFrame.dispose();
                } else if (password.equals(login.password)) {
                    JOptionPane.showMessageDialog(jFrame, "恭喜，尊贵的用户" + username + " 登录成功！");
                    try {
                        new ManagerInterface().init(username);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    jFrame.dispose();
                } else if (login.contains == false) {
                    JOptionPane.showMessageDialog(jFrame, "用户名不存在！");
                } else {
                    JOptionPane.showMessageDialog(jFrame, "帐号或密码有误！", "警告", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 对注册按钮监听
        regisBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new RegisterInterface().init();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                jFrame.dispose();
            }
        });

        // 测试数据库连接
        JDBCBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = JDBCUtils.getConnection();
                    if (!connection.isClosed()) {
                        JOptionPane.showMessageDialog(jFrame, "测试数据库连接成功！");
                    } else {
                        JOptionPane.showMessageDialog(jFrame, "测试数据库连接失败！", "警告", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });


        btnBox.add(JDBCBtn);
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(regisBtn);


        // WelCome
        /*JLabel welComeLabel = new JLabel("欢迎您");
        welComeLabel.setFont(new Font("微软雅黑",Font.BOLD,30));
        vBox.add(welComeLabel);*/

        vBox.add(Box.createVerticalStrut(100));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);

        bgPanel.add(vBox);

        jFrame.add(bgPanel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    // 客户端程序入口
    public static void main(String[] args) throws Exception {
        new ApplicationMainInterface().init();
    }
}
