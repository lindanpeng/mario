package com.config;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SystemConfig {
public static Document DOCUMENT;
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
	Element game= DOCUMENT.getRootElement();
	WINDOW_WIDTH=Integer.valueOf(game.attributeValue("windowWidth"));
	WINDOW_HEIGHT=Integer.valueOf(game.attributeValue("windowHeight"));
	SLEEP_TIME=Integer.valueOf(game.attributeValue("sleepTime"));
	TITLE=game.attributeValue("title");
	AUTO_MOVE_X=Integer.valueOf(game.attributeValue("autoMoveX"));
	ENDX=Integer.valueOf(game.attributeValue("endX"));
}

}
