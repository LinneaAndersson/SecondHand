package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.resource.Sounds;

public class Player extends BlackHole {

	private String name;
	
	private static final int STARTING_LIVES = 3;
	
	private int lives;
	
	private final PropertyChangeSupport sceneSupport = new PropertyChangeSupport(this);
	
	private Vector2 needsToMovePosition;
	
	private final List<PowerUp> powerUpList = new ArrayList<PowerUp>(1) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(final PowerUp object) {
			object.activateEffect(Player.this);
			return super.add(object);
		};
		
		@Override
		public boolean remove(final Object object) {
			final boolean value = super.remove(object); // Priority: The list is empty when you remove last PowerUp
			((PowerUp)object).deactivateEffect(Player.this);
			return value;
		};
	};

	public Player(final Vector2 position, final float radius, final GameWorld level, final float maxSpeed) {
		super(position, radius, level, false, maxSpeed);
		this.lives = STARTING_LIVES;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	
	public void increaseScore(final int increase) {
		final int oldScore = getScore();
		super.increaseScore(increase);
		// we also want to notify the view of this change:
		if(this.level.hasView())
			//this.level.getView().updateScore(this.getScore());
			sceneSupport.firePropertyChange("Score", oldScore, getScore());
	}
	
	private void changeLives(final int change) {
		final int oldLife = lives;
		lives += change;
	//	if(this.level.hasView())
			//this.level.getView().updateLives(this.getLives());
			sceneSupport.firePropertyChange("Life", oldLife, lives);
	}
	
	// the player loses a life 
	private void loseLife() {
		changeLives(-1);
	}
	
	// the player loses a life and is repositioned.
	public void kill() {
		this.loseLife();
		this.setNeedsToMovePosition(new Vector2(50,50));
	}
	
	public boolean lostAllLives() {
		return this.lives == 0;
	}
	
	public void gainLife() {
		changeLives(+1);
	}


	public List<PowerUp> getPowerUps(){
		return powerUpList;
	}
	
	public void removePowerUp(final PowerUp powerUp) {
		this.powerUpList.remove(powerUp);
	}
	
	public void addPowerUp(final PowerUp powerUp){
		this.powerUpList.add(powerUp);			
		sceneSupport.firePropertyChange("ADD",null,powerUp);
	}
	
	public void setName(final String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addListener(final PropertyChangeListener observer) {
		sceneSupport.addPropertyChangeListener(observer);
	}
	
	@Override
	public void setRadius(final float radius) {
		final float oldRadius = this.getRadius();
		super.setRadius(radius);
		final float newRadius = this.getRadius();
		sceneSupport.firePropertyChange("PlayerRadius", oldRadius, newRadius);
	}
	
	public void reachToTouch(final Vector2 touch) {
		MyDebug.d("TouchEvent "+ getRadius()*0.001f);
		Vector2 movementVector = new Vector2((getCenterX() - touch.x),
				getCenterY() - touch.y);

		// the closer the touch is to the player, the more force do we need to
		// apply.

		// make it a bit slower depending on how big it is.
		movementVector = movementVector.mul(this.getRadius() * 0.001f);

		move(movementVector);
	}

	
	// used to implement teleport, because you can't change the position inside a contact
	// listener for some retarded reason(that would be too simple, now wouldn't it!?) 
	// So fuck you Erin Catto.
	// - Sincerely, Eric
	public void setNeedsToMovePosition(final Vector2 position) {
		
		needsToMovePosition = new Vector2(position.x / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				position.y / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
	}
	
	public void moveToNeededPositionIfNecessary() {
		if(needsToMovePosition != null) {
			this.getBody().setTransform(needsToMovePosition, this.getBody().getAngle());
			needsToMovePosition = null;
		}
	}

	protected void handlePowerUp(final PowerUp powerUp) {

		Sounds.getInstance().powerUpSound.play();
		
		addPowerUp(powerUp);
		
		powerUp.wasEaten();
	}

	protected void onGrow() {
		Sounds.getInstance().growSound.play();	
	}
	
	protected void entityWasTooBigToEat(final Entity entity) {
		Sounds.getInstance().obstacleCollisionSound.play();
	}


	
	
	
	@Override
	protected void wasEaten() {
	
		Sounds.getInstance().playerKilledSound.play();
		// We override the default behaviour for wasEaten. We don't want the player to
		// be entirely removed from the world when eaten, we only want to reposition the player and lose a life.
	
		kill();		
	}

	
}
