package com.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * 游戏运行面板
 * @author 林创斌
 *
 */

import com.gameObject.GameObject;
import com.gameObject.creature.Enemy;
import com.gameObject.creature.Mario;
import com.gameObject.lifelessObject.Obstruction;
import com.resource.Img;
import com.scene.Scene;
import com.util.TimeUtil;

public class GamePane extends JPanel {
	private static final long serialVersionUID = 1L;
	// 游戏场景1
	private Scene firstScene;
	// 游戏场景2
	private Scene secondScene;
	// 超级玛丽
	private Mario mario;
	// 画面宽度
	private int width;
	// 画面高度
	private int height;
	// 游戏开始时间
	private long startTime;
    private int times;
	public GamePane(int width, int height) {
		this.width = width;
		this.height = height;
		this.setBounds(0, 0, width, height);
		this.setSize(width, height);
	}

	/**
	 * 绘制画面
	 */
	@Override
	public void paint(Graphics g) {           		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics bg = image.getGraphics();
		// 设置文本字体
		bg.setFont(new Font("微软雅黑", Font.BOLD, 20));
		/* ================场景一===================== */
		drawScene(firstScene, bg);
		/* ================场景二===================== */
		drawScene(secondScene, bg);
		if(firstScene==secondScene)
		//绘制大魔王
		bg.drawImage(Img.monsterImage.get(times++%Img.MONSTER_IMAGES_NUM),500+300,150+0,500+0,150+396,0,0,300,396,null);
		// 绘制超级玛丽
		bg.drawImage(mario.getShowImage(), mario.getX(), mario.getY(),mario.getX()+mario.getWidth(),mario.getY()+mario.getHeight(),
				     0,0,mario.getShowImage().getWidth(),mario.getShowImage().getHeight(), null);
		// 绘制生命数
		bg.drawString("生命数：" + mario.getLife(), 10, 20);
		// 绘制分数
		bg.drawString("分数：" + mario.getScore(), 150, 20);
		// 绘制敌人
		bg.drawString("已用时间：" + TimeUtil.getInterval(startTime), 250, 20);

	
		
		
		g.drawImage(image, 0, 0, null);
	}

	private void drawScene(Scene scene, Graphics bg) {

		// 绘制背景
		bg.drawImage(scene.getBackground(), scene.getX(), scene.getY(), null);
		
		synchronized (scene.getAllEnemies()) {
			// 绘制敌人
		for (Enemy enemy : scene.getAllEnemies()) {
			bg.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), null);
		}
		}
		synchronized (scene.getAllObstructions()) {
		// 绘制障碍物
		for (Obstruction obstruction : scene.getAllObstructions()) {
			bg.drawImage(obstruction.getShowImage(), obstruction.getX(), obstruction.getY(), null);
		}
		}
		synchronized (scene.getAllObjects()){
		// 绘制金币和蘑菇
		for (GameObject obj : scene.getAllObjects()) {
			bg.drawImage(obj.getShowImage(), obj.getX(), obj.getY(), null);
		}
		}
	}

	public void setFirstScene(Scene firstScene) {
		this.firstScene = firstScene;
	}

	public void setSecondScene(Scene secondScene) {
		this.secondScene = secondScene;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

}
