package com.config;

import org.dom4j.Element;

public class MessageConfig {
public static final String ABOUT_MESSAGE;
public static final String WIN_MESSAGE;
public static final String GAMEOVER_MESSAGE;
static{
	Element messages=SystemConfig.DOCUMENT.getRootElement().element("messages");
	ABOUT_MESSAGE=messages.element("aboutMessage").getText();
	WIN_MESSAGE=messages.element("winMessage").getText();
	GAMEOVER_MESSAGE=messages.element("gameOverMessage").getText();
}
}
