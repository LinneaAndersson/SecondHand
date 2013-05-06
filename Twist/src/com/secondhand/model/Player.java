package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.util.List;

import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

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
	public Player(final float[] position, final float radius,
			final GameWorld gameWorld, final int startingLives,
			final int startingScore) {
		super(position, radius, gameWorld, PLAYER_MAX_SPEED, startingScore);
		MyDebug.d("Nw we create Player");
		this.lives = startingLives;
		MyDebug.d("Nw we create Player");
		this.scoreMultiplier = 1;
		MyDebug.d("Nw we create Player");
		util = new PlayerUtil(this);
		MyDebug.d("Nw we create Player");
		powerUpList = util.getPowerUpList();
		MyDebug.d("Nw we create Player");

	}

	public Player(final float[] position, final float radius,
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
	}

	@Override
	public void setRadius(final float radius) {
		super.setRadius(radius);
		util.setRadius(radius);
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

		needsToMovePosition = new Vector2(position.x
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, position.y
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
	}

	public void moveToNeededPositionIfNecessary() {
		if (needsToMovePosition != null) {
			// TODO move to physics
			physics.getBody().setTransform(new com.badlogic.gdx.math.Vector2(needsToMovePosition.x, needsToMovePosition.y),
					physics.getBody().getAngle());
			needsToMovePosition = null;
		}
	}

	protected void handlePowerUp(final PowerUp powerUp) {

		util.fireObject(POWER_UP_SOUND, null, null);

		addPowerUp(powerUp);

		powerUp.wasEaten();
	}

	@Override
	protected void onGrow() {
		util.fireObject(GROW_SOUND, null, null);
	}

	@Override
	protected void entityWasTooBigToEat(final Entity entity) {
		util.fireObject(BIGGER_ENTITY_COLLISION_SOUND, null, null);
	}

	@Override
	protected void wasEaten() {
		
		util.fireObject(PLAYER_KILLED_SOUND, null, null);

		// We override the default behaviour for wasEaten. We don't want the
		// player to
		// be entirely removed from the world when eaten, we only want to
		// reposition the player and lose a life.

		kill();
	}
}
