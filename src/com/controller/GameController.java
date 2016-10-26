package com.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.constant.GameConstant;
import com.listener.KeyCodeAndType;
import com.listener.MyKeyListenter;
import com.obstruction.Obstruction;
import com.role.Enemy;
import com.role.Mario;
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
		// 载入图片资源
		Img.init();
		// 获取游戏场景数据并创建对象
		scenes = new ArrayList<>();
		scenes.add(testScene1());
		scenes.add(testScene2());
		scenes.add(testScene3());
		scenes.add(testScene4());
		// 设置场景1
		firstScene = scenes.get(0);
		// 设置场景2
		secondScene = scenes.get(1);
		//设置第二个场景的起始x坐标在画面之外
		secondScene.setX(GameConstant.SCENE_DEFAULT_WIDTH);
		// 获取mario数据并创建对象
		mario = new Mario(0, 480, 3,this);
		myKeyListenter = new MyKeyListenter(mario);
		gamePane.setFirstScene(firstScene);
		gamePane.setSecondScene(secondScene);
		gamePane.setMario(mario);
		mario.setFirstScene(firstScene);
		mario.setSecondScene(secondScene);
		mario.setNowScene(firstScene);
		mario.setLastSceneSort(scenes.size());
		marioAction = new HashMap<KeyCodeAndType, Method>();
		try {
			marioAction.put(new KeyCodeAndType(37, 0), mario.getClass().getMethod("leftMove"));
			marioAction.put(new KeyCodeAndType(39, 0), mario.getClass().getMethod("rightMove"));

		} catch (NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 开始游戏,启动各个线程
	 */
	public void startGame() {
		// TODO 为了不消耗资源,改为每次启动两个场景的线程
		for (Scene scene : scenes)
			scene.reset();
		// 初始化mario对象，启动线程
		mario.init();
		// 启动画面绘制线程
		new GameThread().start();
		// 安装玩家游戏控制器
		gamePane.addKeyListener(myKeyListenter);

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
		}
		else{
			mario.setFirstScene(mario.getSecondScene());
			mario.setLastSceneSort(mario.getNowScene().getSort());
		}
	}

	/**
	 * 游戏结束后处理
	 */
	public void afterOver() {

	}

	/**
	 * 判断游戏是否结束
	 * 
	 */
	private boolean isOver() {
		return false;
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
		/*
		 * TODO 添加敌人
		 * 
		 * enemies.add(new Mushroom(180, 480)); enemies.add(new Flower(690,
		 * 480));
		 */
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
		Scene scene = new Scene(Img.bgIamge, enemies, obstructions, 1);
		return scene;
	}

	/**
	 * TODELETE 测试场景2
	 */
	private Scene testScene2() {
		List<Enemy> enemies = new ArrayList<>();
		/*
		 * 添加地面
		 */
		List<Obstruction> obstructions = new ArrayList<>();
		for (int i = 0; i < 15; i++)
			obstructions.add(new Obstruction(i * 60, 540, 1));
		/*
		 * TODO 添加敌人
		 * 
		 * enemies.add(new Mushroom(180, 480)); enemies.add(new Flower(690,
		 * 480));
		 */
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
		Scene scene = new Scene(Img.bgIamge, enemies, obstructions, 2);
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
		/*
		 * TODO 添加敌人
		 * 
		 * enemies.add(new Mushroom(180, 480)); enemies.add(new Flower(690,
		 * 480));
		 */
		/*
		 * 添加墙块
		 */
		Scene scene = new Scene(Img.bgIamge, enemies, obstructions, 3);
		return scene;
	}
	/**
	 * TODELETE 测试场景4
	 */
	private Scene testScene4() {
		List<Enemy> enemies = new ArrayList<>();
		/*
		 * 添加地面
		 */
		List<Obstruction> obstructions = new ArrayList<>();
		for (int i = 0; i < 15; i++)
			obstructions.add(new Obstruction(i * 60, 540, 1));
		/*
		 * TODO 添加敌人
		 * 
		 * enemies.add(new Mushroom(180, 480)); enemies.add(new Flower(690,
		 * 480));
		 */
		/*
		 * 添加墙块
		 */
		Scene scene = new Scene(Img.bgIamge, enemies, obstructions, 4);
		return scene;
	}
	/**
	 * 画面绘制线程
	 *
	 */
	class GameThread extends Thread {

		public void run() {
			// 当游戏不处于暂停或结束情况下则不断循环
			while (!isOver() && !isPause) {
				gamePane.repaint();
				try {
					Thread.sleep(GameConstant.SLEEPTIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
