package com.secondhand.model.physics;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.Enemy;
import com.secondhand.model.CollisionResolver;
import com.secondhand.model.Entity;
import com.secondhand.model.GameWorld;

public class Physics implements IPhysics {
	private PhysicsWorld physicsWorld;
	private Body[] bodies;
	private IShape[] worldBounds;
	private PhysicsConnector physicsConnector;
	private PhysicsEnemyUtil enemyUtil;
	private final CollisionResolver collisionResolver;
	private PhysicsWorldBounds bounds;

	// no vector needed because its zero gravity. And if the constructor
	// needs an vector that means we need to to import Vector2
	// wherever we creates Physics
	// TODO:remove vector2.

	public Physics(final GameWorld gameWorld, final Vector2 vector) {
		// TODO: Will be here later.
		// physicsWorld = new PhysicsWorld(vector, true);
		//bounds = new PhysicsWorldBounds()
			//enemyUtil = new EnemyUtil(physicsWorld);
		this.collisionResolver = new CollisionResolver(gameWorld);

	}

	public void init() {
		this.physicsWorld.setVelocityIterations(16);
		this.physicsWorld.setPositionIterations(16);
	}

	// put some invisible, static rectangles that keep the player within the
	// world bounds:
	// we do not do this using registerEntity, because these bodies are
	// static.
	public void setWorldBounds(final int levelWidth, final int levelHeight) {
		bounds.setWorldBounds(levelWidth, levelHeight);
	}

	@Override
	public void removeWorldBounds() {
		bounds.removeBounds();
	}

	@Override
	public void registerBody(final Entity entity, final Body body,
			final boolean rotation) {
		body.setUserData(entity);

		physicsWorld.registerPhysicsConnector(new CustomPhysicsConnector(entity
				.getShape(), entity.isCircle(), entity.getBody(), true,
				rotation));

	}

	// andEngine or box2d coordinates in? and depending on from
	// where we call the method we could perhaps have an vector as input.
	// We souldn't need to do much more here, all other calculations should
	// be done in model. Entity instead of body and then somehow get body?
	// All entities that need this function are enemies and player.
	@Override
	public void applyImpulse(final Body body, final float posX, final float posY) {

		final Vector2 position = new Vector2(posX
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, posY
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		final Vector2 force = new Vector2(body.getWorldCenter().x - posX,
				body.getWorldCenter().y - posY);

		body.applyLinearImpulse(force, position);

	}

	public void deleteBody(Boolean scheduledBody) {
		if (!scheduledBody) {
			throw new IllegalStateException("Body not scheduled for deletion!");
		}

		physicsWorld.unregisterPhysicsConnector(physicsConnector);

		MyDebug.i(physicsConnector.getBody() + " will be destroyed");

		physicsWorld.destroyBody(physicsConnector.getBody());

		MyDebug.i(physicsConnector.getBody() + " destruction complete");
	}

	// TODO: Will remove thi later, just for testing it works okej.
	@Override
	public void setPhysicsWorld(PhysicsWorld p) {
		this.physicsWorld = p;
		init();
		enemyUtil = new PhysicsEnemyUtil(physicsWorld);
		bounds = new PhysicsWorldBounds(physicsWorld);
		
	}

	@Override
	public void setConnector(IShape shape) {
		physicsConnector = physicsWorld.getPhysicsConnectorManager()
				.findPhysicsConnectorByShape(shape);
	}

	@Override
	public void checkCollision(Contact contact) {
		collisionResolver.checkCollision(contact.getFixtureA().getBody()
				.getUserData(), contact.getFixtureB().getBody().getUserData());
	}
	
	@Override
	public boolean isStraightLine(Entity entity, Enemy enemy){
		return enemyUtil.straightLine(entity, enemy);
	}
}
