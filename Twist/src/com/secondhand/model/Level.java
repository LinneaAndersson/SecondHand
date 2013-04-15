package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Level {

	private List<Entity> entityList;
	private int maxSize;
	private PhysicsWorld physicsWorld;
	private PhysicsHandler playerPhysicsHandler;

	private Player player;

	// many constructors necessary?
	// default maxsize?
	public Level() {
		this(100);
	}

	public Level(int maxSize) {
		this(maxSize, new PhysicsWorld(new Vector2(), true), new Player(
				new Vector2(), 10));
	}

	public Level(int maxSize, PhysicsWorld pW, Player p) {
		this.maxSize = maxSize;
		this.physicsWorld = pW;
		player = p;
		registerEntities();
	}

	public void addEntity(Entity entity) {
		entityList.add(entity);
	}

	public void removeEntity(Entity entity) {
		entityList.remove(entity);
	}

	public void setEntityList(List<Entity> list) {
		entityList = list;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public PhysicsWorld getPhysicsWorld() {
		return physicsWorld;
	}

	public void registerEntities() {
		registerEntity(player);
		/*
		 * for (Entity e : entityList) { registerEntity(e); }
		 */
	}

	public Player getPlayer() {
		return player;
	}

	public void registerEntity(Entity entity) {

		PhysicsHandler pH = new PhysicsHandler(entity.getShape());

		entity.getShape().registerUpdateHandler(pH);

		entity.setBody(PhysicsFactory.createCircleBody(physicsWorld,
				entity.getShape(), BodyType.DynamicBody,
				PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f)));

		physicsWorld.registerPhysicsConnector(new PhysicsConnector(entity
				.getShape(), entity.getBody(), true, true));

	}

	// I wonder if all this is needed
	// Do we even use the vectors in entity?
	// to me it seems that box2d works that out for us
	// no you don't, read the comment below - Eric
	public void moveEntities(Vector2 v) {
		// pBody.applyLinearImpulse(new Vector2(100,100),new
		// Vector2(sh.getX(),sh.getY()));

		// By writing the values of the body position I can see that we have
		// movement, somewhere we need to set the camera to follow the player

		if (v.x + v.y != 0) {
			player.getBody().applyLinearImpulse(
					new Vector2(v.x - player.getPosition().x, v.y
							- player.getPosition().y),
					player.getBody().getWorldCenter());
		}

		// no, this is most definitely not necessary.
		// all you need to do is give Box2D an initial position and a body for
		// each of the
		// entities, and then Box2D will handle the rest.
		// you basically want to talk with Box2D as little as possible, because
		// it will handle
		// most things for you. Only when you want to perform a manual
		// intervention in the
		// physics world(like moving the player) do you need to talk with Box2D
		// so the one other thing we will need to do in this method is the
		// following:
		// move the enemy black holes in the direction that their AI:s has
		// determined.
		// (obviously using applyLinearImpulse)

		/*
		 * Iterator<Body> bit = pW.getBodies(); Body tmp; Entity e; while
		 * (bit.hasNext()) { tmp = bit.next(); e = (Entity) tmp.getUserData();
		 * e.setPosition(tmp.getPosition()); }
		 */
	}

	public boolean checkPlayerBigEnough() {
		return player.getRadius() >= maxSize;

	}

}
