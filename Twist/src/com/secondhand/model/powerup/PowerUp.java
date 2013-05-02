package com.secondhand.model.powerup;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.BaseRectangle;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.FixtureDefs;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.model.RectangleEntity;
import com.secondhand.model.resource.PowerUpType;
import com.secondhand.view.resource.TextureRegions;

public abstract class PowerUp extends RectangleEntity {
		
	public final static int WIDTH = 64;
	public final static int HEIGHT = 64;
	
	protected float duration;
	
	public PowerUp(final BaseRectangle rectangle, final GameWorld level, final float duration) {
		super(rectangle, true, level, FixtureDefs.POWER_UP_FIXTURE_DEF);
		this.duration = duration;	
	}
	
	public PowerUp (final Vector2 position, final PowerUpType powerUpType, final GameWorld level, final float duration) {
		this(new Sprite(position.x, position.y, WIDTH, HEIGHT, TextureRegions.getInstance().getPowerUpTexture(powerUpType)), level, duration);
	}
	
	// this constructor is easier to test. 
	public PowerUp (final Vector2 position, final GameWorld level, final float duration) {
		this(new Rectangle(position.x, position.y, WIDTH, HEIGHT), level, duration);
	}	
	
	public float getDuration() {
		return duration;
	}
	
	public TimerHandler getTimer(final Player player) {
		return new TimerHandler(duration, new ITimerCallback() {
			private Player user = player; 
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				user.removePowerUp(PowerUp.this);
			}
		});
	}
	
	public abstract void activateEffect(final Player player);
	
	public void deactivateEffect(final Player player) {
		/* 	TODO: Reset player texture or whatever it is the powerup changes */
		if (player.getPowerUps().isEmpty())
			player.getCircle().setColor(1f, 1f, 1f);
	}
	
	@Override
	public float getScoreWorth() {
		throw new IllegalStateException("cannot score points for eating powerup");

	}
	
	public boolean hasText(){
		return getText() != null;
	}
	
	
	public String getText(){
		return null;
	}
	
	public boolean hasAnother(final Player player) {
		boolean hasAnother = false;
		for (final PowerUp powerUp : player.getPowerUps()) {
			if (powerUp.getClass() == this.getClass()) // old was DoubleScore.getClass() but this should be right?
				hasAnother = true;
		}
		return hasAnother;
		
	}
	
}
