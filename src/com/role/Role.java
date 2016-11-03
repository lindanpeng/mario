package com.role;

import java.awt.image.BufferedImage;
import java.util.List;

import com.gameobject.Coin;
import com.gameobject.Obstruction;
import com.scene.Scene;
import com.ui.Img;

/**
 * 游戏角色类，mario、敌人都要继承该类
 * 
 * @author 林丹
 *
 */
public abstract class Role implements Runnable {
	// 人物高度
	protected int height;
	// 人物宽度
	protected int width;
	// x坐标
	protected int x;
	// y坐标
	protected int y;
	// 起始x坐标
	protected int startX;
	// 起始y坐标
	protected int startY;
	// 当前显示的图片
	protected BufferedImage showImage;
	// 全部图片
	protected List<BufferedImage> images;
	// 所处的场景
	protected Scene nowScene;
	// TODO 水平移动速度
	protected int xspeed;
	// TODO 垂直移动速度
	protected int yspeed;
	// 标志线程是否开始
	protected boolean isStart;
	// 标志线程是否结束（完全死亡）
	protected boolean isOver;
	// 标志是否死亡
	protected boolean isDead;
	//标志线程是否暂停
	protected boolean isPause;
	public Role(int x, int y) {
		this(x, y, 60, 60);
	}

	public Role(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10);
	}

	public Role(int x, int y, int width, int height, int xspeed, int yspeed) {
		this.startX = x;
		this.startY = y;
		this.width = width;
		this.height = height;
		this.xspeed = xspeed;
		this.yspeed = yspeed;
	}

	/**
	 * 恢复初始数据,场景重置时调用
	 */
	public void init() {
		this.x = startX;
		this.y = startY;
		isOver = false;
		isDead = false;
		isPause=false;
		showImage = images.get(0);
		if (!isStart) {
			new Thread(this).start();
			isStart = true;
		}
	}

	/**
	 * 判断是否可以向左移动
	 */
	protected synchronized boolean isCanLeft() {
		// 根据障碍物判断是否可以向左移动
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			boolean yCondition = (obstruction.getY() <= this.y && this.y - obstruction.getY() < obstruction.getHeight())
					|| (obstruction.getY() > this.y && obstruction.getY() - this.y < this.height);
			boolean xCondition = (obstruction.getX() <= this.x)
					&& (this.x - obstruction.getX() <= obstruction.getWidth());
			if (xCondition && yCondition)
				return false;
		}
		// 根据敌人判断是否可以向左移动
		for (Enemy enemy : nowScene.getAllEnemies()) {
			if (enemy == this)
				continue;
			boolean yCondition = (enemy.getY() <= this.y && this.y - enemy.getY() < enemy.getHeight())
					|| (enemy.getY() > this.y && enemy.getY() - this.y < this.height);
			boolean xCondition = (enemy.getX() <= this.x) && (this.x - enemy.getX() <= enemy.getWidth());
			if (xCondition && yCondition)
				return false;
		}
		return true;
	}

	/**
	 * 判断是否可以向右移动移动
	 */
	protected synchronized boolean isCanRight() {
		// 根据障碍物判断是否可以向右移动
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			boolean yCondition = (obstruction.getY() <= this.y && this.y - obstruction.getY() < obstruction.getHeight())
					|| (obstruction.getY() > this.y && obstruction.getY() - this.y < this.height);
			boolean xCondition = (obstruction.getX() >= this.x) && (obstruction.getX() - this.x <= this.getWidth());
			if (xCondition && yCondition)
				return false;
		}
		// 根据敌人判断是否可以向右移动
		for (Enemy enemy : nowScene.getAllEnemies()) {
			if (enemy == this)
				continue;
			boolean yCondition = (enemy.getY() <= this.y && this.y - enemy.getY() < enemy.getHeight())
					|| (enemy.getY() > this.y && enemy.getY() - this.y < this.height);
			boolean xCondition = (enemy.getX() >= this.x) && (enemy.getX() - this.x <= this.getWidth());
			if (xCondition && yCondition)
				return false;
		}
		return true;
	}

	/**
	 * 判断是否撞到障碍物
	 */
	protected synchronized boolean isStriken() {
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			boolean xCondition = (obstruction.getX() >= this.x && obstruction.getX() - this.x < this.getWidth())
					|| (obstruction.getX() <= this.x && this.x - obstruction.getX() < obstruction.getWidth());
			boolean yCondition = this.y > obstruction.getY() && this.y - obstruction.getY() < obstruction.getHeight();
			if (xCondition && yCondition) {
			     obstruction.beStriken(nowScene);
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否在障碍物上面
	 */
	protected synchronized boolean isOnland() {
		// 判断是否在地面
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			boolean xCondition = (obstruction.getX() >= this.x && obstruction.getX() - this.x < this.getWidth())
					|| (obstruction.getX() <= this.x && this.x - obstruction.getX() < obstruction.getWidth());

			if ((obstruction.getY() > this.y && obstruction.getY() - this.y <= this.height) && xCondition) {
				return true;
			}
		}
		// 判断是否在敌人上面
		for (Enemy enemy : nowScene.getAllEnemies()) {
			boolean xCondition = (enemy.getX() >= this.x && enemy.getX() - this.x < this.getWidth())
					|| (enemy.getX() <= this.x && this.x - enemy.getX() < enemy.getWidth());

			if ((enemy.getY() > this.y && enemy.getY() - this.y <= this.height) && xCondition) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 修正人物与障碍物的距离
	 */
	protected synchronized void fixDistance() {
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			boolean yCondition = (obstruction.getY() > this.y && obstruction.getY() - this.y < this.height);
			boolean xCondition = (obstruction.getX() >= this.x && obstruction.getX() - this.x < this.getWidth())
					|| (obstruction.getX() <= this.x && this.x - obstruction.getX() < obstruction.getWidth());
			if ((obstruction.getX() > this.x && obstruction.getX() - this.x < this.width) && yCondition)
				this.x = obstruction.getX() - this.width;
			if (obstruction.getX() < this.x && this.x - obstruction.getX() < obstruction.getWidth() && yCondition)
				this.x = obstruction.getX() + obstruction.getWidth();
			if (xCondition && yCondition)
				this.y = obstruction.getY() - this.height;

		}
	}

	/**
	 * 死亡后执行的方法
	 */
	protected abstract void afterDead();

	/**
	 * 判断是否死亡
	 * 
	 * @return
	 */
	protected abstract boolean isDead();

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

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

}
