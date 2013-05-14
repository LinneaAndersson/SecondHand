package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.SoundType;

public class Player extends BlackHole {

	private String name;

	// starting lives for a new player.
	private static final int STARTING_LIVES = 1;

	private int lives;

	private int maxSize;

	private float scoreMultiplier;
	
	private float speedMultiplier;

	private Vector2 needsToMovePosition;

	private PlayerUtil util;
	
	private SoundType soundType = null;
	
	//For playerView to get the color of player
	// The color will change!
	private float[] RGB = new float[3];

	// ============== EVENT-CONSTANTS ==============
	public final static String INCREASE_SCORE = "IncreaseScore";
	public final static String INCREASE_LIFE = "IncreaseLife";
	public final static String ADD_POWER_UP = "AddPowerUp";

	// =============================================
	public Player(final Vector2 position, final float radius, final int startingLives) {
		super(position, radius);
		
		this.speedMultiplier = 1;
		this.lives = startingLives;
		this.scoreMultiplier = 1;
		util = new PlayerUtil(this);
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
	
	public float[] getRGB(){
		return RGB;
	}
	
	public void setRGB(float[] RGB){
		this.RGB = RGB;
		pcs.firePropertyChange("color", null, RGB);
	}

	@Override
	protected void onGrow() {
		setSoundType(SoundType.GROW_SOUND);
	}

	@Override
	protected void entityWasTooBigToEat(final Entity entity) {
		setSoundType(SoundType.OBSTACLE_COLLISION_SOUND);
	}

	@Override
	protected void wasEaten() {
		setSoundType(SoundType.PLAYER_KILLED_SOUND);

		// We override the default behaviour for wasEaten. We don't want the
		// player to
		// be entirely removed from the world when eaten, we only want to
		// reposition the player and lose a life.

		kill();
	}
	
	public void setSoundType(final SoundType sound){
		soundType = sound;
		pcs.firePropertyChange("Sound", null, sound);
	}
	
	public SoundType getSoundType(){
		return soundType;
	}
	
}
