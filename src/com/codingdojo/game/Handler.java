package com.codingdojo.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public int spd = 5;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			if(temp.getId() != ID.Player) {
				removeObject(temp);
				i--;
			}
		}
	}
	public void restart() {
		for(int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			removeObject(temp);
			i--;
		}
	}

}
