package com.role;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.constant.GameConstant;
import com.controller.GameController;
import com.scene.Scene;
import com.ui.Img;

/**
 * 超级玛丽类
 * 
 * @author 贵安
 */
public class Mario extends Role implements Runnable {
	// 生命数
	private int life;
    // 当前的状态(左站立、右站立、左移动、右移动、左跳跃、右跳跃)
	private String status;
	// 所处场景1
	private Scene firstScene;
	//所处场景2
	private Scene secondScene;
	//最后一个场景的顺序
	private int lastSceneSort;
	// 所有动态执行的方法
	private Map<String, Method> methods;
	// 是否跳跃标志
	private boolean isJumping;
	// 跳跃次数,upTime*yspeed即为跳跃距离
	private int upTimes;
	// 跳跃高度
	private int jumpHeight=240;
    //死亡标志
	private boolean isDead;
	//辅助显示图片
	private int posture;
	private GameController gameController;
	public Mario(int x, int y, int life,GameController gameController){
		super(x, y);
		this.gameController=gameController;
		this.life = life;
		images=Img.allMarioImage;
		methods=new HashMap<String, Method>();
		try {
			methods.put("rightStop", this.getClass().getMethod("rightStop"));
			methods.put("leftStop", this.getClass().getMethod("leftStop"));
			methods.put("leftMove", this.getClass().getMethod("leftMove"));
			methods.put("rightMove", this.getClass().getMethod("rightMove"));
		} catch (NoSuchMethodException | SecurityException e) {
		
			e.printStackTrace();
		}
	

	}

	/**
	 * 初始化数据
	 */
	public void init() {
		this.x = startX;
		this.y = startY;
		status = "rightStop";
		showImage = images.get(0);
		new Thread(this).start();
	}

	/**
	 * 向左移动
	 */
	public void leftMove() {
		status = "leftMove";
		if(this.x<=0)
			return ;
		x -= xspeed;
		System.out.println(status);
	}

	/**
	 * 向右移动
	 */
	public void rightMove() {
		status = "rightMove";
		//如果第一个场景已经移出画面
		if(this.getFirstScene().getX()<=-GameConstant.SCENE_DEFAULT_WIDTH){
			gameController.nextScene();
		}
	   //如果
		if(this.x>=nowScene.getWidth()-this.width)
			return;
		//如果mario已经走出了第一个场景
		if (this.firstScene.getX() <= -GameConstant.SCENE_DEFAULT_WIDTH/2)
		{
			nowScene=secondScene;
		}
		//如果尚未到达场景中间或者已经到达最后一个，则mario右移
		if(this.x<=firstScene.getWidth()/2||firstScene==secondScene)
		x += xspeed;
		//否则场景左移
		else
			{
			  firstScene.leftMove();
			  secondScene.leftMove();
			}

		System.out.println(status);
	}

	/**
	 * 左暂停
	 */
	public void leftStop() {
		status = "leftStop";
		System.out.println(status);
	}

	/**
	 * 右暂停
	 */
	public void rightStop() {
		status = "rightStop";
		System.out.println(status);
	}

	public void toJump() {
		if (!isJumping) {
			this.isJumping = true;
			//upTimes = upTimesMax;
		}
	}

	/**
	 * 跳跃
	 */
	public void jump() {
		y -= yspeed;
	}

	/**
	 * 下落
	 */
	public void down() {
		y += yspeed;
	}

	/**
	 * 死亡后执行的方法
	 */
	public void afterDead() {

	}

	/**
	 * 根据状态显示图片，注意移动的时候图片要不断切换
	 */
	public void showImageWithStatus() {
         if(status.equals("rightMove")){
        	 posture = (++posture) % 5;
         }
         else if(status.equals("leftMove")){
        	 posture = (++posture) % 5 + 5;
         }
         else if(status.equals("rightStop")){
        	 posture = 0;
         }
         else if(status.equals("leftStop")){
        	 posture = 5;
         }
         else if(status.equals("leftJump")){
        	 posture = 9;
         }
         else if(status.equals("rightJump")){
        	 posture = 4;
         }
     	showImage = images.get(posture);// 显示状态图
	}

	/**
	 * 判断是否可以向左移动
	 */
	public boolean isCanLeft() {
		//TODO
		
		return false;
	}

	/**
	 * 判断是否可以向右移动移动
	 */
	public boolean isCanRight() {
		//TODO
		return false;
	}

	/**
	 * 判断是否在障碍物上面
	 */
	public boolean isOnland() {
		//TODO
		return false;
	}

	@Override
	public void run() {
		while (true) {
			try { 
			/*	 1.根据状态改变显示的图片
				 2.判断是否碰到障碍物
				 3.判断是否在障碍物上面
				 4.判断是否与敌人接触
    		*/
			    // 是否可以左向移动的标志
				boolean isCanLeft = isCanLeft();
				// 是否可以右向移动的标志
				boolean isCanRight = isCanRight();
				// 是否着陆的标志
				boolean isOnland = isOnland();
				methods.get(status).invoke(this);
                showImageWithStatus();
				Thread.sleep(GameConstant.SLEEPTIME);
			} catch (IllegalAccessException e1) {

				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {

				e1.printStackTrace();
			} catch (InvocationTargetException e1) {

				e1.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("mario线程中断");
				e.printStackTrace();
			}

		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
    
	public Scene getFirstScene() {
		return firstScene;
	}

	public void setFirstScene(Scene firstScene) {
		this.firstScene = firstScene;
	}

	public Scene getSecondScene() {
		return secondScene;
	}

	public void setSecondScene(Scene secondScene) {
		this.secondScene = secondScene;
	}

	public int getLastSceneSort() {
		return lastSceneSort;
	}

	public void setLastSceneSort(int lastSceneSort) {
		this.lastSceneSort = lastSceneSort;
	}

}
