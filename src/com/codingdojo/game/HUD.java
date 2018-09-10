package com.codingdojo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	public static float health = 100;
	
	private float greenValue = 255f;
	private float redValue = 0f;
	
	private static int score = 0;
	private static int level = 1;

	public static int bounds = 0;
	
	public HUD () {
	
	}
	
	public void tick(Handler handler) {
		health = Game.clamp ((int) health, 0, 100 + bounds/2);
		
		greenValue = health * 2;
		redValue = 255 - greenValue;
		
		greenValue = Game.clamp((int) greenValue, 0, 255);
		redValue = Game.clamp((int) redValue, 0, 255);
		
		
		score++;
	}
	
	public void render(Graphics g) {
		Font font = new Font("dialog", 1, 12);
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color((int) redValue, (int) greenValue, 0));
		g.fillRect((int) 15, (int) 15, (int) (health * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		
		g.setFont(font);
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("Press Space for shop", 15, 94);
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void setScore(int s) {
		score = s;
	}

	public static int getLevel() {
		return level;
	}
	
	public static void setLevel(int l) {
		level = l;
	}
	
	//reset
	public static void reset() {
		health = 100;
		level = 1;
		score = 0;
		bounds = 0;
	}
}
