package com.gameObject.lifelessObject;

import com.gameObject.GameObject;
import com.resource.Img;
/**
 * 金币类
 * @author 林丹
 *
 */
public class Coin extends GameObject{
public Coin(int x,int y){
	super(x, y);
	super.init();
	showImage=Img.coinImage;
	this.width=showImage.getWidth();
	this.height=showImage.getHeight();
}

}
