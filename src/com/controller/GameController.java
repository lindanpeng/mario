package com.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.constant.GameConstant;
import com.gameobject.Obstruction;
import com.listener.KeyCodeAndType;
import com.listener.MyKeyListenter;
import com.role.Enemy;
import com.role.Flower;
import com.role.Mario;
import com.role.Triangle;
import com.scene.Scene;
import com.ui.GameFrame;
import com.ui.GamePane;
import com.ui.Img;

/**
 * 游戏控制器
 * 
 * @author 林丹
 *
 */
public class GameController {
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
		mario = new Mario(0, 300, 0, this);
		// 获取游戏场景数据并创建对象
		scenes = new ArrayList<>();
		scenes.add(testScene1());
		scenes.add(testScene2());
		scenes.add(testScene3());
		// 设置场景1
		firstScene = scenes.get(0);
		// 设置场景2
		secondScene = scenes.get(1);
		// 设置第二个场景的起始x坐标在画面之外
		secondScene.setX(GameConstant.SCENE_DEFAULT_WIDTH);

		myKeyListenter = new MyKeyListenter(this);
		gamePane.setFirstScene(firstScene);
		gamePane.setSecondScene(secondScene);
		gamePane.setMario(mario);
		mario.setFirstScene(firstScene);
		mario.setSecondScene(secondScene);
		mario.setNowScene(firstScene);
		mario.setLastSceneSort(scenes.size());
		marioAction = new HashMap<KeyCodeAndType, Method>();
		try {
			marioAction.put(new KeyCodeAndType(37, 0), mario.getClass().getMethod("toLeftMove"));
			marioAction.put(new KeyCodeAndType(39, 0), mario.getClass().getMethod("toRightMove"));
			marioAction.put(new KeyCodeAndType(37, 1), mario.getClass().getMethod("toLeftStop"));
			marioAction.put(new KeyCodeAndType(39, 1), mario.getClass().getMethod("toRightStop"));
			marioAction.put(new KeyCodeAndType(38,0),  mario.getClass().getMethod("toJump"));

		} catch (NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 开始游戏,启动各个线程
	 */
	public void startGame() {
		isPause=false;
		isWin=false;
		gamePane.setPause(false);
		gamePane.setStartTime(System.currentTimeMillis());
		// TODO 为了不消耗资源,改为每次启动两个场景的线程
		for (Scene scene : scenes)
			scene.reset();
		// 初始化mario对象，启动线程
		mario.init();
		// 启动画面绘制线程
		new GameThread().start();
		// 安装玩家游戏控制器
		gamePane.addKeyListener(myKeyListenter);
		gamePane.requestFocus();
	}
  /**
   * 暫停/开始游戲
   */
	public void pauseGame(){
		boolean status=!isPause;
		isPause=status;
		mario.setPause(status);
		for(Scene scene:scenes){
			scene.pauseScene(status);
		}
		gamePane.setPause(status);
	
		
	}
	/**
	 * 进入下一个场景
	 */
	public void nextScene() {
		if (mario.getSecondScene().getSort() != mario.getLastSceneSort()) {
			Scene scene = scenes.get(mario.getSecondScene().getSort());
			scene.setX(GameConstant.SCENE_DEFAULT_WIDTH);
			mario.setFirstScene(mario.getSecondScene());
			mario.setSecondScene(scene);
			gamePane.setFirstScene(mario.getFirstScene());
			gamePane.setSecondScene(mario.getSecondScene());
		} else {
			mario.setFirstScene(mario.getSecondScene());
		}
	}
	/**
	 * 执行mario的动作
	 * @param keyCodeAndType
	 */
    public void doAction(KeyCodeAndType keyCodeAndType){
    	try {
			marioAction.get(keyCodeAndType).invoke(mario);			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("反射执行动作出错！");
			e.printStackTrace();
		}
    }
	/**
	 * 游戏结束后处理
	 */
	public void afterOver() {
		int i;
		if (isWin) {
			i = JOptionPane.showConfirmDialog(null, "恭喜你通过游戏的所有关卡，请问是否要退出游戏?", "提示", JOptionPane.YES_NO_OPTION);
		} else {
			i = JOptionPane.showConfirmDialog(null, "游戏结束，请问是否要退出游戏?", "提示", JOptionPane.YES_NO_OPTION);

		}
		if (i == 0)
			gameFrame.dispose();
		else {
			mario.setStart(false);
			mario.setScore(0);
			gamePane.removeKeyListener(myKeyListenter);
			gameFrame.reset();
		}

	}

	/**
	 * TODELETE 测试场景1
	 */
	private Scene testScene1() {
		List<Enemy> enemies = new ArrayList<>();
		/*
		 * 添加地面
		 */
		List<Obstruction> obstructions = new ArrayList<>();
		for (int i = 0; i < 15; i++)
			obstructions.add(new Obstruction(i * 60, 540, 1));

		//enemies.add(new Turtle(180, 480));
		 enemies.add(new Triangle(360, 480));
		enemies.add(new Flower(690, 480));

		/*
		 * 添加墙块
		 */
		obstructions.add(new Obstruction(120, 360, 6));
		obstructions.add(new Obstruction(300, 360, 7));
		obstructions.add(new Obstruction(360, 360, 6));
		obstructions.add(new Obstruction(420, 360, 7));
		obstructions.add(new Obstruction(480, 360, 6));
		obstructions.add(new Obstruction(540, 360, 7));
		obstructions.add(new Obstruction(600, 360, 6));
		obstructions.add(new Obstruction(660, 360, 7));
		/*
		 * 添加水管
		 */
		obstructions.add(new Obstruction(660, 480, 2));
		obstructions.add(new Obstruction(720, 480, 3));
		obstructions.add(new Obstruction(660, 540, 4));
		obstructions.add(new Obstruction(720, 540, 5));
		Scene scene = new Scene(Img.bgImage, enemies, obstructions, 1, mario);
		return scene;
	}

	/**
	 * TODELETE 测试场景2
	 */
	private Scene testScene2() {
		List<Enemy> enemies = new ArrayList<>();
		List<Obstruction> obstructions = new ArrayList<>();
		obstructions.add(new Obstruction(780, 420, 2));
		obstructions.add(new Obstruction(840, 420, 3));
		obstructions.add(new Obstruction(780, 480, 4));
		obstructions.add(new Obstruction(780, 540, 4));
		obstructions.add(new Obstruction(840, 480, 5));
		obstructions.add(new Obstruction(840, 540, 5));

		obstructions.add(new Obstruction(0, 540, 1));
		obstructions.add(new Obstruction(60, 540, 1));
		obstructions.add(new Obstruction(120, 540, 1));
		obstructions.add(new Obstruction(420, 540, 1));
		obstructions.add(new Obstruction(540, 540, 1));
		obstructions.add(new Obstruction(660, 540, 1));

		obstructions.add(new Obstruction(60, 240, 6));
		obstructions.add(new Obstruction(180, 360, 7));
		obstructions.add(new Obstruction(180, 120, 7));
		obstructions.add(new Obstruction(240, 120, 7));
		Scene scene = new Scene(Img.bgImage, enemies, obstructions, 2, mario);
		return scene;
	}

	/**
	 * TODELETE 测试场景3
	 */
	private Scene testScene3() {
		List<Enemy> enemies = new ArrayList<>();
		/*
		 * 添加地面
		 */
		List<Obstruction> obstructions = new ArrayList<>();
		for (int i = 0; i < 15; i++)
			obstructions.add(new Obstruction(i * 60, 540, 1));
		Scene scene = new Scene(Img.bgImage, enemies, obstructions, 3, mario);
		return scene;
	}
	
	/**
	 * 画面绘制线程
	 *
	 */
	class GameThread extends Thread {

		public void run() {
			// 当游戏不处于暂停或结束情况下则不断循环
			while (!mario.isOver() && !isWin) {
				try {
					Thread.sleep(GameConstant.SLEEPTIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gamePane.repaint();
				if (mario.getX() >= GameConstant.SCENE_DEFAULT_WIDTH - mario.getWidth()) {
					isWin = true;
				}
			
			}
			afterOver();

		}
	}

}
