package com.role;

import com.ui.Img;

/**
 * 食人花敌人
 * @author 灿坚
 *
 */
public class Flower extends Enemy {

	public Flower(int x, int y) {
		super(x, y);
		images=Img.allFlowerImage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

}
