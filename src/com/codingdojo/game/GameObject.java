package com.codingdojo.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	//GETTERS
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public ID getId() {
		return id;
	}
	public float getVelY() {
		return velY;
	}
	public float getVelX() {
		return velX;
	}
	
	//SETTERS
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public void setVelY(int y) {
		this.velY = y;
	}
	public void setVelX(int x) {
		this.velX = x;
	}

}
