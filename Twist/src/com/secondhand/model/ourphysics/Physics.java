package com.secondhand.model.ourphysics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.model.Entity;
import com.secondhand.model.physics.CustomPhysicsConnector;

public class Physics implements IPhysics {
	private PhysicsWorld physicsWorld;
	private Body[] bodies;
	private IShape[] worldBounds;

	// no vector needed because its zero gravity. And if the constructor
	// needs an vector that means we need to to import Vector2
	// wherever we creates Physics
	public Physics(Vector2 vector) {
		physicsWorld = new PhysicsWorld(vector, true);
	}

	public void init() {
		this.physicsWorld.setVelocityIterations(16);
		this.physicsWorld.setPositionIterations(16);
	}

	// put some invisible, static rectangles that keep the player within the
	// world bounds:
	// we do not do this using registerEntity, because these bodies are
	// static.
	public void setWorldBounds(IShape[] shape) {
		bodies = new Body[4];

		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,
				0.5f, 0.5f);

		bodies[0] = PhysicsFactory.createBoxBody(physicsWorld, shape[0],
				BodyType.StaticBody, wallFixtureDef);
		bodies[1] = PhysicsFactory.createBoxBody(physicsWorld, shape[1],
				BodyType.StaticBody, wallFixtureDef);
		bodies[2] = PhysicsFactory.createBoxBody(physicsWorld, shape[2],
				BodyType.StaticBody, wallFixtureDef);
		bodies[3] = PhysicsFactory.createBoxBody(physicsWorld, shape[3],
				BodyType.StaticBody, wallFixtureDef);
	}

	@Override
	public void removeWorldBounds() {
		for (int i = 0; i < 4; ++i) {
			this.physicsWorld.destroyBody(bodies[i]);
			this.worldBounds[i].detachSelf();
		}
	}

	@Override
	public void registerBody(Entity entity, Body body, Boolean rotation) {
		body.setUserData(entity);

		physicsWorld.registerPhysicsConnector(new CustomPhysicsConnector(entity
				.getShape(), entity.isCircle(), entity.getBody(), true,
				rotation));

	}

	// andEngine or box2d coordinates in? and depending on from
	// where we call the method we could perhaps have an vector as input
	@Override
	public void applyImpulse(Body body, float posX, float posY) {
		posX /= PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
		posY /= PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

		Vector2 position = new Vector2(posX, posY);
		Vector2 force = new Vector2(body.getWorldCenter().x - posX,
				body.getWorldCenter().y - posY);
		
		body.applyLinearImpulse(force, position);

	}

}
