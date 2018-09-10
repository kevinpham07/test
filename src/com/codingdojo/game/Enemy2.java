package com.codingdojo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy2 extends GameObject {

	private Handler handler;
	private GameObject player;
	
	Random r = new Random();
	
	public Enemy2 (int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
		velY = r.nextInt(2) + 1;
		velX = r.nextInt(2) + 1;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 10, 10);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		float distance = (float) Math.sqrt( (x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()) );
		
		velX = ((-1/distance) * diffX);
		velY = ((-1/distance) * diffY);
		
		if(y <= 0 || y >= Game.HEIGHT - 50) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 25) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, 10, 10, 0.03f, Color.cyan, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 10, 10);
	}
}
