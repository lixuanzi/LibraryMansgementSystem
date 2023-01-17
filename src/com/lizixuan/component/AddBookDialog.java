package com.lizixuan.component;

import com.lizixuan.listener.ActionDoneListener;
import com.lizixuan.mysqloperation.AddBookJDBC;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddBookDialog extends JDialog {
    // 定义宽高
    final int WIDTH = 600;
    final int HEIGHT = 400;
    private ActionDoneListener listener;

    public AddBookDialog(JFrame jFrame, String title, boolean isModel, ActionDoneListener listener) throws Exception {
        super(jFrame, title, isModel);

        this.listener = listener;
        // 组装视图
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);

        // 带背景的 JPanel
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("register.jpg"))));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();

        // 编号
        Box iBox = Box.createHorizontalBox();
        JLabel iLabel = new JLabel("编号：");
        JTextField iField = new JTextField(15);

        iBox.add(iLabel);
        iBox.add(Box.createHorizontalStrut(15));
        iBox.add(iField);

        // 名称
        Box nBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("书名：");
        JTextField nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(15));
        nBox.add(nField);

        // 作者
        Box aBox = Box.createHorizontalBox();
        JLabel aLabel = new JLabel("作者：");
        JTextField aField = new JTextField(15);

        aBox.add(aLabel);
        aBox.add(Box.createHorizontalStrut(15));
        aBox.add(aField);

        // 价格
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("价格：");
        JTextField pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 简介
        Box sBox = Box.createHorizontalBox();
        JLabel sLabel = new JLabel("简介：");
        JTextArea sArea = new JTextArea(3, 15);

        sBox.add(sLabel);
        sBox.add(Box.createHorizontalStrut(15));
        sBox.add(sArea);

        // 按钮
        Box bBox = Box.createHorizontalBox();
        JButton okBtn = new JButton("添加");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户录入信息
                String bookID = iField.getText().trim();
                String bookName = nField.getText().trim();
                String author = aField.getText().trim();
                String price = pField.getText().trim();
                String synopsis = sArea.getText().trim();

                if (bookID.length() == 0 && bookName.length() == 0 && price.length() == 0) {
                    JOptionPane.showMessageDialog(jFrame, "编号 作者 价格 均不能为空\n" + "请检查!", "警告", JOptionPane.ERROR_MESSAGE);
                }

                if (bookID.length() != 0 && bookName.length() != 0 && price.length() != 0) {
                    // 调用插入方法
                    AddBookJDBC addBookJDBC = new AddBookJDBC();
                    addBookJDBC.addBookInfoToDatabase(bookID, bookName, author, price, synopsis);

                    // 接口回调 刷新表格
                    listener.done(null);

                    JOptionPane.showMessageDialog(jFrame, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
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
        vBox.add(iBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(nBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(aBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(sBox);
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
}
