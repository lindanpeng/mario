package com.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.constant.CommonConstant;
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
 * @author 林丹
 *
 */
public class GameController {
//游戏窗口
private GameFrame gameFrame;
//游戏运行面板
private GamePane gamePane;
//所有场景
private List<Scene> scenes;
//当前场景
private Scene nowScene;
//超级玛丽
private Mario mario;
//玩家控制器
private MyKeyListenter myKeyListenter;
//游戏是否暂停标志
private boolean isPause;
//mario行为控制
private Map<KeyCodeAndType,Method> marioAction;
public GameController(GameFrame gameFrame){
	this.gameFrame=gameFrame;
	this.gamePane=gameFrame.getGamePane();
	myKeyListenter=new MyKeyListenter(mario);

	this.init();
}
/**
 * 初始化方法,载入资源以及数据,建立游戏人物场景
 */
public void init(){
	//载入图片资源
	 Img.init();
	//获取游戏场景数据并创建对象
	 scenes=new ArrayList<>();
     scenes.add(testScene1());
     //设置当前场景
     nowScene=scenes.get(0);
	//获取mario数据并创建对象
	 mario=new Mario(0,480, 3);
	 
	 gamePane.setScene(nowScene);
	 gamePane.setMario(mario);
}

/**
 * 开始游戏,启动各个线程
 */
public void startGame(){
	//TODO 为了不消耗资源,改为每次启动两个场景的线程  
	 for(Scene scene:scenes)
		 scene.reset();
	 //初始化mario对象，启动线程
	 mario.init();
	 //启动画面绘制线程
	 new GameThread().start();
	 //安装玩家游戏控制器
	 gamePane.addKeyListener(myKeyListenter);

}
/**
 * 进入下一个场景
 */
public void nextScene(){
	
}
/**
 * 游戏结束后处理
 */
public void afterOver(){
	
}
/**
 * 判断游戏是否结束
 * 
 */
private boolean isOver(){
	return false;
}
/** TODELETE
 * 测试场景1
 */
private Scene testScene1(){
    List<Enemy> enemies=new ArrayList<>();	
    /*
     * 添加地面
     */
    List<Obstruction> obstructions=new ArrayList<>();
	for (int i = 0; i < 15; i++)
		obstructions.add(new Obstruction(i * 60, 540, 1));
	/*
	 * TODO 添加敌人
	 
	enemies.add(new Mushroom(180, 480));	
	enemies.add(new Flower(690, 480));*/
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
	Scene scene=new Scene(Img.bgIamge, enemies, obstructions);
	return scene;
}
/**
 * 画面绘制线程
 *
 */
  class GameThread extends Thread {
	
	public void run() {
		//当游戏不处于暂停或结束情况下则不断循环
		while(!isOver()&&!isPause){
			gamePane.repaint();
			try {
				Thread.sleep(CommonConstant.sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
}
