package com.lizixuan.component;

import com.lizixuan.listener.ActionDoneListener;
import com.lizixuan.mysqloperation.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

public class BorrowManageComponent extends Box {

    JFrame jf = null;
    final int WIDTH = 1200;
    final int HEIGHT = 600;


    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    // 声明数据模型
    private DefaultTableModel tableModel;


    public BorrowManageComponent(JFrame jf) {

        // 垂直布局
        super(BoxLayout.Y_AXIS);

        //组装视图
        this.jf = jf;
        JPanel btnPanel = new JPanel();
        Color color = new Color(203, 220, 217);
        btnPanel.setBackground(color);

        // 最大宽和高
        btnPanel.setMaximumSize(new Dimension(WIDTH, 40));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JTextField searchField = new JTextField(15);
//        searchField.setText("请输入需要检索图书编号");
        JButton searchBtn = new JButton("搜索");
        JButton addBtn = new JButton("添加");
        JButton updateBtn = new JButton("修改");
        JButton deleteBtn = new JButton("删除");

        //TODO 搜索借阅编号监听
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 获取搜索框内容
                String text = searchField.getText();
                if (text.length() != 0) {
                    int message = JOptionPane.showConfirmDialog(jf, "只能通过借阅编号搜索\n" + text + "是借阅编号吗？", "提示", JOptionPane.YES_NO_OPTION);

                    if (message == JOptionPane.YES_OPTION) {
                        // 调用方法获取输入内容所在的行号
                        int row = retrievalBookId(text);

                        // 选中第一列 row 行
                        table.setRowSelectionInterval(0, row);
                    }
                } else {
                    JOptionPane.showMessageDialog(jf, "请在搜索框输入借阅编号");
                }
            }
        });

        // TODO 添加借阅事件
        // 添加按钮的监听
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddBorrowDialog(jf, "添加借阅事件", true, new ActionDoneListener() {
                        @Override
                        public void done(Object result) {
                            requestData();
                        }
                    }).setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        // TODO 修改借阅监听
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 获取当前选中的行号,返回行号，咩有选中返回 -1
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(jf, "请选择要修改的条目");
                    return;
                }

                // 获取 bookId
                String valueAt = (String) tableModel.getValueAt(selectedRow, 0);

                // 通过 bookId 查询书籍信息

                SearchBorrowJDBC searchBorrowJDBC = new SearchBorrowJDBC();
                Map<String, String> map = searchBorrowJDBC.database(valueAt);
                String borrowId = map.get("borrowId");
                String borrowers = map.get("borrowers");
                String bookName = map.get("bookName");
                String lendingLate = map.get("lendingLate");
                String returnDate = map.get("returnDate");
                try {
                    new UpDataBorrowDialog(jf, "更新借阅", true, new ActionDoneListener() {
                        @Override
                        public void done(Object result) {

                            requestData();
                        }
                    }, borrowId, borrowers, bookName, lendingLate, returnDate).setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //TODO 删除借阅监听
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取当前选中的行号,返回行号，咩有选中返回 -1
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(jf, "请选择要删除的条目");
                }
                // 获取 bookId
                String valueAt = (String) tableModel.getValueAt(selectedRow, 0);

                int result = JOptionPane.showConfirmDialog(jf, "确定要删除借阅编号为：" + valueAt + "的借阅记录吗？", "警告", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    // 通过 bookId 删除书籍信息
                    DeleteBorrowJDBC deleteBorrowJDBC = new DeleteBorrowJDBC();
                    deleteBorrowJDBC.deleteBorrowInfoById(valueAt);

                    // 重新获取图书信息
                    requestData();
                    if(deleteBorrowJDBC.result>0){
                        JOptionPane.showMessageDialog(jf, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });
        btnPanel.add(searchField);
        btnPanel.add(searchBtn);
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);

        this.add(btnPanel);

        // 组装表格
        // 存储标题
        String[] ts = {"借阅编号", "借阅人", "书名", "借阅时间", "还回时间"};
        titles = new Vector<>();
        for (String title : ts) {
            titles.add(title);
        }

        // 存储数据
        tableData = new Vector<>();

        tableModel = new DefaultTableModel(tableData, titles);

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 一次只选中一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);

        // 调用图书信息填充到表格
        requestData();
    }


    // 获取图书信息
    public void requestData() {
        // 清空 tableData
        tableData.clear();

        BorrowManageJDBC borrowManageJDBC = new BorrowManageJDBC();
        borrowManageJDBC.database(tableModel.getDataVector());

        // 刷新表格
        tableModel.fireTableDataChanged();
    }


    // 遍历表格第一列
    public int retrievalBookId(String searchField) {
        int flag = 0;

        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if (searchField.equals(table.getModel().getValueAt(i, 0))) {
                flag = i;
                System.out.println(table.getModel().getValueAt(i, 0));
            }
        }

        return flag;
    }
}
