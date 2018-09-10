package com.codingdojo.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -3151438546829393853L;

	public static final int WIDTH = 900, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	private Shop shop;
	
	public enum state {
		Menu,
		Game,
		Help,
		End,
		Win,
		Shop
	};
	
	public state gameState = state.Menu;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler);
		menu = new Menu(this, handler, shop);
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		Audio.init();
		Audio.getMusic("main_menu").loop(1, (float) 0.02);
		
		new Window(WIDTH, HEIGHT, "A game", this);
		
		spawn = new Spawn(handler);
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1) {
				tick();
				delta--;
			}
			if(running) {
				render();
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		
		if(gameState == state.Game) {
			if(!paused) {
				handler.tick();
				hud.tick(handler);
				spawn.tick();
				if(HUD.health <= 0) {
					gameState = state.End;
					handler.clearEnemies();
					Audio.getMusic("game_over").play(1, (float) 0.02);
					
				}
				if(HUD.getLevel() == 20) {
					gameState = state.Win;
					handler.clearEnemies();
					Audio.getMusic("win").play(1, (float) 0.02);
				}
			}
			else {
				Audio.getMusic("game").pause();
			}
		}
		else if(gameState == state.Menu || gameState == state.Help || gameState == state.End || gameState == state.Win) {
			menu.tick();
			handler.tick();			
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,  0, WIDTH, HEIGHT);
		
		if(paused) {
			Font font = new Font("arial", 1, 50);
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString("PAUSED", 350, 200);
		}
		
		if(gameState == state.Game ) {
			hud.render(g);
			handler.render(g);
		}
		else if(gameState == state.Menu || gameState == state.Help || gameState == state.End || gameState == state.Win) {
			menu.render(g);
			handler.render(g);
		}
		else if (gameState == state.Shop) {
			shop.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
	public static float clamp (float var, float min, float max) {
		if(var >= max) {
			return var = max;
		}
		else if(var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
}
