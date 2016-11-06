package com.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.config.MessageConfig;
import com.config.ScenesConfig;
import com.config.SystemConfig;
import com.controller.GameController;
import com.util.FrameUtil;



/**
 * 游戏主窗口
 * 
 * @author Administrator
 *
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	//游戏运行面板
	private GamePane gamePane;
	//游戏开始面板
	private StartPane startPane;
	//游戏暂停面板
	private PausePane pausePane;
	//游戏控制器
	private GameController gameController;
	//层次面板
	private JPanel contentPane;
	private CardLayout cardLayout;
	public GameFrame() {
		int height=ScenesConfig.SCENE_HEIGHT;
		int width=ScenesConfig.SCENE_WIDTH;
	    gamePane=new GamePane(width,height);
	    startPane=new StartPane(width,height);
	    pausePane=new PausePane(width, height);
	    cardLayout=new CardLayout();
        contentPane=new JPanel();
        contentPane.setLayout(cardLayout);
        contentPane.add(startPane, "start");
        contentPane.add(gamePane, "game");
        contentPane.add(pausePane, "pause");
		gameController=new GameController(this);
		addAction();
		// 设置标题
		this.setTitle(SystemConfig.TITLE);
		// 设置默认关闭属性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口大小
		this.setSize(SystemConfig.WINDOW_WIDTH,SystemConfig.WINDOW_HEIGHT);
		//设置内容
		this.setContentPane(contentPane);
		// 不允许用户改变大小
		this.setResizable(false);
		//居中
		FrameUtil.setFrameCenter(this);
		//设置可见
		
		this.setVisible(true);
	}
	public void addAction(){
		startPane.getStartBtn().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(contentPane);
				gameController.startGame();
			}
		});
		startPane.getAboutBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,MessageConfig.ABOUT_MESSAGE, "关于",
						JOptionPane.WARNING_MESSAGE);
				
			}
		});
	}
	public void reset() {
	     cardLayout.first(contentPane);
}
   public void showPausePane(boolean isPause){
	   if (isPause) 
	    cardLayout.last(contentPane);
	   else 		
		cardLayout.previous(contentPane);
		
	
   }
	public GamePane getGamePane() {
		return gamePane;
	}
  public static void main(String[] args) {

		 try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	new GameFrame();
}

}
