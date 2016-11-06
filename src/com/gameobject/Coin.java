package com.gameobject;

import com.ui.Img;

public class Coin extends GameObject{
public Coin(int x,int y){
	super(x, y);
	super.init();
	showImage=Img.coinImage;
	this.width=showImage.getWidth();
	this.height=showImage.getHeight();
}

}
