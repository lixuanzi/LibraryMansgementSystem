package com.lizixuan.ui;

import com.lizixuan.component.AboutMe;
import com.lizixuan.component.BookManageComponent;
import com.lizixuan.component.BorrowManageComponent;
import com.lizixuan.component.UserManageComponent;
import com.lizixuan.util.PathUtils;
import com.lizixuan.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ManagerInterface {


    final int WIDTH = 1200;
    final int HEIGHT = 800;

    // 组装视图
    public void init(String name) throws Exception {
        JFrame jFrame = new JFrame("图书馆管理系统：" + name + "欢迎你");
        // 设置窗口属性
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jFrame.setResizable(false);
        jFrame.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("favicon.png"))));

        // 设置菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("文件");
        JMenuItem cancellationItem = new JMenuItem("注销");
        JMenuItem exitItem = new JMenuItem("退出");

        cancellationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(jFrame, "点击“确定”按钮，将退出此账户并返回到登录界面!\n确定继续吗？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        new ApplicationMainInterface().init();
                        jFrame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jMenu.add(cancellationItem);
        jMenu.add(exitItem);
        jMenuBar.add(jMenu);

        jFrame.setJMenuBar(jMenuBar);

        // 设置分割面板
        JSplitPane splitPane = new JSplitPane();

        // 支持连续布局
        splitPane.setContinuousLayout(true);
        // 初始显示位置
        splitPane.setDividerLocation(200);
        // 设置分割条宽度
        splitPane.setDividerSize(7);

        // 设置左侧内容
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
        DefaultMutableTreeNode userManage = new DefaultMutableTreeNode("用户管理");
        DefaultMutableTreeNode bookManage = new DefaultMutableTreeNode("图书管理");
        DefaultMutableTreeNode borrowManage = new DefaultMutableTreeNode("借阅管理");
        DefaultMutableTreeNode aboutManage = new DefaultMutableTreeNode("关于我们");

        root.add(userManage);
        root.add(bookManage);
        root.add(borrowManage);
        root.add(aboutManage);

        Color color = new Color(203, 220, 217);
        JTree tree = new JTree(root);
        MyRenderer myRenderer = new MyRenderer();
        myRenderer.setBackgroundNonSelectionColor(color);
        myRenderer.setBackgroundSelectionColor(new Color(140, 140, 140));
        tree.setCellRenderer(myRenderer);
        tree.setBackground(color);
        splitPane.setLeftComponent(tree);

        // 设置树组件监听事件
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // 得到当前选中的对象
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if (userManage.equals(lastPathComponent)) {
                    splitPane.setRightComponent(new UserManageComponent(jFrame));
                    splitPane.setDividerLocation(200);
                } else if (bookManage.equals(lastPathComponent)) {
                    splitPane.setRightComponent(new BookManageComponent(jFrame));
                    splitPane.setDividerLocation(200);
                } else if (borrowManage.equals(lastPathComponent)) {
                    splitPane.setRightComponent(new BorrowManageComponent(jFrame));
                    splitPane.setDividerLocation(200);
                } else if (aboutManage.equals(lastPathComponent)) {
                    try {
                        splitPane.setRightComponent(new AboutMe(jFrame));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    splitPane.setDividerLocation(200);
                }
            }
        });

        // 设置默认选中图书管理
        tree.setSelectionRow(2);

        jFrame.add(splitPane);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    // 设置树的图片,自定义节点绘制器
    private class MyRenderer extends DefaultTreeCellRenderer {
        private ImageIcon rootIcon = null;
        private ImageIcon userManageIcon = null;
        private ImageIcon bookManageIcon = null;
        private ImageIcon borrowManageIcon = null;
        private ImageIcon aboutIcon = null;

        public MyRenderer() {
            rootIcon = new ImageIcon(PathUtils.getRealPath("root.png"));
            userManageIcon = new ImageIcon(PathUtils.getRealPath("userManage.png"));
            bookManageIcon = new ImageIcon(PathUtils.getRealPath("bookManage.png"));
            borrowManageIcon = new ImageIcon(PathUtils.getRealPath("borrowManage.png"));
            aboutIcon = new ImageIcon(PathUtils.getRealPath("aboutManage.png"));
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            // 使用默认绘制
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            ImageIcon image = null;

            switch (row) {
                case 0:
                    image = rootIcon;
                    break;
                case 1:
                    image = userManageIcon;
                    break;
                case 2:
                    image = bookManageIcon;
                    break;
                case 3:
                    image = borrowManageIcon;
                    break;
                case 4:
                    image = aboutIcon;
                    break;
                default:
                    break;
            }
            this.setIcon(image);
            return this;
        }
    }

    public static void main(String[] args) {
        try {
            new ManagerInterface().init("demo");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
