package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.ourphysics.IPhysics;
import com.secondhand.model.ourphysics.Physics;
//TODO I think we should perhaps create an IGameWorld to support mvc
// This class was formerly known as level. 
public class GameWorld {

	private final EntityManager entityManager;

	private static final int STARTING_LEVEL = 2;

	//TODO: will remove this later
	private PhysicsWorld physicsWorld;
	
	private IPhysics mPhysic;

	private int levelWidth;
	private int levelHeight;

	private int levelNumber;

	private PropertyChangeSupport support;
	
	public PropertyChangeSupport getPropertyChangeSupport() {
		return this.support;
	}

	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
		getPlayer().addListener(listener);
	}

	public GameWorld() {

		init();
		this.levelWidth = 1700 * 2;
		this.levelHeight = 1700 * 2;
		this.entityManager = new EntityManager(new Player(new float[]{50,50},
				30, this));

		generateNewLevelEntities(STARTING_LEVEL);
		
		mPhysic.setWorldBounds(levelWidth, levelHeight);
	}

	private void init() {

		support = new PropertyChangeSupport(this);

		//TODO: will remove this later
		this.physicsWorld = new PhysicsWorld(new Vector2(), true);

		this.mPhysic = new Physics(this, new Vector2()); // TODO: have to do this other way. I fix

		mPhysic.setPhysicsWorld(getPhysicsWorld());
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

	public int getLevelNumber() {
		return levelNumber;
	}

	//TODO: will remove this later
	public PhysicsWorld getPhysicsWorld() {
		return physicsWorld;
	}

	public IPhysics getPhysics(){
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
