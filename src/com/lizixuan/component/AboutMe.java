package com.lizixuan.component;

import com.lizixuan.listener.ActionDoneListener;
import com.lizixuan.mysqloperation.BookManageJDBC;
import com.lizixuan.mysqloperation.DeleteBookJDBC;
import com.lizixuan.mysqloperation.SearchBookJDBC;
import com.lizixuan.util.PathUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

public class AboutMe extends Box {

    JFrame jf = null;
    final int WIDTH = 1200;
    final int HEIGHT = 600;

    public AboutMe(JFrame jf) throws IOException {
        // 垂直布局
        super(BoxLayout.Y_AXIS);
        //组装视图
        this.jf = jf;


        // 设置背景图片
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("aboutme.png"))));
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);


        // 垂直布局
        Box vBox = Box.createVerticalBox();

        Box zBox = Box.createHorizontalBox();
        JLabel zLabel = new JLabel("致    谢");
        zLabel.setFont(new java.awt.Font("等线", 1, 30));

        zBox.add(zLabel);


        // 致谢内容
        Box contentBox = Box.createHorizontalBox();
        JLabel contentLabel = new JLabel("总结：");
        JTextArea contentArea = new JTextArea(7,15);
        contentArea.setText("\n\n\n\n总的来说这次的还算顺利吧，其中个人感觉比较困难的地方就属于是修改\n" +
                            "内容了，完成修改内容不单单需要根据ID查找到还需要更新数据，从数据\n" +
                            "库中传出内容的时候也是费很大的功夫，在这里感谢一下我的学长“北鱼不\n" +
                            "该在戴溪打怪”。再也没有非常难的地方，哦对了，登录验证那里，由于的\n" +
                            "技术不太精湛用了非常愚蠢的办法，但是还是成功的验证了数据库里面的帐\n" +
                            "号和密码！");
        contentLabel.setFont(new java.awt.Font("等线", 1, 16));
        contentArea.setOpaque(false);
        contentArea.setEditable(false);

        contentBox.add(contentLabel);
        contentBox.add(contentArea);

        // 作者
        Box authorBox = Box.createHorizontalBox();
        JLabel authorLabel = new JLabel("作者：");
        JTextField authorField = new JTextField(15);

        authorLabel.setFont(new java.awt.Font("等线", 1, 16));
        authorField.setText("致敬（vx:lixuanzi0521）");
        authorField.setBorder(null);
        authorField.setOpaque(false);
        authorField.setEditable(false);

        authorBox.add(authorLabel);
        authorBox.add(authorField);

        // 鸣谢所有人员
        Box thankBox = Box.createVerticalBox();

        // 再次感谢一下成员提供的帮助和支持
        Box titleBox = Box.createHorizontalBox();
        JLabel titleLabel = new JLabel("再次感谢以下成员提供的帮助和支持");

        titleLabel.setFont(new java.awt.Font("等线", 1, 18));
        titleBox.add(titleLabel);


        Box biliBox = Box.createHorizontalBox();
        JTextField biliField = new JTextField();
        JTextField urlBiliField = new JTextField();

        biliField.setText("java教程 深入浅出讲解java的图形化界面编程");
        urlBiliField.setText("https://www.bilibili.com/video/av839084699/");

        biliField.setFont(new java.awt.Font("等线", 1, 15));
        biliField.setBorder(null);
        biliField.setOpaque(false);
        biliField.setEditable(false);
        urlBiliField.setFont(new java.awt.Font("等线", 1, 15));
        urlBiliField.setBorder(null);
        urlBiliField.setOpaque(false);
        urlBiliField.setEditable(false);

        biliBox.add(biliField);
        biliBox.add(Box.createHorizontalStrut(15));
        biliBox.add(urlBiliField);


        // 特别鸣谢
        Box specialBox = Box.createHorizontalBox();
        JLabel specialLabel =new JLabel("特别鸣谢");
        JTextField specialField = new JTextField();

        specialLabel.setFont(new java.awt.Font("等线", 1, 15));
        specialField.setText("北鱼不该在戴溪打怪");
        specialField.setFont(new java.awt.Font("等线", 1, 15));
        specialField.setBorder(null);
        specialField.setOpaque(false);
        specialField.setEditable(false);

        specialBox.add(specialLabel);
        specialBox.add(Box.createHorizontalStrut(255));
        specialBox.add(specialField);

        // 小组成员
        Box groupBox = Box.createHorizontalBox();
        JLabel groupLabel =new JLabel("小组成员");
        JTextField groupField = new JTextField();

        groupLabel.setFont(new java.awt.Font("等线", 1, 15));
        groupField.setText("积云雨(啥也不干)、浮世（就聊过一次天）");
        groupField.setFont(new java.awt.Font("等线", 1, 15));
        groupField.setBorder(null);
        groupField.setOpaque(false);
        groupField.setEditable(false);

        groupBox.add(groupLabel);
        groupBox.add(Box.createHorizontalStrut(255));
        groupBox.add(groupField);

        // 其他致谢
        Box otherBox = Box.createHorizontalBox();
        JLabel otherLabel =new JLabel("其他致谢（听了我好多废话^_^）");
        JTextArea otherField = new JTextArea();

        otherLabel.setFont(new java.awt.Font("等线", 1, 15));
        otherField.setText("陶陶\n\n"+"来日可期\n\n"+"仅我可见\n\n"+"胡说艺术家\n\n"+"城隔玖梦\n\n");
        otherField.setFont(new java.awt.Font("等线", 1, 15));
        otherField.setBorder(null);
        otherField.setOpaque(false);
        otherField.setEditable(false);

        otherBox.add(otherLabel);
        otherBox.add(Box.createHorizontalStrut(95));
        otherBox.add(otherField);

        thankBox.add(titleBox);
        thankBox.add(Box.createVerticalStrut(30));
        thankBox.add(biliBox);
        thankBox.add(Box.createVerticalStrut(20));
        thankBox.add(specialBox);
        thankBox.add(Box.createVerticalStrut(20));
        thankBox.add(groupBox);
        thankBox.add(Box.createVerticalStrut(20));
        thankBox.add(otherBox);

        vBox.add(Box.createVerticalStrut(40));
        vBox.add(zLabel);   // 添加致谢二字
        vBox.add(Box.createVerticalStrut(40));
        /*vBox.add(contentBox);   // 添加致谢内容
        vBox.add(Box.createVerticalStrut(20));*/
        vBox.add(authorBox);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(thankBox);

        bgPanel.add(vBox);
        this.add(bgPanel);
    }
}
