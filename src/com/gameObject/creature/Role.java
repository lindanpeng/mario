package com.gameObject.creature;

import java.awt.image.BufferedImage;
import java.util.List;

import com.gameObject.GameObject;
import com.gameObject.lifelessObject.Obstruction;
import com.scene.Scene;

/**
 * 游戏角色类，mario、敌人都要继承该类
 * 
 * @author 林丹
 *
 */
public abstract class Role extends GameObject implements Runnable {
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
	// 标志线程是否暂停
	protected boolean isPause;
	public Role(int x, int y, int width, int height, int xspeed, int yspeed) {
		super(x, y);
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
		isPause = false;
		showImage = images.get(0);
		if (!isStart) {
			new Thread(this).start();
			isStart = true;
		}
	}

	/**
	 * 判断是否可以向左移动
	 */
	protected boolean isCanLeft() {
		// 根据障碍物判断是否可以向左移动
		synchronized (nowScene.getAllObstructions()) {
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			boolean yCondition = (obstruction.getY() <= this.y && this.y - obstruction.getY() < obstruction.getHeight())
					|| (obstruction.getY() > this.y && obstruction.getY() - this.y < this.height);
			boolean xCondition = (obstruction.getX() <= this.x)
					&& (this.x - obstruction.getX() <= obstruction.getWidth());
			if (xCondition && yCondition)
				return false;
		}
		}
		return true;
	}

	/**
	 * 判断是否可以向右移动移动
	 */
	protected boolean isCanRight() {
		synchronized (nowScene.getAllObstructions()) {
			// 根据障碍物判断是否可以向右移动
			for (Obstruction obstruction : nowScene.getAllObstructions()) {
				boolean yCondition = (obstruction.getY() <= this.y
						&& this.y - obstruction.getY() < obstruction.getHeight())
						|| (obstruction.getY() > this.y && obstruction.getY() - this.y < this.height);
				boolean xCondition = (obstruction.getX() >= this.x) && (obstruction.getX() - this.x <= this.getWidth());
				if (xCondition && yCondition)
					return false;
			}
		}
		return true;

	}

	/**
	 * 判断是否撞到障碍物
	 */
	protected boolean isStriken() {
		synchronized (nowScene.getAllObstructions()) {
			for (Obstruction obstruction : nowScene.getAllObstructions()) {
				if (!obstruction.isCanStriken())
					continue;
				boolean xCondition = (obstruction.getX() >= this.x && obstruction.getX() - this.x < this.getWidth())
						|| (obstruction.getX() <= this.x && this.x - obstruction.getX() < obstruction.getWidth());
				boolean yCondition = this.y > obstruction.getY()
						&& this.y - obstruction.getY() < obstruction.getHeight();
				if (xCondition && yCondition) {
					obstruction.beStriken(nowScene);
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 判断是否在障碍物上面
	 */
	protected boolean isOnland() {
		synchronized (nowScene.getAllObstructions()) {
			// 判断是否在地面,TODO 当人物有一半身体站在障碍物上时为true
			for (Obstruction obstruction : nowScene.getAllObstructions()) {
				boolean xCondition = (obstruction.getX() >= this.x&& obstruction.getX() - this.x < this.getWidth())
						|| (obstruction.getX() <= this.x && this.x  - obstruction.getX()< obstruction.getWidth());
                boolean yCondition=(obstruction.getY() > this.y && obstruction.getY() - this.y <= this.height);
				if (yCondition&& xCondition) {

					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 修正人物与障碍物的距离
	 */
	protected void fixDistance() {
		/**
		 * 如果x重叠并且y重叠时修正距离
		 */
		synchronized (nowScene.getAllObstructions()) {

			for (Obstruction obstruction : nowScene.getAllObstructions()) {
				boolean yCondition = (obstruction.getY() >= this.y && obstruction.getY() - this.y < this.height);
				boolean xCondition = (obstruction.getX() >= this.x && obstruction.getX() - this.x < this.width)|| (obstruction.getX()<= this.x && this.x - obstruction.getX() < obstruction.getWidth());
				//当与障碍物重叠时
				if (xCondition && yCondition) {
				if (obstruction.getY()>this.y) 
						this.y = obstruction.getY() - this.height;
				
				else
				if (obstruction.getX() > this.x)
						this.x = obstruction.getX() - this.width;
				else if(obstruction.getX()< this.x)
					this.x = obstruction.getX() + obstruction.getWidth();

				}
			}

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
	protected abstract void showImageWithStatus();
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
