package com.config;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SystemConfig {
public static Document DOCUMENT;
public static final Element ROOT_ELEMENT;
public static final int WINDOW_WIDTH;
public static final int WINDOW_HEIGHT;
public static final int SLEEP_TIME;
public static final int AUTO_MOVE_X;
public static final int ENDX;
public static final String TITLE;
static{
	SAXReader reader=new SAXReader();
	try {
	   DOCUMENT=reader.read(new File("data/cfg.xml"));
	} catch (DocumentException e) {
		
		e.printStackTrace();
	}
	ROOT_ELEMENT= DOCUMENT.getRootElement();
	WINDOW_WIDTH=Integer.valueOf(ROOT_ELEMENT.attributeValue("windowWidth"));
	WINDOW_HEIGHT=Integer.valueOf(ROOT_ELEMENT.attributeValue("windowHeight"));
	SLEEP_TIME=Integer.valueOf(ROOT_ELEMENT.attributeValue("sleepTime"));
	TITLE=ROOT_ELEMENT.attributeValue("title");
	AUTO_MOVE_X=Integer.valueOf(ROOT_ELEMENT.attributeValue("autoMoveX"));
	ENDX=Integer.valueOf(ROOT_ELEMENT.attributeValue("endX"));
}

}
