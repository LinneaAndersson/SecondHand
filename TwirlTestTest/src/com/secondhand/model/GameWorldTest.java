package com.secondhand.model;

import junit.framework.TestCase;

import com.badlogic.gdx.math.Vector2;

public class GameWorldTest extends TestCase {

	public void testConstructor(){

	}
	
	public void testRegisterEntities(){
		
	}
	
	public void testRegisterEntity(){
		
	}
	
	public void testMoveEntities(){
		
	}

	public void testcheckPlayerBigEnough(){
		//I don't know why, but somehow I can 
		// create a level-object in this method but no one else. ??
		
		//Checks the size of Player
		GameWorld level = new GameWorld(700);
		assertEquals(level.checkPlayerBigEnough(),true);
		
		level = new GameWorld(2200);
		assertEquals(level.checkPlayerBigEnough(),false);
		
		//Checks that the player gets the right vector
		float xPosition = level.getPlayer().getX();
		float yPosition = level.getPlayer().getY();
		Vector2 v = new Vector2(5,0);
		level.sendTouchInput(v);
		float xDir = xPosition - 5;
		float yDir = yPosition - 0;
		Vector2 playerDirection = new Vector2(xDir,yDir);
		assertEquals(level.getPlayer().getBody().getLinearVelocity(),playerDirection);
		
		//Checks that we start on level1.
		assertEquals(level.getLevelNumber(),1);
		
		//checks that we get the right Width in the level.
		assertEquals(level.getLevelWidth(),2000);
	}
}
