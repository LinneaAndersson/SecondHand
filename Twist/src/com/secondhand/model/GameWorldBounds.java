package com.secondhand.model;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.secondhand.debug.MyDebug;

public class GameWorldBounds {
	
	private PhysicsWorld physicsWorld;
	
	private Body[] bodies;
	private Shape[]worldBounds;
	
	
	public final void setupWorldBounds(final int levelWidth, final int levelHeight, final PhysicsWorld physicsWorld) {

		this.physicsWorld = physicsWorld;
		
		worldBounds = new Shape[4];
		 bodies = new Body[4];
			
		// put some invisible, static rectangles that keep the player within the
		// world bounds:
		// we do not do this using registerEntity, because these bodies are
		// static.

		worldBounds[0] = new Rectangle(0, levelHeight - 2, levelWidth, 2);
		worldBounds[1] = new Rectangle(0, 0, levelWidth, 2);
		worldBounds[2] = new Rectangle(0, 0, 2, levelHeight);
		worldBounds[3] = new Rectangle(levelWidth - 2, 0, 2, levelHeight);
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,
				0.5f, 0.5f);
		
		bodies[0] = PhysicsFactory.createBoxBody(physicsWorld, worldBounds[0],
				BodyType.StaticBody, wallFixtureDef);
		bodies[1] = PhysicsFactory.createBoxBody(physicsWorld, worldBounds[1],
				BodyType.StaticBody, wallFixtureDef);
		bodies[2] = PhysicsFactory.createBoxBody(physicsWorld, worldBounds[2],
				BodyType.StaticBody, wallFixtureDef);
		bodies[3] = PhysicsFactory.createBoxBody(physicsWorld, worldBounds[3],
				BodyType.StaticBody, wallFixtureDef);
	}
	
	public void removeWorldBounds() {
		
		for(int i = 0; i < 4; ++i) {
			this.physicsWorld.destroyBody(bodies[i]);
			this.worldBounds[i].detachSelf();
		}
	}
}
