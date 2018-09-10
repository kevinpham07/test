package com.codingdojo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		
		if(HUD.getLevel() >= 10) {
			y = Game.clamp(y, 210, Game.HEIGHT - 67);
		}
		else {
			y = Game.clamp(y, 0, Game.HEIGHT - 67);
		}
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			if(temp.getId() == ID.Enemy || temp.getId() == ID.Enemy1 || temp.getId() == ID.Enemy2) {
				if(getBounds().intersects(temp.getBounds())) {
					Audio.getSound("hit").play(1, (float) 0.02);
					HUD.health -= 2;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
	}
	
}
