package com.config;

import org.dom4j.Element;

public class ObstructionConfig {
//x坐标
private int x;
//y坐标
private int y;
//类型
private int type;
public ObstructionConfig(Element obElement){
	this.x=Integer.valueOf(obElement.attributeValue("x"));
	this.y=Integer.valueOf(obElement.attributeValue("y"));
	this.type=Integer.valueOf(obElement.attributeValue("type"));
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
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}

}

