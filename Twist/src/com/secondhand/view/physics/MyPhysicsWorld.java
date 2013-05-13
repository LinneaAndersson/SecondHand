package com.secondhand.view.physics;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.secondhand.model.entity.CollisionResolver;
import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.physics.IPhysicsWorld;

public class MyPhysicsWorld implements IPhysicsWorld {
	private PhysicsWorld physicsWorld;
	// TODO only enemy needs the util class
	private CollisionResolver collisionResolver;
	private final PhysicsWorldBounds bounds;

	public MyPhysicsWorld(final PhysicsWorld physicsWorld){

		// TODO remove physicsWorld from constructor
		// and put worldBoundries somewhere else
		this.physicsWorld = physicsWorld;
		bounds = new PhysicsWorldBounds(physicsWorld);
		this.physicsWorld.setVelocityIterations(8);
		this.physicsWorld.setPositionIterations(8);

	}

	public PhysicsWorld getPhysicsWorld() {
		return this.physicsWorld;
	}

	public void setPhysicsWorld(final PhysicsWorld physicsWorld) {
		this.physicsWorld = physicsWorld;
	}

	// put some invisible, static rectangles that keep the player within the
	// world bounds:
	// we do not do this using registerEntity, because these bodies are
	// static.
	@Override
	public void setWorldBounds(final int levelWidth, final int levelHeight) {
		bounds.setWorldBounds(levelWidth, levelHeight);
	}

	@Override
	public void removeWorldBounds() {
		bounds.removeBounds();
	}

	public void checkCollision(final Contact contact) {
		collisionResolver.checkCollision(contact.getFixtureA().getBody()
				.getUserData(), contact.getFixtureB().getBody().getUserData());
	}

	public boolean isAreaUnOccupied(final float x, final float y, final float r) {
		return PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(x - r,
				y + r), new Vector2(x + r, y - r), physicsWorld);
	}

	@Override
	public void setContactListener() {
		physicsWorld.setContactListener(new CollisionContactListener(this));

	}
	
	@Override
	public void setGameWorld(final GameWorld gameWorld) {
		this.collisionResolver = new CollisionResolver(gameWorld);
	}
}
