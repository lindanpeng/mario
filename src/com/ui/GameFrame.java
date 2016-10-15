package com.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

//import config.FrameConfig;
//import config.GameConfig;
//import ui.window.JPanelGame;
//import util.FrameUtil;

/**
 * 游戏主窗口
 * @author Administrator
 *
 */
public class GameFrame extends JFrame {
	public GameFrame(){
		// GameConfig cfg=ConfigFactory.getGameConfig();
		//设置标题
		 this.setTitle("mario");
	   //设置默认关闭属性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口大小
		this.setSize(1168,680);
		//不允许用户改变大小
		this.setResizable(false);
		//居中
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screen=toolkit.getScreenSize();
        int x=(screen.width-this.getWidth())/2;
    	int y=(screen.height-this.getHeight())/2-30;
		this.setLocation(x,y);
		this.setVisible(true);
		this.setContentPane(new StartPane());
	}
}
