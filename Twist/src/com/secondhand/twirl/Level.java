package com.secondhand.twirl;

import java.util.List;

public class Level {

	private List<Entity> entities;
	private int PLAYER_MAX_SIZE;
	
	public Level(int maxSize){
		PLAYER_MAX_SIZE = maxSize ;
	}
}
