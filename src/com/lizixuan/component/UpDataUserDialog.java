package com.lizixuan.component;

import com.lizixuan.listener.ActionDoneListener;
import com.lizixuan.mysqloperation.UpDataBookJDBC;
import com.lizixuan.mysqloperation.UpDataUserJDBC;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UpDataUserDialog extends JDialog {
    // 定义宽高
    final int WIDTH = 600;
    final int HEIGHT = 400;

    private JTextField cField;
    private JTextField uField;
    private JTextField pField;
    private JTextField gField;
    private JTextField eField;
    private JTextField tField;

    private ActionDoneListener listener;

    // 构造方法
    public UpDataUserDialog() {

    }

    public UpDataUserDialog(JFrame jFrame, String title, boolean isModel, ActionDoneListener listener, String employeeId, String userName, String password, String gender, String mold, String phone) throws Exception {
        super(jFrame, title, isModel);

        this.listener = listener;
        // 组装视图
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);

        // 带背景的 JPanel
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("register.jpg"))));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();

        // 组装职工编号
        Box cBox = Box.createHorizontalBox();
        JLabel cLabel = new JLabel("职工编号：");
        cField = new JTextField(15);

        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(5));
        cBox.add(cField);

        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(uField);

        // 组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 组装性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        gField = new JTextField(15);

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(15));
        gBox.add(gField);

        // 组装身份
        Box eBox = Box.createHorizontalBox();
        JLabel eLabel = new JLabel("身    份：");
        eField = new JTextField(15);

        eBox.add(eLabel);
        eBox.add(Box.createHorizontalStrut(15));
        eBox.add(eField);

        // 组装手机号码
        Box tBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("手机号：");
        tField = new JTextField(15);

        tBox.add(tLabel);
        tBox.add(Box.createHorizontalStrut(15));
        tBox.add(tField);

        // 按钮
        Box bBox = Box.createHorizontalBox();
        JButton okBtn = new JButton("更新信息");



        // 将选中内容填充
        cField.setText(employeeId);
        cField.setEditable(false);
        uField.setText(userName);
        pField.setText(password);
        gField.setText(gender);
        eField.setText(mold);
        tField.setText(phone);

        //TODO 更新数据按钮添加监听
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoInPut();
                JOptionPane.showMessageDialog(jFrame, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton backBtn = new JButton("取消");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        bBox.add(okBtn);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(backBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(cBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(gBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(eBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(tBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(bBox);

        // 添加水平 Box 使 vBox 居中
        Box hBox = Box.createHorizontalBox();

        hBox.add(Box.createHorizontalStrut(40));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(40));

        bgPanel.add(vBox);
        this.add(bgPanel);
    }


    public void infoInPut() {

        // 获取用户录入信息
        String employeeId = cField.getText().trim();
        String userName = uField.getText().trim();
        String password = pField.getText().trim();
        String gender = gField.getText().trim();
        String mold = eField.getText().trim();
        String phone = tField.getText().trim();

        // 调用更新方法
        UpDataUserJDBC upDataUserJDBC = new UpDataUserJDBC();
        upDataUserJDBC.upDataBookInfoToDatabase(employeeId,userName,password,gender,mold,phone);

        // 接口回调 刷新表格
        listener.done(null);
    }

}
