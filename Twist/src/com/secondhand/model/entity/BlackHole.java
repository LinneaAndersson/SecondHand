package com.secondhand.model.entity;

import com.secondhand.model.physics.Vector2;

public abstract class BlackHole extends CircleEntity {
	
	// only a 1/5 of the masses of the eaten bodies is used in the growth
	public static final float GROWTH_FACTOR = 0.2f;

	private boolean canEatInedibles;
	
	// radius property change event. 
	public static final String RADIUS = "radius";
	public static final String INCREASE_SIZE = "increaseSize"; 
	
	protected float increaseSize = 0;

	// I put this in BlackHole, so enemy black holes will also have scores
	// but placing it here made coding the eating logic much more convenient.
	// but we can simply ignore the score for enemy black holes.
	private int score;

	public BlackHole(final Vector2 position, final float radius, final int score) {
		super(position, radius, true);
		this.score = score;	

		setCanEatInedibles(false);
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

	protected void increaseScore(final int increase) {
		score += increase;
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
	
	protected void onGrow(){}

	protected void entityWasTooBigToEat(final Entity entity) {
	}

	private void eatEntityUtil(final Entity entity) {
		if (!this.canEat(entity)) {
			entityWasTooBigToEat(entity);
			return;
		}

		this.increaseScore(entity.getScoreValue());

		// by doing this we ensure that no sound effect is played when a powerups is picked up,
		// as they always have a radius of 0. Powerups already have their own sound effects
		// so we don't want to play the onGrow-sfx here. 
		if(entity.getRadius() != 0) {
			this.increaseSize =+ entity.getRadius() * GROWTH_FACTOR;
			pcs.firePropertyChange(BlackHole.INCREASE_SIZE, null, this);
			onGrow();
		}
		entity.wasEaten();
	}

	public void eatEntity(final Entity entity) {
		
		if (entity instanceof BlackHole) {
			
			
			final BlackHole otherBlackHole = (BlackHole) entity;
			// instead of eating, you will be eaten!
			if (otherBlackHole.canEat(this))
				otherBlackHole.eatEntity(this);
			else
				this.eatEntityUtil(otherBlackHole);
		} else {	
			// else, just eat the entity.
		
			eatEntityUtil(entity);
		}

	}

	@Override
	protected float getScoreWorth() {
		return 3;
	}

	public void setRadius(final float radius) {	
		
		// sanity checking. 
		if(radius < 0 ) 
			throw new AssertionError();
		
		this.pcs.firePropertyChange(RADIUS, this.getRadius(), radius);
		this.radius = radius;
		this.increaseSize = increaseSize - 0.2f;
	}
	
	public float getIncreaseSize(){
		return this.increaseSize;
	}
}
