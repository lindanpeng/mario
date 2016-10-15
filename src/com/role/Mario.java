package com.role;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.obstruction.Obstruction;
import com.scene.Scene;

/**
 * 超级玛丽类
 * 
 * @author 贵安
 */
public class Mario implements Runnable {
	//人物高度
	private int width=60;
	//人物宽度
	private int height=60;
	// x坐标
	private int x;
	// y坐标
	private int y;
	// 起始x坐标
	private int startX;
	// 起始y坐标
	private int startY;
	// 水平移动速度
	private int xspeed;
	// 垂直移动速度
	private int yspeed;
	// 生命数
	private int life;
	// 当前显示的图片
	private BufferedImage showImage;
	// 所有图片
	private List<BufferedImage> images;
	// 当前的状态(左站立、右站立、左移动、右移动、左跳跃、右跳跃)
	private String status;
	// 当前所处的场景
	private Scene scene;
	// 所有动态执行的方法
	private Map<String, Method> methods;
	// 是否跳跃标志
	private boolean isJumping;
	// 跳跃次数,upTime*yspeed即为跳跃距离
	private int upTimes;
	// 跳跃次数最大值
	private int upTimesMax;

	public Mario(int x, int y, int life) throws NoSuchMethodException, SecurityException {
		startX = x;
		startX = y;
		this.life = life;
		//methods.put("rightStop", this.getClass().getMethod("rightStop"));
		//methods.put("leftStop", this.getClass().getMethod("leftStop"));
		methods.put("leftMove", this.getClass().getMethod("leftMove"));
		methods.put("rightMove", this.getClass().getMethod("rightMove"));

	}

	/**
	 * 初始化数据
	 */
	public void init() {
		this.x = startX;
		this.y = startY;
		status = "rightStop";
		showImage = images.get(0);
	}

	/**
	 * 向左移动
	 */
	public void leftMove() {
		x -= xspeed;
		status = "leftMove";
	}

	/**
	 * 向右移动
	 */
	public void rightMove() {
		x += xspeed;
		status = "rightMove";
	}

	/**
	 * 左暂停
	 */
	public void leftStop() {
		status = "leftStop";

	}

	/**
	 * 右暂停
	 */
	public void rightStop() {
		status = "rightStop";
	}

	public void toJump() {
		if (!isJumping) {
			this.isJumping = true;
			upTimes = upTimesMax;
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

				// if(isCanLeft()&&status.startsWith("left")||isCanRight()&&status.startsWith("right"))
				methods.get(status).invoke(this);
                showImageWithStatus();
				Thread.sleep(50);
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
    
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
