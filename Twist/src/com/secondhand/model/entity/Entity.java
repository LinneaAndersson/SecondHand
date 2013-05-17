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
	
	public final static String IS_SCHEDULED_FOR_DELETION = "isScheduleForDeletion";
	
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

	public void setIsEdible(final boolean isEdible) {
		this.isEdible = isEdible;
	}
	
	// how much every unit(pixel) of radius is worth in points.
	protected abstract float getScoreWorth();

	public int getScoreValue() {
		return (int) (this.getRadius() * this.getScoreWorth());
	}

	private void scheduleBodyForDeletion() {
		pcs.firePropertyChange(IS_SCHEDULED_FOR_DELETION, null, this);
	}

	// only valid when the body has been scheduled for deletion.
	public void deleteBody() {
		physics.deleteBody();
	}

	// called when this entity is eaten up.
	protected void wasEaten() {

		// we can't remove the body within a contact listener
		scheduleBodyForDeletion();
		
		this.physics.detachSelf();
	}
		
	public void addListener(final PropertyChangeListener observer) {
		this.pcs.addPropertyChangeListener(observer);
	}
	
	public void removeListener(final PropertyChangeListener observer) {
		this.pcs.removePropertyChangeListener(observer);
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
