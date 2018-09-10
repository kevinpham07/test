package com.codingdojo.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.codingdojo.game.Game.state;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	
	//sticky key problem
	private boolean[] keyDown = new boolean[4];
	
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
	
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println(key);
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			//PLAYER 1
			if(temp.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					temp.setVelY(-handler.spd);
					keyDown[0] = true;
				}
			}
			if(temp.getId() == ID.Player) {
				if(key == KeyEvent.VK_S) {
					temp.setVelY(handler.spd);
					keyDown[1] = true;
				}
			}
			if(temp.getId() == ID.Player) {
				if(key == KeyEvent.VK_A) {
					temp.setVelX(-handler.spd);
					keyDown[2] = true;
				}
			}
			if(temp.getId() == ID.Player) {
				if(key == KeyEvent.VK_D) {
					temp.setVelX(handler.spd);
					keyDown[3] = true;
				}
			}
		}
		if(key == KeyEvent.VK_ESCAPE) {
			if(game.gameState == state.Game) {
				if(Game.paused == true) {
					Game.paused = false;
					Audio.getMusic("game").resume();
				}
				else {
					Game.paused = true;
				}
				
			}
		}
		if(key == KeyEvent.VK_SPACE) {
			if(game.gameState == state.Game) {
				game.gameState = state.Shop;
			}
			else if(game.gameState == state.Shop) {
				game.gameState = state.Game;
			}
		}
		
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			//PLAYER CONTROLS
			if(temp.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					keyDown[0] = false;
				}
				if(temp.getId() == ID.Player) {
					if(key == KeyEvent.VK_S) {
						keyDown[1] = false;
					}
				}
				if(temp.getId() == ID.Player) {
					if(key == KeyEvent.VK_A) {
						keyDown[2] = false;
					}
				}
				if(temp.getId() == ID.Player) {
					if(key == KeyEvent.VK_D) {
						keyDown[3] = false;
					}
				}
				
				if(!keyDown[0] && !keyDown[1]) {
					temp.setVelY(0);
				}
				if(!keyDown[2] && !keyDown[3]) {
					temp.setVelX(0);
				}
			}
		}
	}
}
