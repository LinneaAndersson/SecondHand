package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.debug.MyDebug;
import com.secondhand.physics.CustomPhysicsConnector;

public abstract class Entity {

	private final Body body;
	private final IShape shape;
	private boolean isEdible;
	protected final PhysicsWorld physicsWorld;
	protected final Level level;
	
	protected final boolean updateRotation;
	
	public Entity(final Shape shape, final boolean isEdible, final Body body,  final boolean updateRotation,
			final Level level) {
		this.body = body;
		this.shape = shape;
		this.isEdible = isEdible;
		this.updateRotation = updateRotation;
		this.physicsWorld = level.getPhysicsWorld();
		this.level = level;
		bodyScheduledForDeletion = false;
		
		registerBody(body); //NOPMD
	}
	
	protected final void registerBody(final Body body) {
		// we need this when doing collisions handling between entities and
		// black holes:
		body.setUserData(this);
		physicsWorld.registerPhysicsConnector(new CustomPhysicsConnector(this.getShape(),isCircle(), this.body, true, updateRotation));
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
		return (int)(this.getRadius() * this.getScoreWorth());
	}
	
	// remove this entity from andengine rendering and the physics world.
	private void removeEntity() {

		level.removeEntityFromList(this);
		
		destroyEntity();
	}
	
	public void destroyEntity() {
		

		// we can't remove the body within a contact listener
		scheduleBodyForDeletion();
		
		// Detach the shape from AndEngine-rendering
		getShape().detachSelf();

	}
	
	private boolean bodyScheduledForDeletion;
	// used when deleting the body.
	public PhysicsConnector physicsConnector;
	
	private boolean isBodyScheduledForDeletion() {
		return this.bodyScheduledForDeletion;
	}
	
	private void scheduleBodyForDeletion() {
		physicsConnector = physicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(
						this.getShape());	
		
		this.level.scheduleEntityForDeletion(this);
		
		this.bodyScheduledForDeletion = true;
	}
	
	// only valid when the body has been scheduled for deletion.
	public void deleteBody() {
		
		if(!this.isBodyScheduledForDeletion()) {
			throw new IllegalStateException("Body not scheduled for deletion!");
		}
			
		physicsWorld.unregisterPhysicsConnector(physicsConnector);
					
		MyDebug.i(physicsConnector.getBody() + " will be destroyed");
							
		physicsWorld.destroyBody(physicsConnector.getBody());
			
		MyDebug.i(physicsConnector.getBody() + " destruction complete");
	}
	
	// called when this entity is eaten up.
	protected void wasEaten() {
		removeEntity();
	}
}

