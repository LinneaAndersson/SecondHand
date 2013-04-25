package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.physics.CustomPhysicsConnector;
import com.secondhand.physics.PhysicsDestroyer;

public abstract class Entity {

	private final Body body;
	private final IShape shape;
	private boolean isEdible;
	private String assetName;
	protected final PhysicsWorld physicsWorld;
	
	protected final boolean updateRotation;
	
	public Entity(final Shape shape, final boolean isEdible, final Body body,  final boolean updateRotation,
			final PhysicsWorld physicsWorld) {
		this.body = body;
		this.shape = shape;
		this.isEdible = isEdible;
		this.updateRotation = updateRotation;
		this.physicsWorld = physicsWorld;
		
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
	
	public String getImageName(){
		return assetName;
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
	
	// called when this entity is eaten up.
	protected void wasEaten() {
		
		// TODO: removed enemies should probably also be removed from the  enemy updating list.
		
		// Detach the shape from AndEngine-rendering
		getShape().detachSelf();

		// remove the eaten entity from the physics world:
		PhysicsDestroyer.getInstance().destroy(getShape(), true);

	}
}

