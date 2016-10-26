package com.role;

import java.awt.image.BufferedImage;
import java.util.List;

import com.scene.Scene;
/**
 * 游戏角色类，mario、敌人都要继承该类
 * @author 林丹
 *
 */
public abstract  class Role implements Runnable{
//人物高度
protected int height=60;
//人物宽度
protected int width=60;
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
protected Scene nowScene;
//TODO 水平移动速度
protected int xspeed=10;
//TODO 垂直移动速度
protected int yspeed=10;

public Role(int x,int y){
	startX=x;
	startY=y;
}
/**恢复初始数据,场景重置时调用
 */
public void init(){
	this.x=startX;
	this.y=startY;
	showImage=images.get(0);
	new Thread(this).start();
}
protected  boolean isCanLeft(){
	//TODO 根据障碍物判断是否可以向左移动
	return false;
}
protected  boolean isCanRight(){
	//TODO 根据障碍物判断是否可以向右移动
	return false;
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

public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
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
public Scene getNowScene() {
	return nowScene;
}
public void setNowScene(Scene nowScene) {
	this.nowScene = nowScene;
}
public int getXspeed() {
	return xspeed;
}
public void setXspeed(int xspeed) {
	this.xspeed = xspeed;
}
public int getYspeed() {
	return yspeed;
}
public void setYspeed(int yspeed) {
	this.yspeed = yspeed;
}

}
