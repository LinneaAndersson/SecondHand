package com.secondhand.model;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.debug.MyDebug;
import com.secondhand.scene.IGamePlaySceneView;

// This class was formerly known as level. 
public class GameWorld {

	private final EntityManager entityManager;
	
	// TODO: Maybe store this in player instead?
	private int playerMaxSize;
	
	private PhysicsWorld physicsWorld;

	
	private int levelWidth;
	private int levelHeight;

	private int levelNumber;
	
	private IGamePlaySceneView view;
	
	public void setView(final IGamePlaySceneView view) {
		this.view = view;
	}
	
	public IGamePlaySceneView getView() {
		return this.view;
	}
	
	public boolean hasView() {
		return view != null;
	}

	public GameWorld() {
		this(2);
	}
	
	public GameWorld(final int levelNumber) {
		this.levelNumber = levelNumber;
		
		
		this.physicsWorld  = new PhysicsWorld(new Vector2(), true);
		
		// you can try lowering the values of these if the game starts lagging too much.
		this.physicsWorld.setVelocityIterations(16);
		this.physicsWorld.setPositionIterations(16);

		
		final RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(this);
		
		this.playerMaxSize = randomLevelGenerator.playerMaxSize;
		this.levelWidth = randomLevelGenerator.levelWidth;
		this.levelHeight = randomLevelGenerator.levelHeight;
		
		this.entityManager = new EntityManager(randomLevelGenerator.player,  randomLevelGenerator.entityList,
				randomLevelGenerator.enemyList);
		
		setupWorldBounds();
	}	

	public int getLevelNumber() {
		return levelNumber;
	}


	public PhysicsWorld getPhysicsWorld() {
		return physicsWorld;
	}

	private final void setupWorldBounds() {

		final Shape[] worldBounds = new Shape[4];

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
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[0],
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[1],
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[2],
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[3],
				BodyType.StaticBody, wallFixtureDef);

	}

	

	public int getLevelWidth() {
		return levelWidth;
	}

	public int getLevelHeight() {
		return levelHeight;
	}

	
	
	// for debugging

	private boolean nextLevelAdvanced = false;

	public void nextLevel() {
			
		MyDebug.d("advancing to next level");
		clearLevel();
			/*
			final IGamePlaySceneView view = currentLevel.getView();
			
			// clear physics world expect for player.
			
			
			currentLevel = new Level(currentLevel.getLevelNumber()+1);
			MyDebug.d("now we tell the view to create the level");
			view.newLevelStarted();
			// and also register all new entities in the controller. */
	}

	
	public void onManagedUpdate(final float pSecondsElapsed) {
		if (checkPlayerBigEnough() && !nextLevelAdvanced) {
			nextLevelAdvanced = true;
			nextLevel();
		} else {

			this.entityManager.onManagedUpdate(pSecondsElapsed);
		}
	}

	public boolean isGameOver() {
		return this.entityManager.getPlayer().lostAllLives();
	}
	
	public void checkCollision(final Contact contact) {
		CollisionResolver.checkCollision(contact);	
	}
	
	public Player getPlayer() {
		return this.entityManager.getPlayer();
	}

	public void sendTouchInput(final Vector2 v) {
		this.getPlayer().reachToTouch(v);
	}

	public boolean checkPlayerBigEnough() {
		if(this.getPlayer().getRadius() < playerMaxSize)
			MyDebug.d("current played size: " + this.getPlayer().getRadius() + " goal: " + playerMaxSize);
		return this.getPlayer().getRadius() >= playerMaxSize;
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	// remove every entity(both from the physics world and andengine rendering)
	// from the world expect for the player.
	public void clearLevel() {
		this.entityManager.removeAllEntitiesExpectForPlayer();
	}

	public void updateWithTouchInput(final Vector2 v) {
		sendTouchInput(v);
	}
}
