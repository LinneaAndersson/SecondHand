package com.seconhand.model;


public class Universe {
	private static Level currentLevel;
	
	// what level the player is on
	private static int levelNbr = 1;
	
	//perhaps create a tutorialLevel?
	public Universe(){
		currentLevel = new Level();
	}
	
	public int getlvlNbr(){
		return levelNbr;
	}
	
	public Level getLevel(){
		return currentLevel;
	}
	
	//TODO how to decide what to have on each successive level? 
	public void NextLevel(){
		currentLevel = new Level();
	}
	
}
