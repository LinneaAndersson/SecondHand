package com.secondhand.model.entity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Random;

import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;

public class GameWorld implements IGameWorld, PropertyChangeListener {

	private final EntityManager entityManager;

	public static final int STARTING_LEVEL = 1;

	public static final int PLAYER_STARTING_SIZE = 30;

	private final IPhysicsWorld physicsWorld;

	private int levelWidth;
	private int levelHeight;

	private int levelNumber;

	private final PropertyChangeSupport support;

	private final PowerUpList powerUpList;

	public GameWorld(final IPhysicsWorld physics, final int levelNumber,
			final int playerLives, final int playerScore,
			final IPowerUpFactory factory) {

		this.physicsWorld = physics;
		this.physicsWorld.setCollisionResolver(new CollisionResolver(this));
		this.support = new PropertyChangeSupport(this);
		this.entityManager = new EntityManager();

		this.levelNumber = levelNumber;

		generateNewLevelEntities(playerLives, playerScore, factory);
		this.powerUpList = new PowerUpList(this.entityManager.getPlayer());

		getPlayer().addListener(this);
	}

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return this.support;
	}

	@Override
	public void addListener(final PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void removeListener(final PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public PowerUpList getPowerUpList() {
		return this.powerUpList;
	}

	// generate the level entities of a new level.
	private void generateNewLevelEntities(final int playerLives,
			final int playerScore, final IPowerUpFactory factory) {

		final RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(
				this, factory);

		// now create the new player
		final Player player = new Player(randomLevelGenerator.playerPosition,
				randomLevelGenerator.playerInitialSize, playerLives,
				playerScore, randomLevelGenerator.playerMaxSize);

		this.entityManager.setPlayer(player);

		this.levelWidth = randomLevelGenerator.levelWidth;
		this.levelHeight = randomLevelGenerator.levelHeight;

		this.entityManager.setEntityList(randomLevelGenerator.entityList);
		this.entityManager.setEnemyList(randomLevelGenerator.enemyList);

		physicsWorld.setWorldBounds(this.levelWidth, this.levelHeight);
	}

	@Override
	public int getLevelNumber() {
		return levelNumber;
	}

	@Override
	public IPhysicsWorld getPhysics() {
		return physicsWorld;
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

		// if we don't unregister and remove listeners, we will get memory
		// leaks,
		// so do test that they are indeed unregistered.
		this.entityManager.unregisterFromEntities();
		getPlayer().removeListener(this);

		support.firePropertyChange("NextLevel", false, true);
		// view will now destroy the gameworld and create a new one.
	}

	// update game world for this frame.
	@Override
	public void updateGameWorld() {

		if (checkPlayerBigEnough()) {
			nextLevel();
		} else
			this.entityManager.updateEntities();
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

	private boolean checkPlayerBigEnough() {
		return this.getPlayer().getRadius() >= this.getPlayer().getMaxSize();
	}

	@Override
	public void updateWithTouchInput(final Vector2 v) {
		this.getPlayer().reactToTouch(v);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		final String eventName = event.getPropertyName();

		final Random rng = new Random();
		if (eventName.equals(Player.RANDOMLY_REPOSITION_PLAYER)) {
			getPlayer().setNeedsToMovePosition(
					this.physicsWorld.getRandomUnOccupiedArea(
							this.getLevelWidth(), this.getLevelHeight(),
							getPlayer().getRadius(), rng));
		}
	}

	@Override
	public float getCompletion() {
		final float newRadius = getPlayer().getRadius()
				- GameWorld.PLAYER_STARTING_SIZE;
		final float playerMaxSize = (float) getPlayer().getMaxSize()
				- (float) GameWorld.PLAYER_STARTING_SIZE;

		return 1 - (playerMaxSize - (newRadius)) / (playerMaxSize);
	}

}
