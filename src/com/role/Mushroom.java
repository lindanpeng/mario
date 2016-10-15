package com.role;
/**
 * 蘑菇敌人
 * @author 灿坚
 *
 */
public class Mushroom extends Enemy {

	public Mushroom(int x, int y) {
		super(x, y);
		
	}

	@Override
	public void run() {
		while(true){
	  /*1.判断是否碰到障碍物（改变移动方向）
	   *2.判断是否死亡（死亡后处理）
	   *
	   *     
	   */
		}
	}

	@Override
	protected boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

}
