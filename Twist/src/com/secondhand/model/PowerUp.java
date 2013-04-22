package com.secondhand.model;

import org.anddev.andengine.entity.primitive.BaseRectangle;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;

// I think that if we only want power-UP the
// class doesn't need to be abstract
public class PowerUp extends RectangleEntity {
	
	private Effect effect;
	
	public final static float WIDTH = 64;
	public final static float HEIGHT = 64;
	
	public PowerUp(final Effect effect, final BaseRectangle rectangle, final PhysicsWorld physicsWorld) {
		super(rectangle, true, physicsWorld, false);
		this.effect = effect;
	}
	
	public PowerUp (final Vector2 position, final Effect effect, final TextureRegion texture, final PhysicsWorld physicsWorld) {
		this(effect, new Sprite(position.x, position.y, WIDTH, HEIGHT, texture), physicsWorld);
	}
	
	// this constructor is easier to test. 
	public PowerUp (final Vector2 position, final Effect effect, final PhysicsWorld physicsWorld) {
		this(effect, new Rectangle(position.x, position.y, WIDTH, HEIGHT), physicsWorld);
	}	
	
	public enum Effect{
		NONE, SPEED_UP, SCORE_UP, EAT_OBSTACLE, RANDOM_TELEPORT, SHIELD
	}
	
	public Effect getEffect(){
		return effect;
	}
}
