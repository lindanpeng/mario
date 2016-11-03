package com.gameobject;

import com.ui.Img;

public class Mushroom extends GameObject{

	public Mushroom(int x, int y) {
		super(x, y);
		showImage=Img.mushroomImage;
		this.width=showImage.getWidth();
		this.height=showImage.getHeight();
	}

}
