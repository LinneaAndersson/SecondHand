package com.secondhand.model;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;

// I think that if we only want power-UP the
// class doesn't need to be abstract
public class PowerUp extends Entity {
	
	private Effect effect;
	
	public PowerUp (Vector2 position, float radius, Effect effect, TextureRegion texture) {
		// TODO: pass down the shape of the powerup(it will probably be a square, so probably a sprite)
		super(position, radius, new Sprite(position.x, position.y, 40, 40, texture), true);
		this.effect = effect;
		
	}
	
	public enum Effect{
		NONE, SPEED_UP, SCORE_UP, EAT_OBSTACLE, RANDOM_TELEPORT, SHIELD
	}
	
	public Effect getEffect(){
		return effect;
	}
}
