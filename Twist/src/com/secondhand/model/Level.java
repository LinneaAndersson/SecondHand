package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.secondhand.twirl.MainActivity;

public class Level {

	// TODO: The width and height of the current level is probably not the width and height of the
	// the camera(that would make for a very small and boring level).
	// so we will also need to store that.
	
	private List<Entity> entityList;
	private int maxSize;
	private PhysicsWorld physicsWorld;

	private Player player;

	private Shape[] worldBounds;

	// many constructors necessary?
	// default maxsize?
	public Level() {
		this(100);
	}

	// TODO: we do even need this constructor at all?
	public Level(int maxSize) {
		this(maxSize, new PhysicsWorld(new Vector2(), true), new Player(
				new Vector2(50, 50), 20));
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
		
		
		worldBounds = new Shape[4];
		
		
        // put some invisible, static rectangles that keep the player within the world bounds:
        // we do not do this using registerEntity, because these bodies are static.
        
		// TODO: set these to the level width and height instead.
				final float width = MainActivity.CAMERA_WIDTH;
				final float height = MainActivity.CAMERA_HEIGHT;
		
		worldBounds[0] = new Rectangle(0, height - 2, width, 2);
		worldBounds[1]  = new Rectangle(0, 0, width, 2);
		worldBounds[2]  = new Rectangle(0, 0, 2, height);
		worldBounds[3]  = new Rectangle(width - 2, 0, 2, height);
        final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
        PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[0] , BodyType.StaticBody, wallFixtureDef);
        PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[1] , BodyType.StaticBody, wallFixtureDef);
        PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[2] , BodyType.StaticBody, wallFixtureDef);
        PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[3] , BodyType.StaticBody, wallFixtureDef);
        
        /*
        this.attachChild(ground);
        this.attachChild(roof);
        this.attachChild(left);
        this.attachChild(right);
		*/
	}

	public Player getPlayer() {
		return player;
	}
	
	public Shape[] getWorldBounds() {
		return this.worldBounds;
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


		// TODO: wait, why is this necessary?
		
		// movement is not good needs test more
		// it doesn't go in the right direction but seems to go where it wants to
		

			Vector2 movementVector = new Vector2(v.x - player.getPosition().x, v.y
					- player.getPosition().y);
			// scale the length of the vector some, so that it doesn't move too fast.
			// and reverse the vector so that it  goes in the correct direction. 
			
			// the closer the touch is to the plauer, the more force do we need to apply.
			movementVector.x = 1/ movementVector.x;
			movementVector.y = 1/ movementVector.y;

			// make it a bit faster
			movementVector = movementVector.mul(10);

			
			// also reverse the vector so that it goes in the correct direction.
			movementVector = movementVector.mul(-1);

			player.getBody().applyLinearImpulse(
					movementVector, player.getBody().getWorldCenter());
		

	}

	public boolean checkPlayerBigEnough() {
		return player.getRadius() >= maxSize;

	}

}
