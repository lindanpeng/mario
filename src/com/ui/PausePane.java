package com.ui;

import java.awt.Graphics;

import javax.swing.JPanel;


public class PausePane extends JPanel{
	// 宽度
	private int width;
	// 高度
	private int height;
	public PausePane(int width,int height){
		this.height=height;
		this.width=width;
		this.setOpaque(false);//如何设置透明而不覆盖下面的面板？？
		this.setLayout(null);
	}
	@Override
	public void paintComponent(Graphics g){
	   super.paintComponent(g);
	 //绘制关卡结束后的阴影效果
	   g.drawImage(Img.pauseImage,0,0,width,height,0,0,32,32,null);
	 
	}
}
