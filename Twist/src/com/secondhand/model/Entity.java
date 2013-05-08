package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Entity {

	private boolean isEdible;
	protected IPhysicsEntity physics;
	
	protected final GameWorld gameWorld;
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final Vector2 initialPosition;

	public Entity(final Vector2 position, final boolean isEdible,
			final GameWorld level) {
		this.isEdible = isEdible;
		this.initialPosition = position;
		this.gameWorld = level;
		bodyScheduledForDeletion = false;
	}
	
	public Vector2 getInitialPosition() {
		return this.initialPosition;
	}

	public void setPhysics(final IPhysicsEntity physics){
		this.physics = physics;
		onPhysicsAssigned();
	}
	
	public abstract void onPhysicsAssigned();

	public abstract boolean isCircle();


	public boolean isEdible() {
		return this.isEdible;
	}
	
	public IPhysicsEntity getPhysics(){
		return this.physics;
	}

	public void setIsEdible(final boolean isEdible) {
		this.isEdible = isEdible;
	}

	public abstract float getRadius();
	
	// how much every unit(pixel) of radius is worth in points.
	public abstract float getScoreWorth();

	public int getScoreValue() {
		return (int) (this.getRadius() * this.getScoreWorth());
	}

	// remove this entity from andengine rendering and the physics world.
	private void removeEntity() {
		this.gameWorld.getEntityManager().removeEntityFromList(this);

		destroyEntity();

	}

	// TODO detaching should be done by view or physics, wherever 
	// we save the shape
	public void destroyEntity() {

		// we can't remove the body within a contact listener
		scheduleBodyForDeletion();

		// Detach the shape from AndEngine-rendering
		// do this from IPhysicsEntity instead.
		this.physics.detachSelf();
	}

	private boolean bodyScheduledForDeletion;

	private void scheduleBodyForDeletion() {
		this.gameWorld.getEntityManager().scheduleEntityForDeletion(this);
		this.bodyScheduledForDeletion = true;
	}

	// only valid when the body has been scheduled for deletion.
	public void deleteBody() {
		physics.deleteBody();
	}

	// called when this entity is eaten up.
	protected void wasEaten() {
		removeEntity();
	}

	public boolean isRotating() {
		return this instanceof Player ? false : true;
	}
	
	public void addPropertyChangeListener(final PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}

	
	 public float getCenterX() {
		 return this.physics.getCenterX();
	}
	 
	 public float getCenterY() {
		return  this.physics.getCenterY();
	}

}
