package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.physics.FixtureDefs;
import com.secondhand.view.resource.TextureRegions;

public class PowerUpView extends RectangleView {
	
	public PowerUpView(final PhysicsWorld physicsWorld, final PowerUp powerUp) {
		super(physicsWorld, 
				powerUp,
				new Sprite(powerUp.getInitialPosition().x, powerUp.getInitialPosition().y, powerUp.getWidth(), 
						powerUp.getHeight(), TextureRegions.getInstance().getPowerUpTexture(powerUp.getPowerUpType()))
				, FixtureDefs.POWER_UP_FIXTURE_DEF);
	}
/*
	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		final String eventName = event.getPropertyName();
		if (eventName.equals(Player.ADD_POWER_UP)) {

			final Player player = gameWorld.getPlayer();
			final PowerUp powerUp = ((PowerUp) event.getNewValue());
			engine.registerUpdateHandler(createTimer(player, powerUp));
//	
			if (!powerUp.hasAnother(player)) {
				// TODO: Attach the powerups texture to player (ex: shield makes the player glow)
			//}
			
			// TODO: Implement floating text here
//			if (powerUp.hasText()) {
//				showFadingTextNotifier(powerUp.getText(),
//						new Vector2(player.getX(), player.getY()));
		}
	}*/
}
