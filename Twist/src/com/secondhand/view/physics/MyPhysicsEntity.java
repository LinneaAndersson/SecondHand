package com.secondhand.view.physics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.Entity;
import com.secondhand.model.IPhysicsEntity;
import com.secondhand.view.opengl.Circle;

public class MyPhysicsEntity implements IPhysicsEntity{
	private Body body;
	private final PhysicsWorld physicsWorld;
	private final PhysicsConnector physicsConnector;
	private final IShape shape;
	
	public MyPhysicsEntity(PhysicsWorld physicsWorld, final Entity entity , final IShape shape,
			Body body){
		this.physicsWorld = physicsWorld;
		
		body.setUserData(entity);
		physicsConnector = new CustomPhysicsConnector(shape, entity.isCircle(),
				body, true, entity.getRotation());
		physicsWorld.registerPhysicsConnector(physicsConnector);
		
		this.shape = shape;
	}
	
	// TODO andengine or box2d coordinates?
	/*	@Override
		public float getCenterX() {
			return body.getWorldCenter().x;
		}

		@Override
		public float getCenterY() {
			return body.getWorldCenter().y;
		}*/
		/*
		@Override
		public void registerBody(final Entity entity, final Body body, final IShape shape) {
			
		}*/
		
		// andEngine or box2d coordinates in? and depending on from
		// where we call the method we could perhaps have an vector as input.
		// We souldn't need to do much more here, all other calculations should
		// be done in model. Entity instead of body and then somehow get body?
		// All entities that need this function are enemies and player.
		@Override
		public void applyImpulse(final float posX, final float posY,
				final float maxSpeed) {

			final Vector2 force = new Vector2(posX, posY);

			final Vector2 velocity = new Vector2(body.getLinearVelocity());

			if (velocity.add(force).len() > maxSpeed) {
				// Check if new velocity doesn't exceed maxSpeed!
				return;
			}

			body.applyLinearImpulse(force, body.getWorldCenter());

		}
		
		@Override
		public void deleteBody(final boolean scheduledBody) {
			if (!scheduledBody) {
				throw new IllegalStateException("Body not scheduled for deletion!");
			}

			physicsWorld.unregisterPhysicsConnector(physicsConnector);

			MyDebug.i(physicsConnector.getBody() + " will be destroyed");

			physicsWorld.destroyBody(physicsConnector.getBody());

			MyDebug.i(physicsConnector.getBody() + " destruction complete");
		}
		
		/*
		@Override
		public Body createType(final IShape shape, final Entity entity) {
			MyDebug.d("in createtype in Myphysics...");
			if (entity instanceof Obstacle) {
				final Polygon polygon = (Polygon) shape;
				body = MyPhysicsFactory.createPolygonBody(physicsWorld, polygon,
						BodyType.DynamicBody, FixtureDefs.OBSTACLE_FIXTURE_DEF);
			} else if (entity instanceof Planet) {
				final Circle circle = (Circle) shape;
				body = PhysicsFactory.createCircleBody(physicsWorld, circle.getX(),
						circle.getY(), circle.getRadius(), circle.getRotation(),
						BodyType.DynamicBody, FixtureDefs.PLANET_FIXTURE_DEF);
			} else if (entity instanceof CircleEntity) {
				MyDebug.d("in createtype in Myphysics...");
				final Circle circle = (Circle) shape;
				MyDebug.d("in createtype in Myphysics..." + circle.getX() + "    " + circle.getY() + "   " + circle.getRadius() + "    " + circle.getRotation());
				body = PhysicsFactory.createCircleBody(physicsWorld, circle.getX(),
						circle.getY(), circle.getRadius(), circle.getRotation(),
						BodyType.DynamicBody, FixtureDefs.BLACK_HOLE_FIXTURE_DEF);
				MyDebug.d("in createtype in Myphysics...");
			} else if (entity instanceof RectangleEntity) {
				final RectangularShape rectangle = (RectangularShape) shape;
				body = PhysicsFactory.createCircleBody(physicsWorld, rectangle,
						BodyType.DynamicBody, FixtureDefs.POWER_UP_FIXTURE_DEF);
			}
			registerBody(entity, body, shape);

			return body;
		}*/
		
		@Override
		public Body getBody(){
			return body;
		}
		
		@Override
		public void setLinearDamping(float linearDamping) {
			body.setLinearDamping(linearDamping);
			
		}

		@Override
		public float getRadius() {
			final Circle circle = (Circle)this.shape;
			return circle.getRadius();
		}
		


}
