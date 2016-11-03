package com.gameobject;


import com.constant.GameConstant;
import com.scene.Scene;
import com.ui.Img;
/**
 * 障碍物
 * @author 林丹
 *
 */
public class Obstruction extends GameObject {
	private int startX;
	//起始y坐标
	private int startY;
	//初始类型
	private int originalType;
	//障碍物类型
	private int type;
	public Obstruction(int x, int y,int type) {
	  super(x, y);
      this.startX=x;
      this.startY=y;
      this.originalType=type;
      
	}
	/**
	 * 初始化各种数据
	 */
	public void init() {
		this.x=startX;
		this.y=startY;
		this.type=originalType;
		showImage=Img.allObstructionImage.get(type);
		this.width=showImage.getWidth();
		this.height=showImage.getHeight();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		showImage=Img.allObstructionImage.get(type);
	}
	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}
	public void beStriken(Scene nowScene) {
		// 如果撞到普通墙块，则移除
		if (this.getType() == 7) {
			nowScene.getAllObstructions().remove(this);
			nowScene.getRemovedObstructions().add(this);

		}
		// TODO 如果撞到带金币的墙块
		else if (this.getType() == 6) {
			this.setType(9);
			//有10分之一的机会吃到蘑菇
			int i=(int) (Math.random()*GameConstant.EAT_MUSHROOM_CHANGE);
			if (0!=i) {
				nowScene.getAllObjects().add(new Coin(this.getX(), this.getY() -60));
			}
			else
				nowScene.getAllObjects().add(new Mushroom(this.getX(), this.getY()-48));
		}
		
	}
  
}
