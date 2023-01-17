package com.lizixuan.component;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    // 声明图片
    private Image backIcon;

    public BackGroundPanel(Image backIcon) {
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);

    }
}
