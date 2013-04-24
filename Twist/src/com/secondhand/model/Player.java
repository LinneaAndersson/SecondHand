package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.powerup.PowerUp;

public class Player extends BlackHole {

	private  int score;
	private String name;
	
	private final PropertyChangeSupport powerUpListSupport = new PropertyChangeSupport(this);
	
	private final ArrayList<PowerUp> powerUpList = new ArrayList<PowerUp>(1) {
		@Override
		public boolean add(final PowerUp object) {
			object.activateEffect(Player.this);
			return super.add(object);
		};
		
		@Override
		public boolean remove(final Object object) {
			((PowerUp)object).deactivateEffect(Player.this);
			return super.remove(object);
		};
	};

	public Player(final Vector2 position, final float radius, final PhysicsWorld physicsWorld, final float maxSpeed) {
		super(position, radius, physicsWorld, false, maxSpeed);
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(final int increase) {
		score += increase;
	}
	
	public List<PowerUp> getPowerUps(){
		return powerUpList;
	}
	
	public void removePowerUp(final PowerUp powerUp) {
		this.powerUpList.remove(powerUp);
	}
	
	public void addPowerUp(final PowerUp powerUp){
		this.powerUpList.add(powerUp);			
		powerUpListSupport.firePropertyChange("ADD",null,null);
	}

	public void setName(final String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addListener(PropertyChangeListener observer) {
		powerUpListSupport.addPropertyChangeListener(observer);
	}
	
	public void reachToTouch(final Vector2 touch) {
		Vector2 movementVector = new Vector2((getCenterX() - touch.x),
				getCenterY() - touch.y);

		// the closer the touch is to the player, the more force do we need to
		// apply.

		// make it a bit slower depending on how big it is.
		movementVector = movementVector.mul(this.getRadius() * 0.001f);

		/*
		 * Made a better test, like I said the earlier test we did was not
		 * functional, and since the length of a vector is always positive, the
		 * Math.abs(player.getBody().getLinearVelocity().len()) has no effect.
		 * This runs rather smoothly. Try it!
		 */
		final Vector2 testVector = new Vector2(this.getBody()
				.getLinearVelocity());
		if (testVector.add(movementVector).len() > this.getMaxSpeed()){
			// Check if new velocity doesn't exceed maxSpeed!
			return;
		}

		this.getBody().applyLinearImpulse(movementVector,
				this.getBody().getWorldCenter());

	}
}
