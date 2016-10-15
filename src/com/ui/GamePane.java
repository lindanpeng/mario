package com.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * 游戏运行面板
 * @author 林创斌
 *
 */

import com.role.Enemy;
import com.role.Mario;
import com.scene.Scene;
public class GamePane extends JPanel {
//游戏场景
private Scene scene;
//超级玛丽
private Mario mario;
//
private int width;
private int height;
public GamePane(int width,int height){
	
}
/**
 * 绘制画面
 */
@Override
public void paint(Graphics g){
	BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics bg=image.getGraphics();
	//绘制敌人
	for(Enemy enemy:scene.getAllEnemies()){
		bg.drawImage(enemy.getShowImage(),enemy.getX(),enemy.getY(),null);
	}
	//TODO 绘制障碍物
	
	//TODO 绘制mario
	g.drawImage(image, 0, 0, null);
}
public void setScene(Scene scene) {
	this.scene = scene;
}
public void setMario(Mario mario) {
	this.mario = mario;
}

}
