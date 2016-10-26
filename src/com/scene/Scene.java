package com.scene;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.constant.GameConstant;
import com.obstruction.Obstruction;
import com.role.Enemy;

public class Scene {
//场景x坐标
private int x=0;
//场景y坐标
private int y=0;
//场景宽度
private int width=GameConstant.SCENE_DEFAULT_WIDTH;
//场景高度
private int height=GameConstant.SCENE_DEFAULT_HEIGHT;
//场景顺序
private int sort;
//场景水平移动速度
private int xspeed=10;
//背景图片
private BufferedImage background;
//当前在场景中的所有敌人
private List<Enemy> allEnemies;
//当前在场景中的所有障碍物
private List<Obstruction> allObstructions;
//所有被消灭的敌人
private List<Enemy> removedEnemies=new ArrayList<>();
//所有被消灭的障碍物
private List<Obstruction> removedObstructions=new ArrayList<>();
public Scene(BufferedImage background,List<Enemy> enemies,List<Obstruction> obstructions,int sort){
	this.background=background;
	this.allEnemies=enemies;
	this.allObstructions=obstructions;
	this.sort=sort;
}
/**
 * 场景重置
 */
public void reset(){
	allEnemies.addAll(removedEnemies);
	allObstructions.addAll(removedObstructions);
	removedEnemies.clear();
	removedObstructions.clear();
	for(Enemy enemy:allEnemies){
		enemy.init();
	}
	for(Obstruction obstruction:allObstructions){
		obstruction.init();
	}
}
/**
 * 场景左移
 */
public void leftMove(){
	this.x-=xspeed;
	for(Enemy enemy:allEnemies){
		enemy.setX(enemy.getX()-xspeed);
	}
	for(Obstruction obstruction:allObstructions){
		obstruction.setX(obstruction.getX()-xspeed);
	}
}
/**
 * 场景右移动
 */
public void rightMove(){
	
}
public BufferedImage getBackground() {
	return background;
}
public void setBackground(BufferedImage background) {
	this.background = background;
}
public List<Enemy> getAllEnemies() {
	return allEnemies;
}
public void setAllEnemies(List<Enemy> allEnemies) {
	this.allEnemies = allEnemies;
}
public List<Obstruction> getAllObstructions() {
	return allObstructions;
}
public void setAllObstructions(List<Obstruction> allObstructions) {
	this.allObstructions = allObstructions;
}
public List<Enemy> getRemovedEnemies() {
	return removedEnemies;
}
public void setRemovedEnemies(List<Enemy> removedEnemies) {
	this.removedEnemies = removedEnemies;
}
public List<Obstruction> getRemovedObstructions() {
	return removedObstructions;
}
public void setRemovedObstructions(List<Obstruction> removedObstructions) {
	this.removedObstructions = removedObstructions;
}
public int getX() {
	return x;
}
public void setX(int x) {
	for(Enemy enemy:allEnemies){
		enemy.setX(enemy.getX()+x);
	}
	for(Obstruction obstruction:allObstructions){
		obstruction.setX(obstruction.getX()+x);
	}
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public int getSort() {
	return sort;
}
public void setSort(int sort) {
	this.sort = sort;
}

}
