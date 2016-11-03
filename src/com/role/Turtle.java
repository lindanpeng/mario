package com.role;

import com.constant.DeadType;
import com.constant.GameConstant;
import com.ui.Img;

/**
 * 乌龟敌人
 * 
 * @author 灿坚
 *
 */
public class Turtle extends Enemy {
	// 辅助显示图片
	private int posture;
	// 标志是否可以右移,初始向右移
	private boolean isRightMoving;
    //标志是否变成龟壳
	private boolean isShelling;
	public Turtle(int x, int y) {
		super(x, y);
		images = Img.allTurtleImage;
	
	}
    @Override
    public void init(){
    	super.init();
    	isRightMoving=true;
    	posture=0;
    	xspeed=5;
     	this.yspeed=10;
    }
	private void showImageWithStatus() {
		if (!isDead) {
			if (!isRightMoving) {
				posture = (++posture) % 6;
			} else {
				posture = (++posture) % 6 + 6;
			}

			if (posture < 6)
				showImage = images.get(posture < 3 ? 0 : 1);// 显示状态图
			else
				showImage = images.get(posture < 9 ? 2 : 3);// 显示状态图
		} else {
			posture = 4;
			showImage = images.get(posture);// 显示状态图
		}
	}

	@Override
	protected boolean isDead() {
		if (mario.isFalling()&&(this.y - mario.getY() <= this.height && ((this.x >= mario.getX() && this.x - mario.getX() < this.width)
				|| (this.x < mario.getX() && mario.getX() - this.x <= mario.getWidth()))))
			{

			       mario.afterKillEnemy(DeadType.PRESS);
                  return true;
			}
		if(this.y>=nowScene.getHeight())
		{
		    isOver=true;
		    return true;
		}
		return false;
	}
	@Override
	protected  void afterDead() {
      	 isDead=true;
      	 xspeed=10;
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
            if(!isDead&&isDead()){
            	afterDead();
            }
            //判断是否掉入地下
			if(!isOnland()){
				down();		
				continue;
			}
			fixDistance();
			// 是否可以左向移动的标志
			boolean canLeft = isCanLeft() && this.x >= nowScene.getX();
			// 是否可以右向移动的标志
			boolean canRight = isCanRight() && this.x < nowScene.getX() + nowScene.getWidth() - this.width;
             
			if (canRight && isRightMoving)
				rightMove();
			else if (canLeft && !isRightMoving)
				leftMove();
			else {
				isRightMoving = !isRightMoving;
			}
			
			
		}
		super.afterDead();
	}
	public boolean isRightMoving() {
		return isRightMoving;
	}
	public void setRightMoving(boolean isRightMoving) {
		this.isRightMoving = isRightMoving;
	}

}
