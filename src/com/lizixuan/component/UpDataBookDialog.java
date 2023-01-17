package com.lizixuan.component;

import com.lizixuan.listener.ActionDoneListener;
import com.lizixuan.mysqloperation.AddBookJDBC;
import com.lizixuan.mysqloperation.UpDataBookJDBC;
import com.lizixuan.ui.ApplicationMainInterface;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UpDataBookDialog extends JDialog {
    // 定义宽高
    final int WIDTH = 600;
    final int HEIGHT = 400;

    private JTextField iField;
    private JTextField nField;
    private JTextField aField;
    private JTextField pField;
    private JTextArea sArea;


    private int id;
    private ActionDoneListener listener;

    // 构造方法
    public UpDataBookDialog() {

    }

    public UpDataBookDialog(JFrame jFrame, String title, boolean isModel, ActionDoneListener listener, String bookId, String bookName, String author, String price, String synopsis) throws Exception {
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
        iField = new JTextField(15);

        iBox.add(iLabel);
        iBox.add(Box.createHorizontalStrut(15));
        iBox.add(iField);

        // 名称
        Box nBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("书名：");
        nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(15));
        nBox.add(nField);

        // 作者
        Box aBox = Box.createHorizontalBox();
        JLabel aLabel = new JLabel("作者：");
        aField = new JTextField(15);

        aBox.add(aLabel);
        aBox.add(Box.createHorizontalStrut(15));
        aBox.add(aField);

        // 价格
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("价格：");
        pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 简介
        Box sBox = Box.createHorizontalBox();
        JLabel sLabel = new JLabel("简介：");
        sArea = new JTextArea(3, 15);

        sBox.add(sLabel);
        sBox.add(Box.createHorizontalStrut(15));
        sBox.add(sArea);

        // 按钮
        Box bBox = Box.createHorizontalBox();
        JButton okBtn = new JButton("更新信息");

        // 将选中内容填充
        iField.setText(bookId);
        iField.setEditable(false);
        nField.setText(bookName);
        aField.setText(author);
        pField.setText(price);
        sArea.setText(synopsis);

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


    public void infoInPut() {

        // 获取用户录入信息
        String bookID = iField.getText().trim();
        String bookName = nField.getText().trim();
        String author = aField.getText().trim();
        String price = pField.getText().trim();
        String synopsis = sArea.getText().trim();

        // 调用更新方法
        UpDataBookJDBC upDataBookJDBC = new UpDataBookJDBC();
        upDataBookJDBC.upDataBookInfoToDatabase(bookID, bookName, author, price, synopsis);
        // 接口回调 刷新表格
        listener.done(null);
    }

}
