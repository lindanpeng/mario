package com.obstruction;

import java.awt.image.BufferedImage;
/**
 * 障碍物
 * @author 林丹
 *
 */
public class Obstruction {
	//障碍物高度
	private int height=60;
	//障碍物宽度
	private int width=60;
	// x坐标
	private int x;
	// y坐标
	private int y;
	// 当前显示的图片
	private BufferedImage showImage;
	//障碍物类型
	private int type;
	public Obstruction(int x, int y,int type) {
      this.x=x;
      this.y=y;
      this.type=type;
	}

	/**
	 * 初始化各种数据
	 */
	public void init() {
          
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
     
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

}
