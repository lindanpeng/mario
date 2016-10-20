package com.scene;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.obstruction.Obstruction;
import com.role.Enemy;

public class Scene {
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
public Scene(BufferedImage background,List<Enemy> enemies,List<Obstruction> obstructions){
	this.background=background;
	this.allEnemies=enemies;
	this.allObstructions=obstructions;
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

}
