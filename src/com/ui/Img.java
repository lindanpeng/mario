package com.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 保存所有图片
 * @author Administrator
 *
 */
public class Img {
	// mario动画gif
	public static List<BufferedImage> allMarioImage = new ArrayList<>();
	// 游戏开始画面
	public static BufferedImage startImage = null;
	// 游戏结束画面
	public static BufferedImage endImage = null;
	// 游戏背景图片
	public static BufferedImage bgIamge = null;
	// 食人花gif
	public static List<BufferedImage> allFlowerImage = new ArrayList<>();
	// 蘑菇gif
	public static List<BufferedImage> allMushroomImage = new ArrayList<>();
	// 乌龟gif
	public static List<BufferedImage> allTurtleImage = new ArrayList<>();
	// 障碍物图片
	public static List<BufferedImage> allObstructionImage = new ArrayList<>();
	// mario死亡图片
	public static BufferedImage marioDeadImage = null;
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
	public static void init() {
		try {
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
			/*
			 * 其他图片
			 */
			startImage=ImageIO.read(new File(IMAGE_PATH+"start.gif"));
			bgIamge=ImageIO.read(new File(IMAGE_PATH+"firststage.gif"));
			endImage = ImageIO.read(new File(IMAGE_PATH + "firststageend.gif"));
			marioDeadImage=ImageIO.read(new File(IMAGE_PATH+"over.gif"));

		} catch (IOException e) {
			System.out.println("读取图片资源失败！");
			//e.printStackTrace();
		}

	}	
}
