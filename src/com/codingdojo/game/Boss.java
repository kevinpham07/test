package com.codingdojo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject {

	private Handler handler;
	
	private int timer = 100;
	private int timer2 = 80;
	
	Random r = new Random();
	
	public Boss (int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		Audio.getMusic("boss").loop(1, (float) 0.02);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) {
			velY = 0;
			timer2--;
		}
		else {
			timer--;
		}
		
		if(timer2 <= 0) {
			if(velX == 0) {
				velX = 2;
			}
			
			//GRADUALLY INCREASE SPEED
			if(velX > 0) {
				velX += 0.005f;
			}
			if(velX < 0) {
				velX -= 0.005f;
			}
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(5);
			if(spawn == 0) handler.addObject(new BossBullet((int) x + 30, (int) y + 60, ID.Enemy, handler));
		}
		
//		if(y <= 0 || y >= Game.HEIGHT - 50) {
//			velY *= -1;
//		}
		if(x <= 0 || x >= Game.WIDTH - 70) {
			velX *= -1;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 64, 64);
	}

}

