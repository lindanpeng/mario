package com.gameobject;

import java.awt.image.BufferedImage;

import com.ui.Img;

public abstract class GameObject {
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
		this.x=x;
		this.y=y;
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

}
