package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.secondhand.opengl.Circle;

public class Level {

	private List<Entity> entityList;
	private int maxSize;
	private PhysicsWorld pW;
	private PhysicsHandler playerHandler;
	private Entity player;

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
		player = new Player(new Vector2(), 10);
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
		for(Entity e : entityList){
			registerEntity(e);
		}
	}

	public void registerEntity(Entity entity) {

		IShape sh = new Circle(0, 0, entity.getRadius());
		
		PhysicsHandler pH = new PhysicsHandler(sh);

		
		sh.registerUpdateHandler(pH);
		
		
		Body body = PhysicsFactory.createCircleBody(pW, sh,
				BodyType.DynamicBody,
				PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f));

		pW.registerPhysicsConnector(new PhysicsConnector(sh, body, true, true));
		
	}
	
	public void registerPlayer(){
		
		IShape sh = new Circle(0, 0, player.getRadius());
		
		playerHandler = new PhysicsHandler(sh);

		
		sh.registerUpdateHandler(playerHandler);
		
		
		Body body = PhysicsFactory.createCircleBody(pW, sh,
				BodyType.DynamicBody,
				PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f));

		pW.registerPhysicsConnector(new PhysicsConnector(sh, body, true, true));
	}
	
	public void moveEntitys(Vector2 v){
		playerHandler.accelerate(v.x, v.y);
		
		
	}

	public boolean checkPlayerSize() {
		return player.getRadius() >= maxSize;
		
	}

}
