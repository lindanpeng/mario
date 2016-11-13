package com.gameObject.creature;

import com.config.SystemConfig;
import com.constant.DeadType;
import com.controller.GameController;
import com.resource.Img;

/**
 * 蘑菇敌人
 * 
 * @author 灿坚
 *
 */
public class Triangle extends Enemy {
	// 辅助显示图片
	private int posture;
	// 标志是否可以右移
	private boolean isRightMoving;

	public Triangle(int x, int y) {
		super(x, y);
		images = Img.allMushroomImage;
	}

	@Override
	public void init() {
		super.init();
		isRightMoving = false;
		posture = 0;
	}

	/**
	 * 根据状态显示图片，注意移动的时候图片要不断切换
	 */
	protected void showImageWithStatus() {
		posture = (++posture) % 6;
		showImage = images.get(posture < 3 ? 0 : 1);
	}

	@Override
	protected synchronized boolean isDead() {
		if (mario.isFalling() && ((this.y > mario.getY() && this.y - mario.getY() <= this.height)
				&& ((this.x >= mario.getX() && this.x - mario.getX() < this.width)
						|| (this.x < mario.getX() && mario.getX() - this.x <= mario.getWidth())))) {
			mario.afterKillEnemy(DeadType.PRESS);
			return true;
		}
		for (Enemy enemy : nowScene.getAllEnemies()) {
			if ((enemy instanceof Turtle) && enemy.isDead) {

				boolean xCondition = (enemy.getX() <= this.x && this.x - enemy.getX() <= enemy.getWidth())
						|| (enemy.getX() > this.x && enemy.getX() - this.x < this.width);
				boolean yCondition = (enemy.getY() <= this.y && this.y - enemy.getY() < enemy.getHeight())
						|| (enemy.getY() > this.y && enemy.getY() - this.y <= enemy.getHeight());
				if (xCondition && yCondition) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected void afterDead() {
		isOver = true;
		isStart = false;
		isDead = true;
		showImage = images.get(2);
		try {
			Thread.sleep(SystemConfig.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.afterDead();
	}

	@Override
	public void run() {
		try {
			while (!isOver) {
				Thread.sleep(SystemConfig.SLEEP_TIME);

				if (isPause) {
					synchronized (GameController.THREAD_OBJECT) {
						GameController.THREAD_OBJECT.wait();
					}
				}
				// 根据状态显示图片
				showImageWithStatus();
				// 判断是否死亡
				if (isDead()) {
					afterDead();
				}
				// 判断是否在地上
				if (!isOnland()) {
					down();
					continue;
				}
				// 是否可以左向移动的标志
				boolean canLeft = isCanLeft() && this.x >= nowScene.getX()&&hasObUnderLeft();
				// 是否可以右向移动的标志
				boolean canRight = isCanRight() && this.x < nowScene.getX() + nowScene.getWidth() - this.width&&hasObUnderRight();

				if (canRight && isRightMoving)
					rightMove();
				else if (canLeft && !isRightMoving)
					leftMove();
				else {
					isRightMoving = !isRightMoving;
				}

			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
