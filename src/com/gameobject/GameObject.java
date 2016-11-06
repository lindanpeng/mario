package com.gameobject;

import java.awt.image.BufferedImage;

import com.ui.Img;

public abstract class GameObject {
	//起始x坐标
	private int startX;
	//起始y坐标
	private int startY;
	//x坐标
	protected int x;
	//y坐标
	protected int y;
	//硬币宽度
	protected int width;
	//硬币高度
	protected int height;
	//显示的图片
	protected BufferedImage showImage;
	public GameObject(int x,int y){
	      this.startX=x;
	      this.startY=y;
	}
	public void init(){
		this.x=startX;
		this.y=startY;
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
	public BufferedImage getShowImage() {
		return showImage;
	}
	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
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
}
