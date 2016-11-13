package com.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 保存所有图片
 * @author Administrator
 *
 */
public class Img {

	//开始按钮图片
	public static List<ImageIcon> startBtnImage=new ArrayList<>();
	//关于按钮图片
	public static List<ImageIcon> aboutBtnImage=new ArrayList<>();

	// mario动画gif
	public static List<BufferedImage> allMarioImage = new ArrayList<>();
	// 游戏开始画面
	public static BufferedImage startImage = null;
	// 游戏结束画面
	public static BufferedImage endImage = null;
	// 游戏背景图片
	public static BufferedImage bgImage = null;
	//硬币图片
	public static BufferedImage coinImage=null;
	//蘑菇图片
	public static BufferedImage mushroomImage=null;
	// mario死亡图片
	public static BufferedImage marioDeadImage = null;
	//暂停时阴影图片
	public static BufferedImage  pauseImage=null;
	//怪物gif
	public static List<BufferedImage> monsterImage=new ArrayList<>();
	// 食人花gif
	public static List<BufferedImage> allFlowerImage = new ArrayList<>();
	// 蘑菇gif
	public static List<BufferedImage> allMushroomImage = new ArrayList<>();
	// 乌龟gif
	public static List<BufferedImage> allTurtleImage = new ArrayList<>();
	// 障碍物图片
	public static List<BufferedImage> allObstructionImage = new ArrayList<>();
	public static final int MONSTER_IMAGES_NUM=42;
	public static final int STARTBTN_IMAGES_NUM=3;
	public static final int ABOUNTBTN_IMAGES_NUM=3;
	// mario图片数量
	private static final int MARIO_IMAGES_NUM = 10;
	// flower图片数量
	private static final int FLOWER_IMAGES_NUM = 2;
	// turtle图片数量
	private static final int TURTLE_IMAGES_NUM = 5;
	//triangle图片数量
	private static final int TRIANGLE_IMAGES_NUM=3;
	//障碍物图片数量
	private static final int OBSTRCTION_IMAGES_NUM=12;
	// 图片路径
	private static final String IMAGE_PATH = System.getProperty("user.dir") + "\\resources\\images\\";

	/**
	 * 初始化图片资源
	 */
	static {
		try {
			for(int i=1;i<=STARTBTN_IMAGES_NUM;i++)
				startBtnImage.add(new ImageIcon(IMAGE_PATH + "start" + i + ".gif"));
			for(int i=1;i<=ABOUNTBTN_IMAGES_NUM;i++)
				aboutBtnImage.add(new ImageIcon(IMAGE_PATH + "about" + i + ".gif"));
			/*
			 * 载入mario图片
			 */
			for (int i = 1; i <= MARIO_IMAGES_NUM; i++)
				allMarioImage.add(ImageIO.read(new File(IMAGE_PATH + "mario" + i + ".gif")));
			/*
			 * 载入turtle图片
			 */
			for (int i = 1; i <=TURTLE_IMAGES_NUM; i++)
				allTurtleImage.add(ImageIO.read(new File(IMAGE_PATH + "turtle" + i + ".gif")));
			for(int i=1;i<=FLOWER_IMAGES_NUM;i++)
				allFlowerImage.add(ImageIO.read(new File(IMAGE_PATH+"flower"+i+".gif")));
			for(int i=1;i<=TRIANGLE_IMAGES_NUM;i++)
			    allMushroomImage.add(ImageIO.read(new File(IMAGE_PATH+"triangle"+i+".gif")));
			/*TODO
			 * 载入障碍物图片
			 */
			for(int i=1;i<=OBSTRCTION_IMAGES_NUM;i++)
				allObstructionImage.add(ImageIO.read(new File(IMAGE_PATH+"ob"+i+".gif")));
			
			for (int i = 1; i<=MONSTER_IMAGES_NUM; i++) {
				monsterImage.add(ImageIO.read(new File(IMAGE_PATH+"monster ("+i+").png")));
			}
			/*
			 * 其他图片
			 */
			startImage=ImageIO.read(new File(IMAGE_PATH+"start.jpg"));
			bgImage=ImageIO.read(new File(IMAGE_PATH+"firststage.gif"));
			endImage = ImageIO.read(new File(IMAGE_PATH + "endstage.gif"));
			marioDeadImage=ImageIO.read(new File(IMAGE_PATH+"over.gif"));
			coinImage=ImageIO.read(new File(IMAGE_PATH+"coin.png"));
			mushroomImage=ImageIO.read(new File(IMAGE_PATH+"mushroom.png"));
			pauseImage=ImageIO.read(new File(IMAGE_PATH+"pause.png"));

		} catch (IOException e) {
			System.out.println("读取图片资源失败！");
			//e.printStackTrace();
		}

	}	
}
