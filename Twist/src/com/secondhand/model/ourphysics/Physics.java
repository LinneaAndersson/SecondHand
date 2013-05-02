package com.secondhand.model.ourphysics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import android.graphics.drawable.shapes.Shape;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Physics extends IPhysics {
	private PhysicsWorld physicsWorld;
	private Body[] bodies;

	public Physics(Vector2 vector) {
		physicsWorld = new PhysicsWorld(vector, true);
	}

	@Override
	public void init() {
		this.physicsWorld.setVelocityIterations(16);
		this.physicsWorld.setPositionIterations(16);
	}

	// put some invisible, static rectangles that keep the player within the
	// world bounds:
	// we do not do this using registerEntity, because these bodies are
	// static.
	public void setWorldBounds(IShape shape1, IShape shape2, IShape shape3,
			IShape shape4) {
		bodies = new Body[4];

		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,
				0.5f, 0.5f);

		bodies[0] = PhysicsFactory.createBoxBody(physicsWorld, shape1,
				BodyType.StaticBody, wallFixtureDef);
		bodies[1] = PhysicsFactory.createBoxBody(physicsWorld, shape2,
				BodyType.StaticBody, wallFixtureDef);
		bodies[2] = PhysicsFactory.createBoxBody(physicsWorld, shape3,
				BodyType.StaticBody, wallFixtureDef);
		bodies[3] = PhysicsFactory.createBoxBody(physicsWorld, shape4,
				BodyType.StaticBody, wallFixtureDef);
	}

}
