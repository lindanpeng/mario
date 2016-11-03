package com.role;

import com.constant.DeadType;
import com.constant.GameConstant;
import com.ui.Img;

/**
 * 蘑菇敌人
 * 
 * @author 灿坚
 *
 */
public class Triangle extends Enemy {
	// 辅助显示图片
	private int posture;
    //标志是否可以右移
	private boolean isRightMoving;
	public Triangle(int x, int y) {
		super(x, y);
		images = Img.allMushroomImage;
	}
    @Override
    public void init(){
    	super.init();
    	isRightMoving=false;
    	posture=0;
     	this.yspeed=10;
    }
	/**
	 * 根据状态显示图片，注意移动的时候图片要不断切换
	 */
	private void showImageWithStatus() {
		posture = (++posture) % 6;
		showImage = images.get(posture < 3 ? 0 : 1);
	}

	@Override
	protected synchronized boolean isDead() {
		if(mario.isFalling()&&((this.y>mario.y&&this.y-mario.getY()<=this.height)&&((this.x>=mario.getX()&&this.x-mario.getX()<this.width)||(this.x<mario.getX()&&mario.getX()-this.x<=mario.getWidth()))))
		{
			  mario.afterKillEnemy(DeadType.PRESS);
	          return true;
		}
		for (Enemy enemy : nowScene.getAllEnemies()) {
			if ((enemy instanceof Turtle)&&enemy.isDead){

			boolean xCondition = (enemy.getX() <= this.x && this.x - enemy.getX() <= enemy.getWidth())
					|| (enemy.getX() > this.x && enemy.getX() - this.x < this.width);
			boolean yCondition =(enemy.getY() <= this.y && this.y - enemy.getY() < enemy.getHeight())
						|| (enemy.getY() > this.y &&  enemy.getY() - this.y <= enemy.getHeight());
			if (xCondition && yCondition) {			
				return true;
			}
		}
		}
		return false;
	}
   @Override
   protected void afterDead() {	 
	   isOver=true;
	   isStart=false;
	   isDead=true;
	   showImage=images.get(2);
	   try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	  
		  super.afterDead();
   }
	@Override
	public void run() {
		while (!isOver) {
			
            try {
				Thread.sleep(GameConstant.SLEEPTIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            if(isPause){
            	continue;
            }
            //根据状态显示图片
            showImageWithStatus();
            //判断是否死亡
            if(isDead()){
            	afterDead();
            }
            //判断是否在地上
            if(!isOnland())
            	{
            	  down();
            	  continue;
            	}
			// 是否可以左向移动的标志
			boolean canLeft = isCanLeft()&&this.x>=nowScene.getX();
			// 是否可以右向移动的标志
			boolean canRight =isCanRight()&&this.x<nowScene.getX()+nowScene.getWidth()-this.width;

			if (canRight&&isRightMoving)
				   rightMove();	
			else if (canLeft&&!isRightMoving)
				   leftMove();
			else{
				isRightMoving=!isRightMoving;
			}

		}
	}

}
