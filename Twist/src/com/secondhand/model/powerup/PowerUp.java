package com.secondhand.model.powerup;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.BaseRectangle;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.FixtureDefs;
import com.secondhand.model.Player;
import com.secondhand.model.RectangleEntity;
import com.secondhand.resource.PowerUpType;
import com.secondhand.resource.TextureRegions;

// I think that if we only want power-UP the
// class doesn't need to be abstract
public abstract class PowerUp extends RectangleEntity {
		
	public final static float WIDTH = 64;
	public final static float HEIGHT = 64;
	
	private float duration;
	
	public PowerUp(final BaseRectangle rectangle, final PhysicsWorld physicsWorld, final float duration) {
		super(rectangle, true, physicsWorld, false, FixtureDefs.POWER_UP_FIXTURE_DEF);
		this.duration = duration;	
	}
	
	public PowerUp (final Vector2 position, final PowerUpType powerUpType, final PhysicsWorld physicsWorld, final float duration) {
		this(new Sprite(position.x, position.y, WIDTH, HEIGHT, TextureRegions.getInstance().getPowerUpTexture(powerUpType)), physicsWorld, duration);
	}
	
	// this constructor is easier to test. 
	public PowerUp (final Vector2 position, final PhysicsWorld physicsWorld, final float duration) {
		this(new Rectangle(position.x, position.y, WIDTH, HEIGHT), physicsWorld, duration);
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
	
}
