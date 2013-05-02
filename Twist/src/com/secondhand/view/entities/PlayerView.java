package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.PointParticleEmitter;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Entity;
import com.secondhand.model.GameWorld;
import com.secondhand.model.Player;
import com.secondhand.view.loader.TextureRegionLoader;
import com.secondhand.view.resource.Sounds;
import com.secondhand.view.scene.GamePlayScene;

public class PlayerView implements IEntityView, PropertyChangeListener {

	private Engine engine;
	private GameWorld gameWorld;

	public PlayerView(Engine engine, GameWorld gameWorld) {
		this.engine = engine;
		this.gameWorld = gameWorld;

		this.gameWorld.getPlayer().addListener(this);
	}

	public void playerMoveAnimation(final Vector2 touch) {

		Player player = gameWorld.getPlayer();

		// TODO: Will use TextureLoader, this is just for testing
		final TextureRegion particleTexture = TextureRegionLoader.getInstance().loadTextureRegion("particle.png", 16, 16);

		final Vector2 surfacePosition = getRelativeSurfacePosition(player,
				touch);

		final PointParticleEmitter movementEmitter = new PointParticleEmitter(
				surfacePosition.x, surfacePosition.y);
		final ParticleSystem particleSystem = new ParticleSystem(
				movementEmitter, 60, 60, 10, particleTexture);

		// TODO: How to access GamePlayScene?
		// attachChild(particleSystem);
		
		final float duration = 2;
		engine.registerUpdateHandler(new TimerHandler(duration,
				new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				// GamePlayScene.this.detachChild(particleSystem); // Read TODO above
			}
		}));
	}

	// Calculate the surface position of object relative to given position
	public Vector2 getRelativeSurfacePosition(Entity object, Vector2 position) {
		Vector2 surfacePosition = new Vector2(object.getCenterX() - position.x,	
											  object.getCenterY() - position.y); // Vector from object position to given position
		surfacePosition.mul(object.getRadius() / surfacePosition.len()); // Length of new vector increased/decreased to length of radius
		return surfacePosition;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String propertyName = event.getPropertyName();
		if (propertyName.equals(Player.POWER_UP_SOUND)) {
			Sounds.getInstance().powerUpSound.play();
		}
	}

}
