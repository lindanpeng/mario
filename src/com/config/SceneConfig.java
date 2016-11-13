package com.config;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.gameObject.creature.Enemy;
import com.gameObject.lifelessObject.Obstruction;

public class SceneConfig {
	private List<Obstruction> obstructions =new ArrayList<Obstruction>();
	private List<Enemy> enemies = new ArrayList<Enemy>();

	public SceneConfig(Element sceneElement) {
		List<Element> obElements = sceneElement.element("obstructions").elements();
		List<Element> enemyElements = sceneElement.element("enemies").elements();
		for (Element obElement : obElements) {
			ObstructionConfig obConfig = new ObstructionConfig(obElement);
			obstructions.add(new Obstruction(obConfig.getX(), obConfig.getY(), obConfig.getType()));
		}
		try {
			for (Element enemyElement : enemyElements) {
				EnemyConfig enemyConfig = new EnemyConfig(enemyElement);
				Class<?> clazz = Class.forName(enemyElement.attributeValue("className"));
				Constructor<?> constructor = clazz.getConstructor(int.class, int.class);
				Enemy enemy=(Enemy) constructor.newInstance(enemyConfig.getX(),enemyConfig.getY());
				enemies.add(enemy);
			}

		} 
	 catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Obstruction> getObstructions() {
		return obstructions;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

}
