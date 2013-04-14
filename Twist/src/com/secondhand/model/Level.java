package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Level {

	private List<Entity> entityList;
	private int maxSize;
	private PhysicsWorld pW;
	private PhysicsHandler pH;

	// many constructors necessary?
	public Level() {
		this(100);
	}

	public Level(int maxSize) {
		this(maxSize, new PhysicsWorld(null, false));
	}

	public Level(int maxSize, PhysicsWorld pW) {
		this.maxSize = maxSize;
		this.pW = pW;
	}

	public void addEntity(Entity entity) {
		entityList.add(entity);
	}

	public void removeEntity(Entity entity) {
		entityList.remove(entity);
	}

	public void setEntetyList(List<Entity> list) {
		entityList = list;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public PhysicsWorld getPhysics() {
		return pW;
	}

	public void registerEntities() {
		// TODO somehow connect entities to physics
		// need factory?
		// or make every entity register itself?
	}

	public void registerEntity(Entity entity) {

		Sprite sprite = new Sprite(0, 0, 0, 0, null);

		pH = new PhysicsHandler(sprite);

		sprite.registerUpdateHandler(pH);

		Body body = PhysicsFactory.createCircleBody(pW, sprite,
				BodyType.DynamicBody,
				PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f));

		pW.registerPhysicsConnector(new PhysicsConnector(sprite, body));

	}

}
