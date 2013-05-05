package com.secondhand.view.physics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.CircleEntity;
import com.secondhand.model.CollisionResolver;
import com.secondhand.model.Enemy;
import com.secondhand.model.Entity;
import com.secondhand.model.GameWorld;
import com.secondhand.model.IPhysics;
import com.secondhand.model.Obstacle;
import com.secondhand.model.Planet;
import com.secondhand.model.RectangleEntity;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.opengl.Polygon;
import com.secondhand.view.scene.GamePlayScene;

public class Physics implements IPhysics {
	private final PhysicsWorld physicsWorld;
	private PhysicsConnector physicsConnector;
	private final PhysicsEnemyUtil enemyUtil;
	private CollisionResolver collisionResolver;
	private final PhysicsWorldBounds bounds;
	private GameWorld gameWorld;

	// no vector needed because its zero gravity. And if the constructor
	// needs an vector that means we need to to import Vector2
	// wherever we creates Physics
	// TODO:remove vector2.

	public Physics() {

		physicsWorld = new PhysicsWorld(new Vector2(), true);
		bounds = new PhysicsWorldBounds(physicsWorld);
		enemyUtil = new PhysicsEnemyUtil(physicsWorld);
		this.physicsWorld.setVelocityIterations(16);
		this.physicsWorld.setPositionIterations(16);

	}

	public PhysicsWorld getPhysicsWorld() {
		return this.physicsWorld;
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

	//@Override
	public void registerBody(final Entity entity, final Body body) {
		body.setUserData(entity);

		physicsWorld.registerPhysicsConnector(new CustomPhysicsConnector(entity
				.getShape(), entity.isCircle(),body, true,
				entity.getRotation()));
	}

	// andEngine or box2d coordinates in? and depending on from
	// where we call the method we could perhaps have an vector as input.
	// We souldn't need to do much more here, all other calculations should
	// be done in model. Entity instead of body and then somehow get body?
	// All entities that need this function are enemies and player.
	@Override
	public void applyImpulse(final Body body, final float posX, final float posY, final float maxSpeed) {
		
		final Vector2 position = new Vector2(posX
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, posY
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		final Vector2 force = new Vector2(body.getWorldCenter().x - posX,
				body.getWorldCenter().y - posY);
		
		final Vector2 velocity = new Vector2(body
				.getLinearVelocity());
		
		if (velocity.add(force).len() > maxSpeed){
			// Check if new velocity doesn't exceed maxSpeed!
			return;
		}

		body.applyLinearImpulse(force, position);

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
	public void setConnector(final IShape shape) {
		physicsConnector = physicsWorld.getPhysicsConnectorManager()
				.findPhysicsConnectorByShape(shape);
	}

	@Override
	public void checkCollision(final Contact contact) {
		collisionResolver.checkCollision(contact.getFixtureA().getBody()
				.getUserData(), contact.getFixtureB().getBody().getUserData());
	}
	
	//TODO Enemy needs getCenterX,Y but when shapes is
	// moved from Entity some method here(probably) is needed to return center pos.
	//getCenterX,Y(Entity) or something.

	@Override
	public boolean isStraightLine(final Entity entity, final Enemy enemy) {
		return enemyUtil.straightLine(entity, enemy);
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
	public Body createType(final IShape shape, final Entity entity) {
		Body body = null;
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
		registerBody(entity, body);
		return body;
	}


	@Override
	public void setGameWorld(final GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		this.collisionResolver = new CollisionResolver(gameWorld);

	}

}
