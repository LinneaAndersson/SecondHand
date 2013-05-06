package com.secondhand.view.physics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.*;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.opengl.Polygon;

public class MyPhysicsEntity implements IPhysicsEntity{
	private Body body;
	private PhysicsWorld physicsWorld;
	private PhysicsConnector physicsConnector;
	
	public MyPhysicsEntity(PhysicsWorld physicsWorld){
		this.physicsWorld = physicsWorld;
	}
	
	// TODO andengine or box2d coordinates?
		@Override
		public float getCenterX() {
			return body.getWorldCenter().x;
		}

		@Override
		public float getCenterY() {
			return body.getWorldCenter().y;
		}
		
		@Override
		public void registerBody(final Entity entity, final Body body, final IShape shape) {
			body.setUserData(entity);
			physicsConnector = new CustomPhysicsConnector(shape, entity.isCircle(),
					body, true, entity.getRotation());
			physicsWorld.registerPhysicsConnector(physicsConnector);
		}
		
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
		
		@Override
		public Body createType(final IShape shape, final Entity entity) {
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
				final Circle circle = (Circle) shape;
				body = PhysicsFactory.createCircleBody(physicsWorld, circle.getX(),
						circle.getY(), circle.getRadius(), circle.getRotation(),
						BodyType.DynamicBody, FixtureDefs.BLACK_HOLE_FIXTURE_DEF);
			} else if (entity instanceof RectangleEntity) {
				final RectangularShape rectangle = (RectangularShape) shape;
				body = PhysicsFactory.createCircleBody(physicsWorld, rectangle,
						BodyType.DynamicBody, FixtureDefs.POWER_UP_FIXTURE_DEF);
			}
			registerBody(entity, body, shape);

			return body;
		}
		
		@Override
		public Body getBody(){
			return body;
		}
		


}
