package com.role;

import com.constant.GameConstant;
import com.ui.Img;

/**
 * 食人花敌人
 * 
 * @author 灿坚
 *
 */
public class Flower extends Enemy {
	private int posture;
	private boolean isUping = true;

	public Flower(int x, int y) {
		super(x, y);
		images = Img.allFlowerImage;
	}

	public void showImageWithStatus() {
		posture = (++posture) % 6;
		showImage = images.get(posture < 3 ? 0 : 1);
	}

	@Override
	protected boolean isDead() {
		return false;
	}

	@Override
	public void run() {
		try {
			while (!isOver) {

				Thread.sleep(GameConstant.SLEEPTIME);

				if (isPause)
					continue;
				if (startY == this.y)
					Thread.sleep(1000);
				boolean canUp = startY - this.y <= this.height;
				boolean canDown = (this.y != startY);
				if (canUp && isUping)
					up();
				else if (canDown && !isUping)
					down();
				else
					isUping = !isUping;
				showImageWithStatus();

			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

}
