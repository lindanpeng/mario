package com.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 窗口工具类
 * @author 林丹
 *
 */
public class FrameUtil {
	public static void setFrameCenter(JFrame jf){
		 Toolkit toolkit=Toolkit.getDefaultToolkit();
		 Dimension screen=toolkit.getScreenSize();
		 int x=(screen.width-jf.getWidth())/2;
		 int y=(screen.height-jf.getHeight())/2;
		 jf.setLocation(x,y);
	}
}
