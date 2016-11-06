package com.scene;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.config.ScenesConfig;
import com.gameobject.GameObject;
import com.gameobject.Obstruction;
import com.role.Enemy;
import com.role.Mario;

public class Scene {
//场景x坐标
private int x;
//场景y坐标
private int y;
//场景宽度
private int width=ScenesConfig.SCENE_WIDTH;
//场景高度
private int height=ScenesConfig.SCENE_HEIGHT;
//场景顺序
private int sort;
//场景水平移动速度
private int xspeed=ScenesConfig.XSPEED;
//背景图片
private BufferedImage background;
//当前在场景中的所有敌人
private List<Enemy> allEnemies;
//当前在场景中的所有障碍物
private List<Obstruction> allObstructions;
//所有被消灭的敌人
private List<Enemy> removedEnemies=Collections.synchronizedList(new ArrayList<Enemy>());
//所有被消灭的障碍物
private List<Obstruction> removedObstructions=Collections.synchronizedList(new ArrayList<Obstruction>());
//所有在当前场景的硬币
private List<GameObject> allObjects=Collections.synchronizedList(new ArrayList<GameObject>());
//mario
private Mario mario;
public Scene(BufferedImage background,List<Enemy> enemies,List<Obstruction> obstructions,int sort){
	allEnemies=Collections.synchronizedList(enemies);
	allObstructions=Collections.synchronizedList(obstructions);
	this.background=background;
	this.sort=sort;
}
public Scene(BufferedImage background,List<Enemy> enemies,List<Obstruction> obstructions,int sort,Mario mario){
	allEnemies=Collections.synchronizedList(enemies);
	allObstructions=Collections.synchronizedList(obstructions);
	this.background=background;
	this.sort=sort;
	this.mario=mario;
}
/**
 * 场景重置
 */
public void reset(){
	allEnemies.addAll(removedEnemies);
	allObstructions.addAll(removedObstructions);
	allObjects.clear();
	removedEnemies.clear();
	removedObstructions.clear();
	for(Enemy enemy:allEnemies){
		enemy.setNowScene(this);
		enemy.setMario(mario);
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
	for(GameObject obj:allObjects){
		obj.setX(obj.getX()-xspeed);
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
		enemy.setX(enemy.getStartX()+x);
	}
	for(Obstruction obstruction:allObstructions){
		obstruction.setX(obstruction.getStartX()+x);
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

public List<GameObject> getAllObjects() {
	return allObjects;
}
public void setAllObjects(List<GameObject> allObjects) {
	this.allObjects = allObjects;
}
public void pauseScene(boolean status) {
	for(Enemy enemy:allEnemies){
		enemy.setPause(status);
	}
}
public Mario getMario() {
	return mario;
}
public void setMario(Mario mario) {
	this.mario = mario;
}

}
