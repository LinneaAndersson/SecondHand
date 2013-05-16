package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.Vector2;


public abstract class Entity implements IPhysicsObject{

	private boolean isEdible;
	protected IPhysicsEntity physics;
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final Vector2 initialPosition;

	public Entity(final Vector2 position, final boolean isEdible) {
		this.isEdible = isEdible;
		this.initialPosition = position;
	}
	
	public Vector2 getInitialPosition() {
		return this.initialPosition;
	}

	public void setPhysics(final IPhysicsEntity physics){
		this.physics = physics;
		onPhysicsAssigned();
	}
	
	public abstract void onPhysicsAssigned();

	@Override
	public boolean isEdible() {
		return this.isEdible;
	}
	
	public void detachSelf() {
		physics.detachSelf();

	}

	public void setIsEdible(final boolean isEdible) {
		this.isEdible = isEdible;
	}
	
	// how much every unit(pixel) of radius is worth in points.
	public abstract float getScoreWorth();

	public int getScoreValue() {
		return (int) (this.getRadius() * this.getScoreWorth());
	}

	// remove this entity from andengine rendering and the physics world.
	private void removeEntity() {		destroyEntity();
	}

	/* TODO: Just always use this instead of removeEntity and wasEaten?
	 * All those two methods do is call this one. */
	public void destroyEntity() {

		// we can't remove the body within a contact listener
		scheduleBodyForDeletion();

		this.physics.detachSelf();
	}

	private void scheduleBodyForDeletion() {
		pcs.firePropertyChange("isScheduleForDeletion", null, this);
	}

	// only valid when the body has been scheduled for deletion.
	public void deleteBody() {
		physics.deleteBody();
	}

	// called when this entity is eaten up.
	protected void wasEaten() {
		removeEntity();
	}
	
	public void addPropertyChangeListener(final PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	 public float getCenterX() {
		 return this.physics.getCenterX();
	}
	 
	 @Override
	 public float getCenterY() {
		return  this.physics.getCenterY();
	}
	 
	 
}
