package com.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.role.Mario;

public class MyKeyListenter extends KeyAdapter {
private Mario mario;
public  MyKeyListenter(Mario mario) {
	this.mario=mario;
}
@Override
public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == 37)
		mario.leftStop();
	if (e.getKeyCode() == 39)
		mario.rightStop();

}

@Override
public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == 37)
		mario.leftMove();
	if (e.getKeyCode() == 39)
		mario.rightMove();
	if (e.getKeyCode() == 32)
		mario.jump();

}

}
