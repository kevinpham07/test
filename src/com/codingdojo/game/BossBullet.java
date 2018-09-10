package com.codingdojo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossBullet extends GameObject {

	private Handler handler;
	
	Random r = new Random();
	
	public BossBullet (int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextInt(10) - 5;
		velY = 5;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 5, 5);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
//		if(y <= 0 || y >= Game.HEIGHT - 50) {
//			velY *= -1;
//		}
//		if(x <= 0 || x >= Game.WIDTH - 25) {
//			velX *= -1;
//		}
		if(y >= Game.HEIGHT - 40) {
			handler.removeObject(this);
		}
		if(x >= Game.WIDTH - 10 || x < 0) {
			handler.removeObject(this);
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 5, 5);
	}

}
