	
package com.secondhand.model;


import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.opengl.Circle;

public abstract class BlackHole extends CircleEntity {

	// only a 1/5 of the masses of the eaten bodies is used in the growth
	private static final float GROWTH_FACTOR = 0.2f;
	
	private float maxSpeed;
	
	private boolean canEatInedibles;
	
	private float[] position;
		
	//I put this in BlackHole, so enemy black holes will also have scores
	// but placing it here made coding the eating logic much more convenient.
	// but we can simply ignore the score for enemy black holes.
	private  int score;

	public BlackHole(final float[] position, final float radius,
			final GameWorld level,
			final float maxSpeed, final int startingScore) {
		// TODO load texture instead of creating Circle
		super(new Circle(position[0], position[1], radius), true, level);
		this.position = position;
		this.maxSpeed = maxSpeed;
		this.score = startingScore;
		this.canEatInedibles = false;
	}
	
	public BlackHole(final float[] position, final float radius,
			final GameWorld level,
			final float maxSpeed) {
		this(position, radius, level, maxSpeed, 0);
	}
	
	public float getPosX(){
		return position[0];
	}

	public float getPosY(){
	return position[1];
	}
	
	public boolean canEatInedibles() {
		return this.canEatInedibles;
	}
	
	public void setCanEatInedibles(final boolean canEatInedibles) {
		this.canEatInedibles = canEatInedibles;
	}
	
	public int getScore() {
		return score;
	}

	public void increaseScore(final int increase) {
		score += increase;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(final float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	private void increaseSize(final float increase) {
		pcs.firePropertyChange("radius", getRadius(), getRadius() + increase);
		setRadius(getRadius() + increase);
	}

	/**
	 * If sizes are equal then false is returned.
	 */
	public boolean canEat(final Entity entity) {
		if (!entity.isEdible() && !this.canEatInedibles()) {
			return false;
		}
		return this.getRadius() > entity.getRadius();
	}
	
	// moves in the specified direction. If max speed is reached, then no movement is performed.
	public void move(final Vector2 position) {
		physics.applyImpulse(position.x, position.y, maxSpeed);
	}

	protected abstract void handlePowerUp(final PowerUp powerUp);
	
	protected void onGrow() {
	}
	
	protected void entityWasTooBigToEat(final Entity entity) {}
	
	
	public void eatEntityUtil(final Entity entity) {
		// for now we won't handle black holes eating other black holes.	
		/*if(entity instanceof BlackHole)
			return;*/
		
		
		if(!this.canEat(entity)) {
			entityWasTooBigToEat(entity);
	 		return;	
		}
			
		this.increaseScore(entity.getScoreValue());

		// increase the size of the rendered circle.
		final float radiusInc = entity.getRadius() * GROWTH_FACTOR;
		this.increaseSize(radiusInc);
		onGrow();

		// now we must also increase the size of the circle physics body
		final CircleShape shape = (CircleShape) physics.getBody().getFixtureList()
				.get(0).getShape();
		shape.setRadius(this.getRadius()
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);

		entity.wasEaten();	
	}
	
	public void eatEntity(final Entity entity) {
			
		if(entity instanceof PowerUp) {
			// custom handling
			handlePowerUp((PowerUp)entity);
		} else if(entity instanceof BlackHole) {
				final BlackHole otherBlackHole = (BlackHole)entity;
				
				// instead of eating, you will be eaten!
				if(otherBlackHole.canEat(this))
					otherBlackHole.eatEntity(this);	
				else
					this.eatEntityUtil(otherBlackHole);
		} else {
			
		/*	if(entity instanceof Obstacle) {
				return;
			}*/
			
			eatEntityUtil(entity);
			
			
		}


	}

	@Override
	public float getScoreWorth() {
		return 3;
	}	
	
	
}
