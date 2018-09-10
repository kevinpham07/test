package com.codingdojo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuEnemy extends GameObject {

	private Handler handler;
	
	Random r = new Random();
	
	private Color col;
	
	public MenuEnemy (int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextInt(5) + 5;
		velY = r.nextInt(5) + 5;
		col = new Color (r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
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
		handler.addObject(new Trail(x, y, ID.Trail, 16, 16, 0.04f, col, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int) x, (int) y, 16, 16);
	}

}