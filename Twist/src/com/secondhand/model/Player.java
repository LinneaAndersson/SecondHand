package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.resource.Sounds;

public class Player extends BlackHole {

	private String name;
	
	private final PropertyChangeSupport powerUpListSupport = new PropertyChangeSupport(this);
	
	private final List<PowerUp> powerUpList = new ArrayList<PowerUp>(1) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(final PowerUp object) {
			object.activateEffect(Player.this);
			return super.add(object);
		};
		
		@Override
		public boolean remove(final Object object) {
			final boolean value = super.remove(object); // Priority: The list is empty when you remove last PowerUp
			((PowerUp)object).deactivateEffect(Player.this);
			return value;
		};
	};

	public Player(final Vector2 position, final float radius, final PhysicsWorld physicsWorld, final float maxSpeed) {
		super(position, radius, physicsWorld, false, maxSpeed);
	}


	public List<PowerUp> getPowerUps(){
		return powerUpList;
	}
	
	public void removePowerUp(final PowerUp powerUp) {
		this.powerUpList.remove(powerUp);
	}
	
	public void addPowerUp(final PowerUp powerUp){
		this.powerUpList.add(powerUp);			
		powerUpListSupport.firePropertyChange("ADD",null,powerUp);
	}
	
	public void setName(final String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addListener(final PropertyChangeListener observer) {
		powerUpListSupport.addPropertyChangeListener(observer);
	}
	
	public void reachToTouch(final Vector2 touch) {
		Vector2 movementVector = new Vector2((getCenterX() - touch.x),
				getCenterY() - touch.y);

		// the closer the touch is to the player, the more force do we need to
		// apply.

		// make it a bit slower depending on how big it is.
		movementVector = movementVector.mul(this.getRadius() * 0.001f);

		move(movementVector);
	}

	private Vector2 needsToMovePosition;
	
	// used to implement teleport, because you can't change the position inside a contact
	// listener for some retarded reason(that would be too simple, now wouldn't it!?) 
	// So fuck you Erin Catto.
	// - Sincerely, Eric
	public void setNeedsToMovePosition(final Vector2 position) {
		needsToMovePosition = position;
	}
	
	public void moveToNeededPositionIfNecessary() {
		if(needsToMovePosition != null) {
			this.getBody().setTransform(needsToMovePosition, this.getBody().getAngle());
			needsToMovePosition = null;
		}
	}
	
	@Override
	public float getScoreWorth() {
		throw new IllegalStateException("cannot score points for eating player");
	}
	

	protected void handlePowerUp(final PowerUp powerUp) {

		Sounds.getInstance().powerUpSound.play();
		
		addPowerUp(powerUp);
		
		powerUp.wasEaten();
	}

	protected void onGrow() {
		Sounds.getInstance().growSound.play();	
	}
	
	protected void entityWasTooBigToEat(final Entity entity) {
		if(entity instanceof Obstacle) {
			Sounds.getInstance().obstacleCollisionSound.play();
		}
	}
}
