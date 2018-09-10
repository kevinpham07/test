package com.codingdojo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.codingdojo.game.Game.state;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private Shop shop;
	
	public Menu(Game game, Handler handler, Shop shop) {
		this.game = game;
		this.handler = handler;
		this.shop = shop;
		for(int i = 0; i < 10; i++) {
			handler.addObject(new MenuEnemy(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 50), ID.MenuEnemy, handler));
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == state.Menu) {
			//PLAY BUTTON
			if(mouseOver(mx, my, 360, 300, 200, 70)) {
				Audio.getSound("menu").play(1, (float) 0.02);
				Audio.getMusic("game").loop(1, (float) 0.02);
				handler.restart();
				HUD.reset();
				game.gameState = state.Game;
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH/2-32), r.nextInt(Game.HEIGHT/2-32), ID.Enemy, handler));
			}
			//HELP BUTTON
			if(mouseOver(mx, my, 360, 390, 200, 70)) {
				Audio.getSound("menu").play(1, (float) 0.02);
				game.gameState = state.Help;
			}
			//QUIT BUTTON
			if(mouseOver(mx, my, 360, 480, 200, 70)) {
				System.exit(1);
			}
		}
		if(game.gameState == state.Help) {
			//BACK BUTTON
			if(mouseOver(mx, my, 360, 480, 200, 70)) {
				Audio.getSound("menu").play(1, (float) 0.02);
				game.gameState = state.Menu;
			}	
		}
		if(game.gameState == state.End || game.gameState == state.Win) {
			//PLAY AGAIN BUTTON
			if(mouseOver(mx, my, 360, 300, 200, 70)) {
				Audio.getSound("menu").play(1, (float) 0.02);
				Audio.getMusic("game").loop(1, (float) 0.02);
				game.gameState = state.Game;
				handler.restart();
				HUD.reset();
				shop.resetUpgrades();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH/2-32), r.nextInt(Game.HEIGHT/2-32), ID.Enemy, handler));
				
			}
			//MAIN MENU BUTTON
			if(mouseOver(mx, my, 360, 390, 200, 70)) {
				Audio.getSound("menu").play(1, (float) 0.02);
				game.gameState = state.Menu;
				Audio.init();
				Audio.getMusic("main_menu").loop(1, (float) 0.02);
				handler.restart();
				for(int i = 0; i < 10; i++) {
					handler.addObject(new MenuEnemy(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 50), ID.MenuEnemy, handler));
				}
			}
		}
	}
	
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font font = new Font("arial", 1, 50);
		Font font2 = new Font("arial", 1, 30);
		
		if(game.gameState == state.Menu) {
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Circles", 375, 200);
			
			g.setFont(font2);
			g.drawRect(360, 300, 200, 70);
			g.drawString("Play", 425, 345);
			
			g.setFont(font2);
			g.drawRect(360, 390, 200, 70);
			g.drawString("Help", 425, 435);
			
			g.setFont(font2);
			g.drawRect(360, 480, 200, 70);
			g.drawString("Quit", 425, 525);
		}
		
		else if(game.gameState == state.Help) {
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Use the WASD keys to dodge the enemies", 150, 200);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Press the escape key to pause the game", 165, 300);
			
			g.setFont(font2);
			g.drawRect(360, 480, 200, 70);
			g.drawString("Back", 425, 525);
		}
		
		else if (game.gameState == state.End || game.gameState == state.Win) {
			g.setFont(font2);
			g.setColor(Color.white);
			
			if (game.gameState == state.End) {
				g.drawString("Game Over", 380, 200);
			}
			else {
				g.drawString("YOU WIN", 390, 200);
			}
			
			g.setFont(font2);
			g.drawRect(360, 300, 200, 70);
			g.drawString("Play Again", 385, 345);
			
			g.setFont(font2);
			g.drawRect(360, 390, 200, 70);
			g.drawString("Main Menu", 385, 435);
		}
		
		
	}
}
