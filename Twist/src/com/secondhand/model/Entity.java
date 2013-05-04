package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.debug.MyDebug;

public abstract class Entity {

	private Body body;
	private final IShape shape;
	private boolean isEdible;
	protected final IPhysics physics;
	protected final GameWorld level;

	protected enum Type {
		CIRCLE, RECTANGLE, POLYGON;
	}
	
	public Entity(final Shape shape, final boolean isEdible,
			final GameWorld level) {
		this.shape = shape;
		this.isEdible = isEdible;
		this.physics = level.getPhysics();
		this.level = level;
		bodyScheduledForDeletion = false;
	}

	protected final void registerBody(final Body body) {

		physics.registerBody(this, body);

	}

	public void setBody(Body body) {
		this.body = body;
		registerBody(body); 
		registerBody(body);
	}

	public float getX() {
		return shape.getX();
	}

	public abstract boolean isCircle();

	public float getY() {
		return shape.getY();
	}

	public Body getBody() {
		return body;
	}

	public IShape getShape() {
		return shape;
	}

	public boolean isEdible() {
		return this.isEdible;
	}

	public void setIsEdible(final boolean isEdible) {
		this.isEdible = isEdible;
	}

	public abstract float getRadius();

	public float getCenterX() {
		return getX();
	}

	public float getCenterY() {
		return getY();
	}

	// how much every unit(pixel) of radius is worth in points.
	public abstract float getScoreWorth();

	public int getScoreValue() {
		return (int) (this.getRadius() * this.getScoreWorth());
	}

	// remove this entity from andengine rendering and the physics world.
	private void removeEntity() {

		this.level.getEntityManager().removeEntityFromList(this);

		destroyEntity();

	}

	// detaching should be done by view or physics
	public void destroyEntity() {

		// we can't remove the body within a contact listener
		scheduleBodyForDeletion();

		// Detach the shape from AndEngine-rendering
		getShape().detachSelf();

	}

	private boolean bodyScheduledForDeletion;

	private boolean isBodyScheduledForDeletion() {
		return this.bodyScheduledForDeletion;
	}

	private void scheduleBodyForDeletion() {
		physics.setConnector(this.getShape());

		this.level.getEntityManager().scheduleEntityForDeletion(this);

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
}
