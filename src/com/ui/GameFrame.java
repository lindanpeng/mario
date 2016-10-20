package com.ui;

import javax.swing.JFrame;

import com.controller.GameController;
import com.util.FrameUtil;



/**
 * 游戏主窗口
 * 
 * @author Administrator
 *
 */
public class GameFrame extends JFrame {
	//游戏运行面板
	private GamePane gamePane;
	//游戏控制器
	private GameController gameController;
	public GameFrame() {

		//TODO 硬编码
		int height=600;
		int width=900;
	    gamePane=new GamePane(width,height);
	    
		gameController=new GameController(this);
		gameController.startGame();
		gamePane.setIsStart(true);
		// 设置标题
		this.setTitle("超級玛丽");
		// 设置默认关闭属性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口大小
		this.setSize(width + 6,height + 28);
		//设置内容
		this.setContentPane(gamePane);
		// 不允许用户改变大小
		this.setResizable(false);
		//居中
		FrameUtil.setFrameCenter(this);
		//设置可见
		
		this.setVisible(true);
	}
	
	
	public GamePane getGamePane() {
		return gamePane;
	}
  public static void main(String[] args) {
	GameFrame gameFrame=new GameFrame();
	gameFrame.getGamePane().requestFocus();
	gameFrame.getGamePane().requestFocus();
	
	
}
}
