package com.lizixuan.component;

import com.lizixuan.listener.ActionDoneListener;
import com.lizixuan.mysqloperation.UpDataBorrowJDBC;
import com.lizixuan.mysqloperation.UpDataUserJDBC;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UpDataBorrowDialog extends JDialog {
    // 定义宽高
    final int WIDTH = 600;
    final int HEIGHT = 400;

    private JTextField iField;
    private JTextField eField;
    private JTextField nField;
    private JTextField lField;
    private JTextField rField;

    private ActionDoneListener listener;

    // 构造方法
    public UpDataBorrowDialog() {

    }

    public UpDataBorrowDialog(JFrame jFrame, String title, boolean isModel, ActionDoneListener listener, String borrowId, String borrowers, String bookName, String lendingLate, String returnDate) throws Exception {
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
        iField = new JTextField(15);

        iBox.add(iLabel);
        iBox.add(Box.createHorizontalStrut(5));
        iBox.add(iField);

        // 组装图书名称
        Box nBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("图书名称：");
        nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(5));
        nBox.add(nField);

        // 组装借阅者
        Box eBox = Box.createHorizontalBox();
        JLabel eLabel = new JLabel("借  阅  人：");
        eField = new JTextField(15);

        eBox.add(eLabel);
        eBox.add(Box.createHorizontalStrut(5));
        eBox.add(eField);

        // 组装借出时间
        Box lBox = Box.createHorizontalBox();
        JLabel lLabel = new JLabel("借出时间：");
        lField = new JTextField(15);

        lBox.add(lLabel);
        lBox.add(Box.createHorizontalStrut(5));
        lBox.add(lField);

        // 组装归还时间
        Box rBox = Box.createHorizontalBox();
        JLabel rLabel = new JLabel("归还时间：");
        rField = new JTextField(15);

        rBox.add(rLabel);
        rBox.add(Box.createHorizontalStrut(5));
        rBox.add(rField);

        // 按钮
        Box bBox = Box.createHorizontalBox();
        JButton okBtn = new JButton("更新信息");


        // 将选中内容填充
        iField.setText(borrowId);
        iField.setEditable(false);
        eField.setText(borrowers);
        nField.setText(bookName);
        lField.setText(lendingLate);
        rField.setText(returnDate);

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
        vBox.add(eBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(nBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(lBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(rBox);
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

        String borrowId = iField.getText().trim();
        String borrowers = eField.getText().trim();
        String bookName = nField.getText().trim();
        String lendingLate = lField.getText().trim();
        String returnDate = rField.getText().trim();

        // 调用更新方法
        UpDataBorrowJDBC upDataBorrowJDBC = new UpDataBorrowJDBC();
        upDataBorrowJDBC.upDataBorrowInfoToDatabase(borrowId, borrowers, bookName, lendingLate, returnDate);

        // 接口回调 刷新表格
        listener.done(null);
    }

}
