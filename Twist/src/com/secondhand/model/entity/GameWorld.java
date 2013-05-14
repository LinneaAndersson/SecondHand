package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.randomlevelgenerator.RandomLevelGenerator;

public class GameWorld implements IGameWorld {

	private final EntityManager entityManager;

	private static final int STARTING_LEVEL = 1;

	public static final int PLAYER_STARTING_SIZE = 30;

	private final IPhysicsWorld mPhysic;

	private int levelWidth;
	private int levelHeight;

	private int levelNumber;

	private final PropertyChangeSupport support;

	public GameWorld(final IPhysicsWorld physics) {
		mPhysic = physics;
		mPhysic.setGameWorld(this);
		support = new PropertyChangeSupport(this);

		this.entityManager = new EntityManager(new Player(new Vector2(50, 50),
				PLAYER_STARTING_SIZE));

		generateNewLevelEntities(STARTING_LEVEL);
		mPhysic.setWorldBounds(levelWidth, levelHeight);
	}

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return this.support;
	}

	@Override
	public void addListener(final PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
		getPlayer().addListener(listener);
	}

	// generate the level entities of a new level.
	private void generateNewLevelEntities(final int levelNumber) {
		this.levelNumber = levelNumber;

		final RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(
				this.entityManager.getPlayer(), this);

		this.entityManager.getPlayer().setMaxSize(
				randomLevelGenerator.playerMaxSize);
		this.levelWidth = randomLevelGenerator.levelWidth;
		this.levelHeight = randomLevelGenerator.levelHeight;

		this.entityManager.setEntityList(randomLevelGenerator.entityList);
		this.entityManager.setEnemyList(randomLevelGenerator.enemyList);
	}

	@Override
	public int getLevelNumber() {
		return levelNumber;
	}

	@Override
	public IPhysicsWorld getPhysics() {
		return mPhysic;
	}

	@Override
	public int getLevelWidth() {
		return levelWidth;
	}

	@Override
	public int getLevelHeight() {
		return levelHeight;
	}

	private void nextLevel() {

		++this.levelNumber;

		// destroy the entities expect for player
		clearLevel();

		this.mPhysic.removeWorldBounds();

		// first load the new level entities:
		generateNewLevelEntities(this.levelNumber);

		mPhysic.setWorldBounds(levelWidth, levelHeight);

		this.getPlayer().setRadius(PLAYER_STARTING_SIZE);

		// then notify the view of this, so that it can place out the new
		// Entities in AndEngine for rendering.
		support.firePropertyChange("NextLevel", false, true);
	}

	// update game world for this frame.
	@Override
	public void updateGameWorld() {

		if (checkPlayerBigEnough()) {
			nextLevel();
		} else {
			this.entityManager.updateEntities();
		}
	}

	@Override
	public boolean isGameOver() {
		return this.entityManager.getPlayer().lostAllLives();
	}

	@Override
	public Player getPlayer() {
		return this.entityManager.getPlayer();
	}

	@Override
	public List<Entity> getEntityList() {
		return this.entityManager.getEntityList();
	}

	@Override
	public boolean checkPlayerBigEnough() {
		return this.getPlayer().getRadius() >= this.getPlayer().getMaxSize();
	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	// remove every entity(both from the physics world and andengine rendering)
	// from the world expect for the player.
	private void clearLevel() {
		this.entityManager.removeAllEntitiesExpectForPlayer();
	}

	@Override
	public void updateWithTouchInput(final Vector2 v) {
		this.getPlayer().reachToTouch(v);
	}

}
