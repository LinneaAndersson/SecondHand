package com.secondhand.model;

import java.util.ArrayList;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.powerup.PowerUp;

public class Player extends BlackHole {

	private  int score;
	private String name;
	
	private ArrayList<PowerUp> powerUpList = new ArrayList<PowerUp>(1) {
		@Override
		public boolean add(PowerUp object) {
			object.activateEffect(Player.this);
			return super.add(object);
		};
		
		@Override
		public boolean remove(Object object) {
			((PowerUp)object).deactivateEffect(Player.this);
			return super.remove(object);
		};
	};

	public Player(final Vector2 position, final float radius, final PhysicsWorld physicsWorld, float maxSpeed) {
		super(position, radius, physicsWorld, false, maxSpeed);
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(final int increase) {
		score += increase;
	}
	
	public ArrayList<PowerUp> getPowerUps(){
		return powerUpList;
	}
	
	public void removePowerUp(PowerUp powerUp) {
		this.powerUpList.remove(powerUp);
	}
	
	public void addPowerUp(final PowerUp powerUp){		
		this.powerUpList.add(powerUp);			
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
