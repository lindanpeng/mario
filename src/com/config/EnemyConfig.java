package com.config;

import org.dom4j.Element;

public class EnemyConfig {
public static final int DEFAULT_XSPEED;
public static final int DEFAULT_YSPEED;
public static final int DEFAULT_WIDTH;
public static final int DEFAULT_HEIGHT;
static{
	DEFAULT_XSPEED=Integer.valueOf(SystemConfig.ROOT_ELEMENT.element("Enemy").attributeValue("xspeed"));
	DEFAULT_YSPEED=Integer.valueOf(SystemConfig.ROOT_ELEMENT.element("Enemy").attributeValue("yspeed"));
	DEFAULT_WIDTH=Integer.valueOf(SystemConfig.ROOT_ELEMENT.element("Enemy").attributeValue("width"));
	DEFAULT_HEIGHT=Integer.valueOf(SystemConfig.ROOT_ELEMENT.element("Enemy").attributeValue("height"));
}
private int x;
private int y;
public EnemyConfig(Element enemyElement){
	this.x=Integer.valueOf(enemyElement.attributeValue("x"));
	this.y=Integer.valueOf(enemyElement.attributeValue("y"));
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}

}
