package com.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MyKeyListenter extends KeyAdapter {
   private GameController gameController;
   private List<KeyCodeAndType> list=new ArrayList<>();
	public MyKeyListenter(GameController gameController) {
	this.gameController=gameController;
	list.add(new KeyCodeAndType(37,1));
	list.add(new KeyCodeAndType(39, 1));
	list.add(new KeyCodeAndType(38, 0));
	list.add(new KeyCodeAndType(37, 0));
	list.add(new KeyCodeAndType(39, 0));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37)
			gameController.doAction(list.get(0));
		else if (e.getKeyCode() == 39)
			gameController.doAction(list.get(1));
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37)
			gameController.doAction(list.get(3));
		if (e.getKeyCode() == 39)
			gameController.doAction(list.get(4));
		if (e.getKeyCode() == 38)
			gameController.doAction(list.get(2));
        if(e.getKeyCode()==16)
        	gameController.pauseOrPlayGame();
        	
	}

}
