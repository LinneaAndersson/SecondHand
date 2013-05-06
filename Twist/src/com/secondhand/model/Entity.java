package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.debug.MyDebug;

public abstract class Entity {

	private IShape shape;
	private boolean isEdible;
	protected IPhysicsEntity physics;

	protected final GameWorld gameWorld;
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public Entity(final Shape shape, final boolean isEdible,
			final GameWorld level) {
		//this.shape = shape;
		this.isEdible = isEdible;
		this.gameWorld = level;
		bodyScheduledForDeletion = false;
		MyDebug.d("now we have created a entity");
	}

	public void setPhysics(IPhysicsEntity physics){
		this.physics = physics;
		createType();
		MyDebug.d("is this null?" +this.getCenterX());
		MyDebug.d("is this null?" +this.getCenterY());
		MyDebug.d("is this null?" +this.getRadius());
	}
	
	public abstract void createType();

	public abstract boolean isCircle();

	public IShape getShape() {
		return shape;
	}

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

	public float getCenterX() {
		return physics.getCenterX();
	}

	public float getCenterY() {
		return physics.getCenterY();
	}
	
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
		getShape().detachSelf();
	}

	private boolean bodyScheduledForDeletion;

	private boolean isBodyScheduledForDeletion() {
		return this.bodyScheduledForDeletion;
	}

	private void scheduleBodyForDeletion() {
		//pcs.firePropertyChange(propertyName, oldValue, newValue)
		
		this.gameWorld.getEntityManager().scheduleEntityForDeletion(this);

		this.bodyScheduledForDeletion = true;
	}

	// only valid when the body has been scheduled for deletion.
	public void deleteBody() {
		physics.deleteBody(isBodyScheduledForDeletion());
	}

	// called when this entity is eaten up.
	protected void wasEaten() {
		removeEntity();
	}

	public boolean getRotation() {
		return this instanceof Player ? false : true;
	}
	
	public void addPropertyChangeListener(final PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
}
