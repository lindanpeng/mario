package com.role;

import com.gameobject.Obstruction;

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
		super(x, y,60,60,5,3);
	}
	/**
	 * 判断是否有障碍物在左边
	 * @return
	 */
	public boolean isObsInLeft(){
		for(Obstruction obstruction:nowScene.getAllObstructions()){
           if (this.x-obstruction.getX()==obstruction.getWidth()&&obstruction.getY()-this.y==this.getHeight()) {
			return true;
		}
		}
		return false;
	}
	/**
	 * 判断是否有障碍物在右边
	 */
	public boolean isObsInRight(){
		for(Obstruction obstruction:nowScene.getAllObstructions()){
		  if (obstruction.getX()-this.x==obstruction.getWidth()&&obstruction.getY()-this.y==this.getHeight()) {
				return true;
			}
		}
		  return false;
	}
	/**
	 * 左移
	 */
    public void leftMove(){
    	this.x-=xspeed;
    }
    /**
     * 右移
     */
    public void rightMove(){
    	this.x+=xspeed;
    }
    /**
     * 上移
     */
    public void up(){
    	this.y-=yspeed;
    }
    /**
     * 下移
     */
    public  void down() {
		this.y+=yspeed;
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
