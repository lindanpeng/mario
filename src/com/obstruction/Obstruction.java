package com.obstruction;

import java.awt.image.BufferedImage;
/**
 * 障碍物
 * @author 林丹
 *
 */
public class Obstruction {
	// x坐标
	private int x;
	// y坐标
	private int y;
	// 当前显示的图片
	private BufferedImage showImage;

	public Obstruction(int x, int y) {
      this.x=x;
      this.y=y;
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

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

}
