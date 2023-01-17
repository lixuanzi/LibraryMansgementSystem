package com.lizixuan.ui;

import com.lizixuan.component.BackGroundPanel;
import com.lizixuan.mysqloperation.AddUserJDBC;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RegisterInterface {
    JFrame jFrame = new JFrame("注册");

    final int WIDTH = 600;
    final int HEIGHT = 400;

    // 组装视图
    public void init() throws Exception {
        // 设置窗口的属性
        // 窗口居中
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jFrame.setResizable(false);
        jFrame.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("favicon.png"))));

        // 带背景的 JPanel
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("register.jpg"))));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();

        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(uField);

        // 组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        JPasswordField pField = new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 组装手机号码
        Box tBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("手机号：");
        JTextField tField = new JTextField(15);

        tBox.add(tLabel);
        tBox.add(Box.createHorizontalStrut(15));
        tBox.add(tField);

        // 组装性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        JRadioButton maleBtn = new JRadioButton("男", true);
        JRadioButton femaleBtm = new JRadioButton("女", false);

        ButtonGroup gGroup = new ButtonGroup();
        gGroup.add(maleBtn);
        gGroup.add(femaleBtm);
        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtm);
        gBox.add(Box.createHorizontalStrut(125));

        // 组装职工编号
        Box cBox = Box.createHorizontalBox();
        JLabel cLabel = new JLabel("职工编号：");
        JTextField cField = new JTextField(15);

        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(5));
        cBox.add(cField);

        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton registerBtn = new JButton("注册");
        JButton backBtn = new JButton("返回登录");

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户录入信息
                String employeeNo = cField.getText().trim();
                String username = uField.getText().trim();
                String password = pField.getText().trim();
                String gender = gGroup.isSelected(maleBtn.getModel()) ? "男" : "女";
                String phone = tField.getText().trim();

                if (employeeNo.length() == 0 || username.length() == 0 || password.length() == 0) {
                    JOptionPane.showMessageDialog(jFrame, "职工编号 用户名和密码均不能为空！", "警告", JOptionPane.ERROR_MESSAGE);
                }
                if (employeeNo.length() != 0 && username.length() != 0 && password.length() != 0) {
                    // 调用插入方法
                    AddUserJDBC addUserJDBC = new AddUserJDBC();
                    addUserJDBC.addUserInfoToDatabase(employeeNo, username, password, gender, phone);

                    JOptionPane.showMessageDialog(jFrame, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ApplicationMainInterface().init();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                jFrame.dispose();
            }
        });

        bBox.add(registerBtn);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(backBtn);

        // 整合界面
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(cBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(gBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(tBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(bBox);

        bgPanel.add(vBox);

        jFrame.add(bgPanel);

        jFrame.setVisible(true);
    }
}
