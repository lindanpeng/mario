package com.role;

import java.awt.image.BufferedImage;
import java.util.List;

import com.scene.Scene;
/**
 * 敌人抽象类，花、乌龟、蘑菇等敌人都要继承它
 * @author 林丹
 *
 */
public abstract  class Enemy implements Runnable{
//x坐标
protected int x;
//y坐标
protected int y;
//起始x坐标
protected int startX;
//起始y坐标
protected  int startY;
//当前显示的图片
protected BufferedImage showImage;
//全部图片
protected List<BufferedImage> images;
//所处的场景
protected Scene scene;
//移动速度
protected int speed;
//超级玛丽
protected Mario mario;
public Enemy(int x,int y){
	startX=x;
	startY=y;
}
/**初始化各种数据,场景重置时调用
 */
public void init(){
	this.x=startX;
	this.y=startY;
	showImage=images.get(0);
}
/**死亡后调用的方法
 * 
 */
public void afterDead(){
	//将自己从场景中移除，并添加到被消灭的敌人集合里
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int getStartX() {
	return startX;
}
public void setStartX(int startX) {
	this.startX = startX;
}
public int getStartY() {
	return startY;
}
public void setStartY(int startY) {
	this.startY = startY;
}
public BufferedImage getShowImage() {
	return showImage;
}
public void setShowImage(BufferedImage showImage) {
	this.showImage = showImage;
}
public List<BufferedImage> getImages() {
	return images;
}
public void setImages(List<BufferedImage> images) {
	this.images = images;
}
public Scene getScene() {
	return scene;
}
public void setScene(Scene scene) {
	this.scene = scene;
}
public int getSpeed() {
	return speed;
}
public void setSpeed(int speed) {
	this.speed = speed;
}
public Mario getMario() {
	return mario;
}
public void setMario(Mario mario) {
	this.mario = mario;
}

}
