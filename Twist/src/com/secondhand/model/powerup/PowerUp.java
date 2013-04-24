package com.secondhand.model.powerup;

import org.anddev.andengine.entity.primitive.BaseRectangle;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Player;
import com.secondhand.model.RectangleEntity;

// I think that if we only want power-UP the
// class doesn't need to be abstract
public abstract class PowerUp extends RectangleEntity {
		
	public final static float WIDTH = 64;
	public final static float HEIGHT = 64;
	
	private float duration;
	
	public PowerUp(final BaseRectangle rectangle, final PhysicsWorld physicsWorld, final float duration) {
		super(rectangle, true, physicsWorld, false);
		this.duration = duration;	
	}
	
	public PowerUp (final Vector2 position, final TextureRegion texture, final PhysicsWorld physicsWorld, final float duration) {
		this(new Sprite(position.x, position.y, WIDTH, HEIGHT, texture), physicsWorld, duration);
	}
	
	// this constructor is easier to test. 
	public PowerUp (final Vector2 position, final PhysicsWorld physicsWorld, final float duration) {
		this(new Rectangle(position.x, position.y, WIDTH, HEIGHT), physicsWorld, duration);
	}	
	
	public float getDuration() {
		return duration;
	}
	
	public abstract void activateEffect(Player player);
	
	public void deactivateEffect(Player player) {
		player.getCircle().setColor(1f, 1f, 1f);
	}
	
}
