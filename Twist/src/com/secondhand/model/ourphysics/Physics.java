package com.secondhand.model.ourphysics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.Entity;
import com.secondhand.model.physics.CustomPhysicsConnector;

public class Physics implements IPhysics {
	private PhysicsWorld physicsWorld;
	private Body[] bodies;
	private IShape[] worldBounds;
	private PhysicsConnector physicsConnector;

	public Physics(Vector2 vector) {
		//TODO: Will be here later.
		//physicsWorld = new PhysicsWorld(vector, true);
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
		for(int i = 0; i < 4; ++i) {
			this.physicsWorld.destroyBody(bodies[i]);
			this.worldBounds[i].detachSelf();
		}
	}

	@Override
	public void registerBody(Entity entity, Body body, Boolean rotation) {
		body.setUserData(entity);

		physicsWorld.registerPhysicsConnector(new CustomPhysicsConnector(entity.getShape(),entity.isCircle(), entity.getBody(), true, rotation));


	}

	public void deleteBody(Boolean scheduledBody) {
		if(!scheduledBody) {
			throw new IllegalStateException("Body not scheduled for deletion!");
		}
			
		physicsWorld.unregisterPhysicsConnector(physicsConnector);
					
		MyDebug.i(physicsConnector.getBody() + " will be destroyed");
							
		physicsWorld.destroyBody(physicsConnector.getBody());
			
		MyDebug.i(physicsConnector.getBody() + " destruction complete");
	}

	//TODO: Will remove thi later, just for testing it works okej.
	@Override
	public void setPhysicsWorld(PhysicsWorld p) {
		this.physicsWorld = p;
		init();
		
	}
	
	public void setConnector(IShape shape){
		physicsConnector = physicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(
				shape);
	}

}
