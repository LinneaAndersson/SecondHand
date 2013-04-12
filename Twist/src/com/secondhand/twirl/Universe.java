package com.secondhand.twirl;

public class Universe {
	private static Level currentLevel;
	private static int levelNbr = 0;
	
	public Universe(){
		currentLevel = new Level();
	}
	
	public int getlvlNbr(){
		return levelNbr;
	}
	
	public Level getLevel(){
		return currentLevel;
	}
	
	public void NextLevel(){
		currentLevel = new Level();
	}
	
}
