package com.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * 游戏运行面板
 * @author 林创斌
 *
 */

import com.obstruction.Obstruction;
import com.role.Enemy;
import com.role.Mario;
import com.scene.Scene;
import com.util.TimeUtil;

public class GamePane extends JPanel {
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
	// 游戏开始标志
	private boolean isStart;

	public GamePane(int width, int height) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
	}

	/**
	 * 绘制画面
	 */
	@Override
	public void paint(Graphics g) {
		if (!isStart)
			return;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics bg = image.getGraphics();
		/* ================场景一===================== */
		drawScene(firstScene, bg);
		/* ================场景二===================== */
		drawScene(secondScene, bg);
		// 绘制超级玛丽
		bg.drawImage(mario.getShowImage(), mario.getX(), mario.getY(), null);
		g.drawImage(image, 0, 0, null);
	}

	private void drawScene(Scene scene, Graphics bg) {
		// 绘制背景
		bg.drawImage(scene.getBackground(), scene.getX(), scene.getY(), null);
		// 绘制敌人
		for (Enemy enemy : scene.getAllEnemies()) {
			bg.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), null);
		}
		// 绘制障碍物
		for (Obstruction obstruction : scene.getAllObstructions()) {
			bg.drawImage(obstruction.getShowImage(), obstruction.getX(), obstruction.getY(), null);
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

	public void setIsStart(Boolean isStart) {
		this.isStart = isStart;
	}

}
