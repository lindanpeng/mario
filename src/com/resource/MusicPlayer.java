package com.resource;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class MusicPlayer {
	private static AudioClip BACKGROUND;
	private static AudioClip DEAD;
	private static AudioClip GAME_OVER;
	private static AudioClip EAT_FOOD;
	private static AudioClip JUMP;
	private static AudioClip KILL_ENEMY;
	private static AudioClip GET_COIN;
	private static AudioClip GET_MUSHROOM;
	private static AudioClip WIN;
	private static AudioClip BREAK_WALL;
	static{
		try {
			DEAD=Applet.newAudioClip(new File("resources/music/dead.wav").toURL());
			BACKGROUND=Applet.newAudioClip(new File("resources/music/background.wav").toURL());
			GAME_OVER=Applet.newAudioClip(new File("resources/music/gameOver.wav").toURL());
			EAT_FOOD=Applet.newAudioClip(new File("resources/music/eatFood.wav").toURL());
			KILL_ENEMY=Applet.newAudioClip(new File("resources/music/killEnemy.wav").toURL());
			JUMP=Applet.newAudioClip(new File("resources/music/jump.wav").toURL());
			GET_COIN=Applet.newAudioClip(new File("resources/music/getCoin.wav").toURL());
			GET_MUSHROOM=Applet.newAudioClip(new File("resources/music/getMushroom.wav").toURL());
			WIN=Applet.newAudioClip(new File("resources/music/win.wav").toURL());
			BREAK_WALL=Applet.newAudioClip(new File("resources/music/breakWall.wav").toURL());
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
	}
	public static  void playBackgroundMusic(){
		BACKGROUND.loop();
	}
	public static void playJumpMusic(){
		JUMP.play();
	}
	public static void playkillEnemyMusic(){
		KILL_ENEMY.play();
	}
	public static void playGameOverMusic(){
		GAME_OVER.play();
	}
	public static void playGetCoinMusic(){
		GET_COIN.play();
	}
	public static void playBreakWallMusic(){
		BREAK_WALL.play();
	}
	public static void playGetMushroomMusic(){
		GET_MUSHROOM.play();
	}
	public static void playWinMusic(){
		WIN.play();
	}
	public static void playDeadMusic(){
		DEAD.play();
	}
	public static void playEatFoodMusic(){
		EAT_FOOD.play();
	}
	public static void stopBackgroundMusic(){
		BACKGROUND.stop();
	}
}
