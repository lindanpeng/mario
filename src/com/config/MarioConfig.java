package com.config;

import org.dom4j.Element;

public class MarioConfig {
public static final int X;
public static final int Y;
public static final int DEFAULT_WIDTH;
public static final int DEFAULT_HEIGHT;
public static final int BIG_WIDTH;
public static final int BIG_HEIGHT;
public static final int LIFE;
public static final int XSPEED;
public static final int YSPEED;
static{
	Element marioElement=SystemConfig.DOCUMENT.getRootElement().element("mario");
	X=Integer.valueOf(marioElement.attributeValue("x"));
	Y=Integer.valueOf(marioElement.attributeValue("y"));
	LIFE=Integer.valueOf(marioElement.attributeValue("life"));
	XSPEED=Integer.valueOf(marioElement.attributeValue("xspeed"));
	YSPEED=Integer.valueOf(marioElement.attributeValue("yspeed"));
	DEFAULT_WIDTH=Integer.valueOf(marioElement.attributeValue("defaultWidth"));
	DEFAULT_HEIGHT=Integer.valueOf(marioElement.attributeValue("defaultHeight"));
	BIG_WIDTH=Integer.valueOf(marioElement.attributeValue("bigWidth"));
	BIG_HEIGHT=Integer.valueOf(marioElement.attributeValue("bigHeight"));
}
}
