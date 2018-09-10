package com.codingdojo.game;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		scoreKeep++;
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			HUD.setLevel(HUD.getLevel() + 1);
			
			if(HUD.getLevel() % 2 == 0) {
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			}
			if(HUD.getLevel() % 3 == 0) {
				handler.addObject(new Enemy1(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 50), ID.Enemy1, handler));
			}
			if(HUD.getLevel() % 4 == 0) {
				handler.addObject(new Enemy2(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT -50), ID.Enemy2, handler));
			}
			if (HUD.getLevel() == 10) {
				handler.clearEnemies();
				handler.addObject(new Boss((Game.WIDTH/2 - 25), -64, ID.Boss, handler));				
			}

		}
	}
}
