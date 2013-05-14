package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;
import java.util.List;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.physics.Vector2;

public class Player extends BlackHole {

	private String name;

	// starting lives for a new player.
	private static final int STARTING_LIVES = 1;

	private int lives;

	private int maxSize;

	private float scoreMultiplier;
	
	private float speedMultiplier;

	private Vector2 needsToMovePosition;

	//private final List<IPowerUp> powerUpList;

	private PlayerUtil util;
	
	//For playerView to get the color of player
	// The color will change!
	private int[] RGB = new int[3];

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
	public Player(final Vector2 position, final float radius, final int startingLives) {
		super(position, radius);
		
		this.speedMultiplier = 1;
		this.lives = startingLives;
		this.scoreMultiplier = 1;
		util = new PlayerUtil(this);
		//powerUpList = util.getPowerUpList();
		RGB[0]=0;
		RGB[1]=0;
		RGB[2]=0;

	}

	public Player(final Vector2 position, final float radius) {
		this(position, radius, STARTING_LIVES);
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
		
		
		pcs.firePropertyChange(INCREASE_SCORE, (int)0, (int)getScore());
	}

	public void setScoreMultiplier(final float scoreMultiplier) {
		this.scoreMultiplier = scoreMultiplier;
	}

	public float getScoreMultiplier() {
		return this.scoreMultiplier;
	}

	
	public void setSpeedMultiplier(final float speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	public float getSpeedMultiplier() {
		return this.speedMultiplier;
	}
	
	
	private void changeLives(final int change) {
		lives += change;
		
		pcs.firePropertyChange(INCREASE_LIFE, (int)0, (int)lives);
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

	/*public List<IPowerUp> getPowerUps() {
		return powerUpList;
	}*/

	public void removePowerUp(final IPowerUp powerUp) {
		//this.powerUpList.remove(powerUp);
		
		pcs.firePropertyChange(REMOVE_POWER_UP, null, powerUp);
	}

	public void addPowerUp(final IPowerUp powerUp) {
		//this.powerUpList.add(powerUp);
		
		pcs.firePropertyChange(ADD_POWER_UP, null, powerUp);
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addListener(final PropertyChangeListener observer) {
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
	
	@Override
	protected void handlePowerUp(final IPowerUp powerUp) {

		pcs.firePropertyChange("sound", null, POWER_UP_SOUND);

		addPowerUp(powerUp);

		//powerUp.eaten();
	}
	
	public int[] getRGB(){
		return RGB;
	}
	
	public void setRGB(int[] RGB){
		this.RGB = RGB;
	}

	@Override
	protected void onGrow() {
		pcs.firePropertyChange("sound", null, GROW_SOUND);
	}

	@Override
	protected void entityWasTooBigToEat(final Entity entity) {
		pcs.firePropertyChange("sound", null, BIGGER_ENTITY_COLLISION_SOUND);
	}

	@Override
	protected void wasEaten() {
		
		pcs.firePropertyChange("Sound", null, PLAYER_KILLED_SOUND);

		// We override the default behaviour for wasEaten. We don't want the
		// player to
		// be entirely removed from the world when eaten, we only want to
		// reposition the player and lose a life.

		kill();
	}
	
}
