package com.codingdojo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
	
	Handler handler;
	
	private int B1 = 1000;
	private int B2 = 100;
	private int B3 = 1000;
	
	public Shop(Handler handler) {
		this.handler = handler;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-80, 100);
		
		//b1
		g.drawString("Upgrade Health", 210, 190);
		g.drawString("Cost: " + B1, 210, 240);
		g.drawRect(100, 145, 100, 100);
		
		//b2
		g.setFont(new Font("arial", 0, 48));
		g.drawString("Upgrade Speed", 210, 300);
		g.drawString("Cost: " + B2, 210, 350);
		g.drawRect(100, 255, 100, 100);
				
		//b3
		g.setFont(new Font("arial", 0, 48));
		g.drawString("Restore Health", 210, 410);
		g.drawString("Cost: " + B3, 210, 460);
		g.drawRect(100, 365, 100, 100);
		
		g.drawString("SCORE: " + HUD.getScore(), Game.WIDTH/2-140, 520);
		
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Press space to go back", Game.WIDTH/2-80, 550);
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(mx >= 100 && mx <= 200 ) {
			if(my >= 145 && my <= 245) {
				System.out.println("box 1");
				if(HUD.getScore() >= 1000) {
					HUD.setScore(HUD.getScore() - B1);
					B1 += 1000;
					HUD.bounds += 20;
					HUD.health = (100 + HUD.bounds/2);
				}
			}
		}
		if(mx >= 100 && mx <= 200 ) {
			if(my >= 255 && my <= 355) {
				System.out.println("box 2");
				if(HUD.getScore() >= 100) {
					HUD.setScore(HUD.getScore() - B2);
					B2 += 100;
					handler.spd += 1;
				}
			}
		}
		if(mx >= 100 && mx <= 200 ) {
			if(my >= 365 && my <= 465) {
				System.out.println("box 3");
				if(HUD.getScore() >= 1000) {
					HUD.setScore(HUD.getScore() - B1);
					HUD.health = (100 + HUD.bounds/2);
				}
			}
		}
	}
	public void resetUpgrades() {
		B1 = 1000;
		B2 = 100;
		B3 = 1000;
		handler.spd = 5;
	}

}
