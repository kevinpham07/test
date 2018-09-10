package com.codingdojo.game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Audio {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void init() {
		
		try {
			
			soundMap.put("menu", new Sound("res/ffvii_save_load.ogg"));
			soundMap.put("hit", new Sound("res/ffvii_hit.ogg"));
			musicMap.put("game", new Music("res/ffvii_let_the_battles_begin.ogg"));
			musicMap.put("main_menu", new Music("res/ffvii_menu_bg.ogg"));
			musicMap.put("game_over", new Music("res/ffvii_continue.ogg"));
			musicMap.put("boss", new Music("res/ffvii_fight_on.ogg"));
			musicMap.put("win", new Music("res/ffvii_fanfare.ogg"));
			
		} catch (SlickException e) {
			
			e.printStackTrace();
			
		}
		
	}
	//BACKGROUD MUSIC
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	//STOP MUSIC
	public static void stopMenuMusic() {
		musicMap.remove("music").stop();
	}
	
	//MENU SOUNDS
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}

	
}
