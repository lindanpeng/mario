package com.gameObject.lifelessObject;

import com.gameObject.GameObject;
import com.resource.Img;
/**
 * 蘑菇类
 * @author 林丹
 *
 */
public class Mushroom extends GameObject{

	public Mushroom(int x, int y) {
		super(x, y);
		super.init();
		showImage=Img.mushroomImage;
		this.width=showImage.getWidth();
		this.height=showImage.getHeight();
	}

}
