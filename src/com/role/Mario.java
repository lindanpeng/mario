package com.role;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.config.ScenesConfig;
import com.config.SystemConfig;
import com.constant.DeadType;
import com.controller.GameController;
import com.gameobject.Coin;
import com.gameobject.GameObject;
import com.gameobject.Obstruction;
import com.music.MusicPlayer;
import com.scene.Scene;
import com.ui.Img;

/**
 * 超级玛丽类
 * 
 * @author 贵安
 */
public class Mario extends Role implements Runnable {
	// 当前生命数
	private int life;
	//初始生命数
	private int initLife;
	// 当前的状态(左站立、右站立、左移动、右移动、左跳跃、右跳跃)
	private String status;
	// 所处场景1
	private Scene firstScene;
	// 所处场景2
	private Scene secondScene;
	// 最后一个场景的顺序
	private int lastSceneSort;
	// 所有动态执行的方法
	private Map<String, Method> methods;
	// 是否跳跃标志
	private boolean isJumping;
	//是否二级跳跃
	private boolean isSecondJumping; 
	// 是否下落标志
	private boolean isFalling;
	// 已经跳跃的高度
	private int jumpedHeight;
	// 跳跃最大高度
	private int maxJumpHeight;
	// 分数
	private int score;
	// 辅助显示图片
	private int posture;
	// 游戏控制器
	private GameController gameController;

	public Mario(int x, int y,int width,int height,int life,int xspeed,int yspeed,GameController gameController) {
		super(x, y, width,height, xspeed,yspeed);
		images = Img.allMarioImage;
		this.gameController = gameController;
		this.initLife = life;

		methods = new HashMap<String, Method>();
		try {
			methods.put("leftMove", this.getClass().getMethod("leftMove"));
			methods.put("rightMove", this.getClass().getMethod("rightMove"));
		} catch (NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 复活时初始化数据
	 */
	public void init() {
		status = "rightStop";
		firstScene.reset();
		secondScene.reset();
		firstScene.setX(0);
		secondScene.setX(ScenesConfig.SCENE_WIDTH);
		nowScene = firstScene;
		isSecondJumping=false;
		isJumping=false;
		isFalling=false;
		maxJumpHeight=120;
		super.init();
	}
	/**
	 * 重新开始游戏时初始化数据
	 */
   public void restart(){
	 life=initLife;
	 score=0;
	 isStart=false;
	 init();
   }
	/**
	 * 设置为左移动状态
	 */
	public void toLeftMove() {
		status = "leftMove";
	}

	/**
	 * 设置为右移动状态
	 */
	public void toRightMove() {
		status = "rightMove";
	}

	/**
	 * 设置左暂停状态
	 */
	public void toLeftStop() {
		status = "leftStop";
	}

	/**
	 * 设置为右暂停状态
	 */
	public void toRightStop() {
		status = "rightStop";
	}

	/**
	 * 设置为跳跃状态
	 */
	public void toJump() {
		if (!isJumping) {
			this.isJumping = true;
	        MusicPlayer.playJumpMusic();
		}
		else if(isJumping&&!isSecondJumping)
			{
			  this.isSecondJumping=true;
			  
			}
}

	/**
	 * 向左移动
	 */
	public void leftMove() {

		if (this.x <= 0)
			return;
		x -= xspeed;
	}

	/**
	 * 向右移动
	 */
	public void rightMove() {
		if(this.x>=ScenesConfig.SCENE_WIDTH)
			return;
		if (this.x <= firstScene.getWidth() / 2 || firstScene == secondScene)
			x += xspeed;
		
		// 否则场景左移
		else {
			firstScene.leftMove();
			secondScene.leftMove();
		}
	}

	/**
	 * 跳跃
	 */
	public void jump() {
		jumpedHeight += yspeed;
		y -= yspeed;

	}

	/**
	 * 下落
	 */
	public void down() {

		y += (yspeed+5);
	}

	/**
	 * 杀死敌人执行的方法
	 */
	public void afterKillEnemy(DeadType type) {
		switch (type) {
		case PRESS:
			isJumping = true;
			isFalling = false;
			MusicPlayer.playkillEnemyMusic();
			jumpedHeight =50;
			break;
		default:
			break;
		}
		score++;
	}

	/**
	 * 修正场景
	 */
	private void fixScene() {
		if (this.x - firstScene.getX() <ScenesConfig.SCENE_WIDTH) {
			nowScene = firstScene;
		}
		// 如果第一个场景已经移出画面
		if (this.getFirstScene().getX() <= -ScenesConfig.SCENE_WIDTH) {
			gameController.nextScene();
		}
		// 如果mario已经走出了第一个场景
		if (this.x - this.firstScene.getX() >= ScenesConfig.SCENE_WIDTH) {
			nowScene = secondScene;
		}
		// 如果尚未到达场景中间或者已经到达最后一个，则mario右移
	}

	/**
	 * 判断是否吃到东西
	 */
	private synchronized void eatFood() {
		for (int i=0;i<nowScene.getAllObjects().size();i++) {
			GameObject obj=nowScene.getAllObjects().get(i);
			boolean xCondition = (obj.getX() <= this.x && this.x - obj.getX() < obj.getWidth())
					|| (obj.getX() > this.x && obj.getX() - this.x < this.width);
			boolean yCondition = (obj.getY() < this.y && this.y - obj.getY() <= obj.getHeight())
					|| (obj.getY() > this.y && obj.getY() - this.y <= this.getHeight());
			if (xCondition && yCondition) {
				{
					nowScene.getAllObjects().remove(obj);
					i--;
				}
				if (obj instanceof Coin) {
					this.score++;
				}
				else
					this.life++;
				MusicPlayer.playEatFoodMusic();
			}
		}
	}

	@Override
	protected synchronized boolean isDead() {
		if (this.y >= nowScene.getHeight())
			return true;
		for (Enemy enemy : nowScene.getAllEnemies()) {
			boolean xCondition = (enemy.getX() <= this.x && this.x - enemy.getX() < enemy.getWidth())
					|| (enemy.getX() > this.x && enemy.getX() - this.x < this.width);
			boolean yCondition = (enemy instanceof Flower)
					? (enemy.getY() < this.y && this.y - enemy.getY() <= enemy.getHeight())
							|| (enemy.getY() > this.y && enemy.getY() - this.y <= this.getHeight()-10)
					: (enemy.getY() < this.y && this.y - enemy.getY() < enemy.getHeight());
			if (xCondition && yCondition) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 死亡后处理方法
	 */
	@Override
	protected synchronized void afterDead() {
		if (life > 0) {
			life--;
			this.init();
			nowScene.reset();
            MusicPlayer.playDeadMusic();
		} 
		else
			{
			 isOver = true;
		
			}

	}

	@Override
	protected boolean isStriken() {
		if (isJumping == false)
			return false;
		return super.isStriken();
	}
    @Override
    protected synchronized boolean isOnland() {
    	// 判断是否在地面
		for (Obstruction obstruction : nowScene.getAllObstructions()) {
			boolean xCondition = (obstruction.getX() >= this.x && obstruction.getX() - this.x < this.getWidth())
					|| (obstruction.getX() <= this.x && this.x - obstruction.getX() < obstruction.getWidth());

			if ((obstruction.getY() > this.y && obstruction.getY() - this.y <= this.height) && xCondition) {
				return true;
			}
		}
		return false;
    }
	/**
	 * 根据状态显示图片，注意移动的时候图片要不断切换
	 */
	private void showImageWithStatus() {
		if (status.equals("rightMove")) {
			posture = (++posture) % 5;
		} else if (status.equals("leftMove")) {
			posture = (++posture) % 5 + 5;
		} else if (status.equals("rightStop")) {
			posture = 0;
		} else if (status.equals("leftStop")) {
			posture = 5;
		} else if (status.equals("leftJump")) {
			posture = 9;
		} else if (status.equals("rightJump")) {
			posture = 4;
		}
		showImage = images.get(posture);// 显示状态图
	}

	@Override
	public void run() {
		try {
			while (!isOver) {
				Thread.sleep(SystemConfig.SLEEP_TIME);
				//判断是否暂停
				if (isPause) {
					continue;
				}
				if(x>SystemConfig.AUTO_MOVE_X)
			     status="rightMove";
				// 判断是否死亡
				if (isDead())
					afterDead();
				// 判断吃硬币
				eatFood();
				// 修改当前场景
				fixScene();
				// 修改mario和障碍物的距离
				fixDistance();
				// 根据状态显示图片
				showImageWithStatus();

				// 是否可以左向移动的标志
				boolean canLeft = isCanLeft();
				// 是否可以右向移动的标志
				boolean canRight = isCanRight();
				// 是否着陆的标志
				boolean onland = isOnland();
				// 是否撞击到障碍物
				boolean striken = isStriken();
				if (canLeft && status.equals("leftMove") || canRight && status.equals("rightMove"))
					methods.get(status).invoke(this);
				// 处于上升状态时
				if (isJumping) {
					if (striken) {
						isJumping = false;
						isFalling = true;

					}
					if (isSecondJumping) {
					 this.maxJumpHeight=200;
					 isSecondJumping=false;
					}
					// 如果还未跳跃到上升高度
					if (!isFalling && jumpedHeight < maxJumpHeight)
						jump();
					else {
						isJumping = false;
						isFalling = true;
					}
				}
				// 处于下落状态时
				if ((!onland && !isJumping) || isFalling) {
					isSecondJumping=false;
					maxJumpHeight=120;
					// 如果还未落到地上
					if (!onland) {
						isFalling = true;
						down();
					} else {
						isFalling = false;
						jumpedHeight = 0;
					}
				}
			
			}
		} catch (IllegalAccessException e1) {

			e1.printStackTrace();
		} catch (InvocationTargetException e1) {

			e1.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("mario线程中断");
			e.printStackTrace();
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean isFalling() {
		return isFalling;
	}

	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}
}
