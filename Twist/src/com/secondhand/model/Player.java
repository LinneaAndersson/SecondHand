package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.util.List;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.powerup.PowerUp;

public class Player extends BlackHole {

	private String name;

	// starting lives for a new player.
	private static final int STARTING_LIVES = 1;

	private static final int PLAYER_MAX_SPEED = 20;

	private int lives;

	private int maxSize;

	private float scoreMultiplier;

	private Vector2 needsToMovePosition;

	private final List<PowerUp> powerUpList;

	private PlayerUtil util;

	// ============== EVENT-CONSTANTS ==============
	public final static String INCREASE_SCORE = "IncreaseScore";
	public final static String INCREASE_LIFE = "IncreaseLife";
	public final static String ADD_POWER_UP = "AddPowerUp";
	public final static String REMOVE_POWER_UP = "RemovePowerUp";
	public final static String POWER_UP_SOUND = "PowerUpSound";
	public final static String GROW_SOUND = "GrowSound";
	public final static String BIGGER_ENTITY_COLLISION_SOUND = "PlayerBiggerEntityCollision";
	public final static String PLAYER_KILLED_SOUND = "PlayerKilled";


	// =============================================
	public Player(final Vector2 position, final float radius,
			final GameWorld gameWorld, final int startingLives,
			final int startingScore) {
		super(position, radius, gameWorld, PLAYER_MAX_SPEED, startingScore);
		
		this.lives = startingLives;
		this.scoreMultiplier = 1;
		util = new PlayerUtil(this);
		powerUpList = util.getPowerUpList();

	}

	public Player(final Vector2 position, final float radius,
			final GameWorld gameWorld) {
		this(position, radius, gameWorld, STARTING_LIVES, 0);
	}

	public boolean isMirroredMovement() {
		return util.isMirroredMovement();
	}

	public void setMirroredMovement(final boolean mirrored) {
		util.setMirroredMovement(mirrored);
	}

	public int getLives() {
		return this.lives;
	}

	public void setMaxSize(final int size) {
		maxSize = size;
	}

	public int getMaxSize() {
		return maxSize;
	}

	@Override
	public void increaseScore(final int increase) {
		super.increaseScore((int) this.getScoreMultiplier() * increase);
		util.fireInt(INCREASE_SCORE, 0, getScore());
	}

	public void setScoreMultiplier(final float scoreMultiplier) {
		this.scoreMultiplier = scoreMultiplier;
	}

	public float getScoreMultiplier() {
		return this.scoreMultiplier;
	}

	private void changeLives(final int change) {
		lives += change;
		util.fireInt(INCREASE_LIFE, 0, lives);
	}

	// the player loses a life
	private void loseLife() {
		changeLives(-1);
	}

	// the player loses a life and is repositioned.
	public void kill() {
		this.loseLife();
		this.setNeedsToMovePosition(new Vector2(50, 50));
	}

	public boolean lostAllLives() {
		return this.lives == 0;
	}

	public void gainLife() {
		changeLives(+1);
	}

	public List<PowerUp> getPowerUps() {
		return powerUpList;
	}

	public void removePowerUp(final PowerUp powerUp) {
		MyDebug.d("now we deattach the powerup");
		this.powerUpList.remove(powerUp);
		util.fireObject(REMOVE_POWER_UP, null, powerUp);
	}

	public void addPowerUp(final PowerUp powerUp) {
		this.powerUpList.add(powerUp);
		util.fireObject(ADD_POWER_UP, null, powerUp);
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addListener(final PropertyChangeListener observer) {
		util.addListener(observer);
		this.pcs.addPropertyChangeListener(observer);
	}
	
	public void reachToTouch(final Vector2 touch) {
		util.reachToTouch(touch);
	}

	// used to implement teleport, because you can't change the position inside
	// a contact
	// listener for some retarded reason(that would be too simple, now wouldn't
	// it!?)
	// So fuck you Erin Catto.
	// - Sincerely, Eric
	public void setNeedsToMovePosition(final Vector2 position) {

		needsToMovePosition = new Vector2(position.x, position.y);
	}

	public void moveToNeededPositionIfNecessary() {
		if (needsToMovePosition != null) {	
			this.physics.setTransform(needsToMovePosition);
			needsToMovePosition = null;
		}
	}

	protected void handlePowerUp(final PowerUp powerUp) {

		pcs.firePropertyChange(POWER_UP_SOUND, null, null);

		addPowerUp(powerUp);

		powerUp.wasEaten();
	}

	@Override
	protected void onGrow() {
		pcs.firePropertyChange(GROW_SOUND, null, null);
	}

	@Override
	protected void entityWasTooBigToEat(final Entity entity) {
		pcs.firePropertyChange(BIGGER_ENTITY_COLLISION_SOUND, null, null);
	}

	@Override
	protected void wasEaten() {
		
		pcs.firePropertyChange(PLAYER_KILLED_SOUND, null, null);

		// We override the default behaviour for wasEaten. We don't want the
		// player to
		// be entirely removed from the world when eaten, we only want to
		// reposition the player and lose a life.

		kill();
	}
}
