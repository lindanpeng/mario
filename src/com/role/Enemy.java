package com.role;

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
		super(x, y);
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
