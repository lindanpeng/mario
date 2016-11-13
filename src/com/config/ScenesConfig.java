package com.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.resource.Img;
import com.scene.Scene;

public class ScenesConfig {
public static final int SCENE_WIDTH;
public static final int SCENE_HEIGHT;
public static final int XSPEED;
public static List<Scene> ALLSCENES=new ArrayList<>();
static{
	Element scenes=SystemConfig.DOCUMENT.getRootElement().element("scenes");
	SCENE_WIDTH=Integer.valueOf(scenes.attributeValue("width"));
	SCENE_HEIGHT=Integer.valueOf(scenes.attributeValue("height"));
	XSPEED=Integer.valueOf(scenes.attributeValue("xspeed"));
	List<Element> scenesElement=scenes.elements("scene");
	for(int i=0;i<scenesElement.size();i++){
		Element sceneElement=scenesElement.get(i);
		SceneConfig sceneConfig=new SceneConfig(sceneElement);
		Scene scene=new Scene(Img.bgImage,sceneConfig.getEnemies(),sceneConfig.getObstructions(),i+1);
		ALLSCENES.add(scene);
	}
}

}
