package com.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PausePane extends JPanel{
//宽度
private int width;
//高度
private int height;
public PausePane(int width,int height) {
	this.width=width;
	this.height=height;
}
@Override
public void paint(Graphics g){
		g.drawImage(Img.pauseImage, 0, 0, width, height, null);  	
}
}
