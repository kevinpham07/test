package com.codingdojo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy1 extends GameObject {

	private Handler handler;
	
	Random r = new Random();
	
	public Enemy1 (int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextInt(2) + 1;
		velY = r.nextInt(2) + 1;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 25) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, 32, 32, 0.03f, Color.red, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 32, 32);
	}
}
