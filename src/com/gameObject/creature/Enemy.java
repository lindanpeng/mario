package com.gameObject.creature;

import com.config.EnemyConfig;
import com.gameObject.lifelessObject.Obstruction;

/**
 * 敌人抽象类，花、乌龟、蘑菇等敌人都要继承它
 * 
 * @author 林丹
 *
 */
public abstract class Enemy extends Role {
	// 超级玛丽
	protected Mario mario;

	public Enemy(int x, int y) {
		super(x, y, EnemyConfig.DEFAULT_WIDTH, EnemyConfig.DEFAULT_HEIGHT, EnemyConfig.DEFAULT_XSPEED,
				EnemyConfig.DEFAULT_YSPEED);
	}

	/**
	 * 判断是否有障碍物在左边
	 * 
	 * @return
	 */
	public boolean hasObUnderLeft() {
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			if (this.x >= obstruction.getX() && this.x - obstruction.getX() <= this.width
					&& obstruction.getY() - this.y >= this.getHeight()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否有障碍物在右边
	 */
	public boolean hasObUnderRight() {
		synchronized (nowScene.getAllObstructions()) {
			for (Obstruction obstruction : nowScene.getAllObstructions()) {
				if (obstruction.getX() >= this.x && obstruction.getX() - this.x < this.width
						&& obstruction.getY() - this.y >= this.getHeight()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected boolean isCanLeft() {
		// 根据敌人判断是否可以向左移动
		synchronized (nowScene.getAllEnemies()) {
			for (Enemy enemy : nowScene.getAllEnemies()) {
				if (enemy == this)
					continue;
				boolean yCondition = (enemy.getY() <= this.y && this.y - enemy.getY() < enemy.getHeight())
						|| (enemy.getY() > this.y && enemy.getY() - this.y < this.height);
				boolean xCondition = (enemy.getX() <= this.x) && (this.x - enemy.getX() <= enemy.getWidth());
				if (xCondition && yCondition)
					return false;
			}
		}
		return super.isCanLeft();

	}

	@Override
	protected boolean isCanRight() {
		// 根据敌人判断是否可以向右移动

		synchronized (nowScene.getAllEnemies()) {
			for (Enemy enemy : nowScene.getAllEnemies()) {
				if (enemy == this)
					continue;
				boolean yCondition = (enemy.getY() <= this.y && this.y - enemy.getY() < enemy.getHeight())
						|| (enemy.getY() > this.y && enemy.getY() - this.y < this.height);
				boolean xCondition = (enemy.getX() >= this.x) && (enemy.getX() - this.x <= this.getWidth());
				if (xCondition && yCondition)
					return false;
			}
		}
		return super.isCanRight();
	}

	@Override
	protected boolean isOnland() {
		synchronized (nowScene.getAllEnemies()) {
			// 判断是否在敌人上面
			for (Enemy enemy : nowScene.getAllEnemies()) {
				boolean xCondition = (enemy.getX() >= this.x && enemy.getX() - this.x < this.getWidth())
						|| (enemy.getX() <= this.x && this.x - enemy.getX() < enemy.getWidth());

				if ((enemy.getY() > this.y && enemy.getY() - this.y <= this.height) && xCondition) {
					return true;
				}
			}
		}
		return super.isOnland();
	}

	/**
	 * 左移
	 */
	public void leftMove() {
		this.x -= xspeed;
	}

	/**
	 * 右移
	 */
	public void rightMove() {
		this.x += xspeed;
	}

	/**
	 * 上移
	 */
	public void up() {
		this.y -= yspeed;
	}

	/**
	 * 下移
	 */
	public void down() {
		this.y += yspeed;
	}

	/**
	 * 死亡后调用的方法
	 * 
	 */
	protected void afterDead() {
		// 将自己从场景中移除，并添加到被消灭的敌人集合里
		nowScene.getAllEnemies().remove(this);
		nowScene.getRemovedEnemies().add(this);
	}

	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}
}
