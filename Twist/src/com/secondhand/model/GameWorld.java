package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;

// This class was formerly known as level. 
public class GameWorld {

	private final EntityManager entityManager;

	// TODO: Maybe store this in player instead?
	private int playerMaxSize;

	private PhysicsWorld physicsWorld;

	private GameWorldBounds gameWorldBounds;

	private int levelWidth;
	private int levelHeight;

	private int levelNumber;

	private PropertyChangeSupport support;

	// I would strongly recommend using some kind of observer-pattern here
	// instead
	// private IGamePlaySceneView view;

	/*
	 * public void setView(final IGamePlaySceneView view) { this.view = view; }
	 * 
	 * public IGamePlaySceneView getView() { return this.view; }
	 * 
	 * public boolean hasView() { return view != null; }
	 */

	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
		getPlayer().addListener(listener);
	}

	public GameWorld() {
		this(2);
	}

	public GameWorld(final int levelNumber) {
		this.levelNumber = levelNumber;

		support = new PropertyChangeSupport(this);

		this.physicsWorld = new PhysicsWorld(new Vector2(), true);

		this.gameWorldBounds = new GameWorldBounds();

		this.entityManager = new EntityManager(new Player(new Vector2(50, 50),
				30, this, 20));

		// you can try lowering the values of these if the game starts lagging
		// too much. Basically, high values for these gives a higher quality
		// physics simulation.
		this.physicsWorld.setVelocityIterations(16);
		this.physicsWorld.setPositionIterations(16);

		createLevelEntities(this.levelNumber);
	}

	// create the level entities of a new level.
	private void createLevelEntities(final int levelNumber) {
		final RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(
				this.entityManager.getPlayer(), this);

		this.playerMaxSize = randomLevelGenerator.playerMaxSize;
		this.levelWidth = randomLevelGenerator.levelWidth;
		this.levelHeight = randomLevelGenerator.levelHeight;

		this.entityManager.setEntityList(randomLevelGenerator.entityList);
		this.entityManager.setEnemyList(randomLevelGenerator.enemyList);

		gameWorldBounds.setupWorldBounds(this.levelWidth, this.levelHeight,
				this.physicsWorld);
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public PhysicsWorld getPhysicsWorld() {
		return physicsWorld;
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

		++this.levelNumber;

		// destroy old level

		// destroy the entities expect for player
		clearLevel();
		this.gameWorldBounds.removeWorldBounds();

		// first load the new level entities:
		createLevelEntities(this.levelNumber);

		// then notify the view of this, so that it can place out the new
		// Entities in AndEngine for rendering.
		support.firePropertyChange("NextLevel", false, true);
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
		return this.getPlayer().getRadius() >= playerMaxSize;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void saveCurrentState() {
		this.entityManager.serialize();
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
