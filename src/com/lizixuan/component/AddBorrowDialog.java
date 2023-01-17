package com.lizixuan.component;

import com.lizixuan.listener.ActionDoneListener;
import com.lizixuan.mysqloperation.AddBorrowJDBC;
import com.lizixuan.mysqloperation.AddUserJDBC;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddBorrowDialog extends JDialog {
    // 定义宽高
    final int WIDTH = 600;
    final int HEIGHT = 450;
    private ActionDoneListener listener;

    public AddBorrowDialog(JFrame jFrame, String title, boolean isModel, ActionDoneListener listener) throws Exception {
        super(jFrame, title, isModel);

        this.listener = listener;
        // 组装视图
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);

        // 带背景的 JPanel
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("register.jpg"))));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);

        Box vBox = Box.createVerticalBox();

        // 组装借阅编号
        Box iBox = Box.createHorizontalBox();
        JLabel iLabel = new JLabel("借阅编号：");
        JTextField iField = new JTextField(15);

        iBox.add(iLabel);
        iBox.add(Box.createHorizontalStrut(5));
        iBox.add(iField);

        // 组装图书名称
        Box nBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("图书名称：");
        JTextField nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(5));
        nBox.add(nField);

        // 组装借阅者
        Box eBox = Box.createHorizontalBox();
        JLabel eLabel = new JLabel("借  阅  人：");
        JTextField eField = new JTextField(15);

        eBox.add(eLabel);
        eBox.add(Box.createHorizontalStrut(5));
        eBox.add(eField);

        // 组装借出时间
        Box lBox = Box.createHorizontalBox();
        JLabel lLabel = new JLabel("借出时间：");
        JTextField lField = new JTextField(15);

        lBox.add(lLabel);
        lBox.add(Box.createHorizontalStrut(5));
        lBox.add(lField);

        // 组装归还时间
        Box rBox = Box.createHorizontalBox();
        JLabel rLabel = new JLabel("归还时间：");
        JTextField rField = new JTextField(15);

        rBox.add(rLabel);
        rBox.add(Box.createHorizontalStrut(5));
        rBox.add(rField);

        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");
        JButton backBtn = new JButton("取消");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户录入信息
                String borrowId = iField.getText().trim();
                String borrowers = nField.getText().trim();
                String bookName = eField.getText().trim();
                String lendingLate = lField.getText().trim();
                String returnDate = rField.getText().trim();

                if (borrowId.length() == 0 || borrowers.length() == 0 || bookName.length() == 0 || lendingLate.length() == 0|| returnDate.length() == 0) {
                    JOptionPane.showMessageDialog(jFrame, "五项均不能为空！", "警告", JOptionPane.ERROR_MESSAGE);
                }
                if (borrowId.length() != 0 && borrowers.length() != 0 && bookName.length() != 0 && lendingLate.length() != 0&& returnDate.length() != 0) {

                    // 调用插入方法
                    AddBorrowJDBC addBorrowJDBC = new AddBorrowJDBC();
                    addBorrowJDBC.addBorrowInfoToDatabase(borrowId, borrowers, bookName, lendingLate, returnDate);

                    // 接口回调 刷新表格
                    listener.done(null);

                    JOptionPane.showMessageDialog(jFrame, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        bBox.add(addBtn);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(backBtn);

        // 整合界面
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(iBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(eBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(nBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(lBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(rBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(bBox);

        bgPanel.add(vBox);
        this.add(bgPanel);
    }
}
