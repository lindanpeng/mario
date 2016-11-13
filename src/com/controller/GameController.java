package com.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.config.MarioConfig;
import com.config.MessageConfig;
import com.config.ScenesConfig;
import com.config.SystemConfig;
import com.gameObject.creature.Mario;
import com.gameObject.lifelessObject.Obstruction;
import com.resource.Img;
import com.resource.MusicPlayer;
import com.scene.Scene;
import com.ui.GameFrame;
import com.ui.GamePane;

/**
 * 游戏控制器
 * 
 * @author 林丹
 *
 */
public class GameController {
	// 游戏线程控制对象
	public static final Object THREAD_OBJECT = new Object();
	// 游戏窗口
	private GameFrame gameFrame;
	// 游戏运行面板
	private GamePane gamePane;
	// 所有场景
	private List<Scene> scenes;
	// 当前场景1
	private Scene firstScene;
	// 当前场景2
	private Scene secondScene;
	// 超级玛丽
	private Mario mario;
	// 玩家控制器
	private MyKeyListenter myKeyListenter;
	// 游戏是否暂停标志
	private boolean isPause;
	// 是否取得胜利
	private boolean isWin;
	// mario行为控制
	private Map<KeyCodeAndType, Method> marioAction;

	public GameController(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.gamePane = gameFrame.getGamePane();
		this.init();
	}
	/**
	 * 初始化方法,载入资源以及数据,建立游戏人物场景
	 */
	public void init() {
		// 获取mario数据并创建对象
		mario = new Mario(MarioConfig.X,MarioConfig.Y,MarioConfig.DEFAULT_WIDTH,MarioConfig.DEFAULT_HEIGHT,
				          MarioConfig.LIFE,MarioConfig.XSPEED,MarioConfig.YSPEED,this);
		// 获取游戏场景数据并创建对象
		scenes =ScenesConfig.ALLSCENES;
		scenes.get(scenes.size()-1).setBackground(Img.endImage);
        for(Scene scene:scenes)
        	scene.setMario(mario);
		// 设置场景1
		firstScene = scenes.get(0);
		// 设置场景2
		if(scenes.size()>1)
			{
			 secondScene=scenes.get(1);
			 secondScene.setX(ScenesConfig.SCENE_WIDTH);
			}
		else			
		  {
			secondScene=firstScene;
		  }
		// 设置第二个场景的起始x坐标在画面之外
		
		gamePane.setMario(mario);
		myKeyListenter = new MyKeyListenter(this);

		mario.setLastSceneSort(scenes.size());
		marioAction = new HashMap<KeyCodeAndType, Method>();
		try {
			marioAction.put(new KeyCodeAndType(37, 0), mario.getClass().getMethod("toLeftMove"));
			marioAction.put(new KeyCodeAndType(39, 0), mario.getClass().getMethod("toRightMove"));
			marioAction.put(new KeyCodeAndType(37, 1), mario.getClass().getMethod("toLeftStop"));
			marioAction.put(new KeyCodeAndType(39, 1), mario.getClass().getMethod("toRightStop"));
			marioAction.put(new KeyCodeAndType(38, 0), mario.getClass().getMethod("toJump"));

		} catch (NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 开始游戏,启动各个线程
	 */
	public void startGame() {
		isPause = false;
		isWin = false;
		gamePane.setStartTime(System.currentTimeMillis());
		gamePane.setFirstScene(firstScene);
		gamePane.setSecondScene(secondScene);
		mario.setFirstScene(firstScene);
		mario.setSecondScene(secondScene);
		mario.setNowScene(firstScene);
		// TODO 为了不消耗资源,改为每次启动两个场景的线程
        firstScene.reset();
        secondScene.reset();
		// 初始化mario对象，启动线程
		mario.restart();
		// 启动画面绘制线程
		new GameThread().start();
		// 安装玩家游戏控制器
		gameFrame.addKeyListener(myKeyListenter);
		gameFrame.requestFocus();
		//播放背景音乐
		MusicPlayer.playBackgroundMusic();
	}

	/**
	 * 暫停/运行游戲
	 */
	public void pauseOrPlayGame() {
		boolean status = !isPause;
		synchronized (THREAD_OBJECT) {
			isPause = status;
			
			mario.setPause(status);
			for (Scene scene : scenes) {
				scene.pauseScene(status);
			}
			gameFrame.showPausePane(status);
			if (!status)
				THREAD_OBJECT.notifyAll();
		}
	}

	/**
	 * 进入下一个场景
	 */
	public void nextScene() {
		if (mario.getSecondScene().getSort() != mario.getLastSceneSort()) {
			Scene scene = scenes.get(mario.getSecondScene().getSort());
		    scene.reset();
			scene.setX(ScenesConfig.SCENE_WIDTH);
			mario.setFirstScene(mario.getSecondScene());
			mario.setSecondScene(scene);
			gamePane.setFirstScene(mario.getFirstScene());
			gamePane.setSecondScene(mario.getSecondScene());
		} else {
			mario.setFirstScene(mario.getSecondScene());
			gamePane.setFirstScene(mario.getFirstScene());
			gamePane.setSecondScene(mario.getSecondScene());
		}
	
	}

	/**
	 * 执行mario的动作
	 * @param keyCodeAndType
	 */
	public void doAction(KeyCodeAndType keyCodeAndType) {
		try {
			marioAction.get(keyCodeAndType).invoke(mario);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("反射执行动作出错！");
			e.printStackTrace();
		}
	}

	/**
	 * 游戏结束后处理
	 * @throws InterruptedException 
	 */
	public void afterOver() throws InterruptedException {
        MusicPlayer.stopBackgroundMusic();
		int choice;
		if (isWin) {
			 MusicPlayer.playWinMusic();
			 choice = JOptionPane.showConfirmDialog(null,MessageConfig.WIN_MESSAGE, "提示", JOptionPane.YES_NO_OPTION);
		} 
		else {
        	MusicPlayer.playGameOverMusic();
        	choice= JOptionPane.showConfirmDialog(null,MessageConfig.GAMEOVER_MESSAGE, "提示", JOptionPane.YES_NO_OPTION);
		}
		if (choice == 0)
			System.exit(0);
		else {
			gameFrame.removeKeyListener(myKeyListenter);
			gameFrame.reset();
		}

	}
	/**
	 * 画面绘制线程
	 *
	 */
	class GameThread extends Thread {

		public void run() {
			try {
				// 当游戏不处于暂停或结束情况下则不断循环
				while (!mario.isOver() && !isWin) {
					Thread.sleep(SystemConfig.SLEEP_TIME);
					gamePane.repaint();
					if (isPause) {
						synchronized (GameController.THREAD_OBJECT) {
							GameController.THREAD_OBJECT.wait();
						}
					}
/*					if (mario.getX()>SystemConfig.AUTO_MOVE_X) {
						gameFrame.removeKeyListener(myKeyListenter);
					}*/
					if (mario.getX() >=SystemConfig.ENDX) {
						{
							isWin = true;
							mario.setOver(true);					
						}
					}

				}
				afterOver();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
