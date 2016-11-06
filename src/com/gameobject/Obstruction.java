package com.gameobject;

import com.music.MusicPlayer;
import com.scene.Scene;
import com.ui.Img;
/**
 * 障碍物
 * @author 林丹
 *
 */
public class Obstruction extends GameObject {
	private int originalType;
	//障碍物类型
	private int type;
	public Obstruction(int x, int y,int type) {
	  super(x, y);
      this.originalType=type;
      
	}
	/**
	 * 初始化各种数据
	 */
	public void init() {
		super.init();
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
	public void beStriken(Scene nowScene) {
		// 如果撞到普通墙块，则移除
		if (this.getType() == 7) {
			nowScene.getAllObstructions().remove(this);
			nowScene.getRemovedObstructions().add(this);
			MusicPlayer.playBreakWallMusic();

		}
		// TODO 如果撞到带金币的墙块
		else if (this.getType() == 6) {
			this.setType(9);
			//有2分之一的机会吃到蘑菇
			int i=(int) (Math.random()*2);
			if (0!=i) {
				nowScene.getAllObjects().add(new Coin(this.getX(), this.getY() -60));
				MusicPlayer.playGetCoinMusic();
			}
			else
				{
				 nowScene.getAllObjects().add(new Mushroom(this.getX(), this.getY()-48));
				 MusicPlayer.playGetMushroomMusic();
				}
			    
		}
		
	}
  
}
