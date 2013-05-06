package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.secondhand.debug.MyDebug;


public class GameWorld {

	private final EntityManager entityManager;

	private static final int STARTING_LEVEL = 2;

	private final IPhysicsWorld mPhysic;

	private final int levelWidth;
	private final int levelHeight;

	private int levelNumber;

	private final PropertyChangeSupport support;
	
	private Vector2 cameraPosition;

	public GameWorld(final IPhysicsWorld physics) {
		mPhysic = physics;
		MyDebug.d("now we have created GameWorld");
		mPhysic.setGameWorld(this);
		
		MyDebug.d("now we have created GameWorld");
		support = new PropertyChangeSupport(this);
		MyDebug.d("now we have created GameWorl" + "d");
		this.levelWidth = 1700 * 2;
		MyDebug.d("now we have created GameWorld");
		this.levelHeight = 1700 * 2;
		MyDebug.d("now we have created GameWorld");
		this.entityManager = new EntityManager(new Player(new Vector2(50,50),
				30, this));
		MyDebug.d("now we have created GameWorld");
		mPhysic.setWorldBounds(levelWidth, levelHeight);
		MyDebug.d("now we have created GameWorld");
		generateNewLevelEntities(STARTING_LEVEL);
MyDebug.d("now we have created GameWorld");
	}
	
	public PropertyChangeSupport getPropertyChangeSupport() {
		return this.support;
	}

	public void addListener(final PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
		getPlayer().addListener(listener);
	}
	

	// generate the level entities of a new level.
	private void generateNewLevelEntities(final int levelNumber) {
		this.levelNumber = levelNumber;
		this.entityManager.getPlayer().setMaxSize(80);
		
		
		final RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(
				this.entityManager.getPlayer(), this);

		this.entityManager.setEntityList(randomLevelGenerator.entityList);
		this.entityManager.setEnemyList(randomLevelGenerator.enemyList);

	}
	
	public float getCameraX(){
		return cameraPosition.x;
	}
	
	public float getCameraY(){
		return cameraPosition.y;
	}
	
	public void setCameraPos(final float posX, final float posY){
		cameraPosition = new Vector2(posX, posY);
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public IPhysicsWorld getPhysics(){
		return mPhysic;
	}
	
	public int getLevelWidth() {
		return levelWidth;
	}

	public int getLevelHeight() {
		return levelHeight;
	}

	public void nextLevel() {

		++this.levelNumber;

		// destroy old level

		// destroy the entities expect for player
		clearLevel();
		
		this.mPhysic.removeWorldBounds();
		
		// first load the new level entities:
		generateNewLevelEntities(this.levelNumber);
		
		mPhysic.setWorldBounds(levelWidth, levelHeight);
		
		// then notify the view of this, so that it can place out the new
		// Entities in AndEngine for rendering.
		support.firePropertyChange("NextLevel", false, true);
	}

	// update game world for this frame.
	public void updateGameWorld() {
		if (checkPlayerBigEnough()) {
			nextLevel();
		} else {
			this.entityManager.updateEntities();
		}
	}

	public boolean isGameOver() {
		return this.entityManager.getPlayer().lostAllLives();
	}

	public Player getPlayer() {
		return this.entityManager.getPlayer();
	}

	public void sendTouchInput(final Vector2 v) {
		this.getPlayer().reachToTouch(v);
	}

	public boolean checkPlayerBigEnough() {
		return this.getPlayer().getRadius() >= this.getPlayer().getMaxSize();
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
