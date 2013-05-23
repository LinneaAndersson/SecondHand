package com.secondhand.model.powerup;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PowerUpType;
import com.secondhand.model.resource.SoundType;

import junit.framework.TestCase;

public class PowerUpTest extends TestCase {
	
	private boolean powerUpActivated;
	
	public PowerUp getNewPowerUp(Vector2 position, PowerUpType powerUpType, float duration) {
		return new PowerUp(position, powerUpType, duration) {
			@Override
			public void activateEffect(Player player) {powerUpActivated = true;}
			
			@Override
			public String getText(){
				return "lorem ipsum";
			}
			
			public float getR() {return 1f;}
			public float getG() {return 0f;}
			public float getB() {return 1f;}

		};
	}
	
	public void testConstructor() {
		Vector2 position = new Vector2(1,2);
		PowerUpType powerUpType = PowerUpType.EXTRA_LIFE;
		float duration = 5;
		
		PowerUp powerUp = getNewPowerUp(position, powerUpType, duration);
		
		assertEquals(powerUpType, powerUp.getPowerUpType());
		assertEquals(duration, powerUp.getDuration());
		
		assertEquals(PowerUp.WIDTH, powerUp.getWidth(), 0.001f);
		assertEquals(PowerUp.WIDTH, powerUp.getHeight(), 0.001f);
		assertEquals(0, powerUp.getRadius(), 0.001f);
		assertEquals(0, powerUp.getScoreValue());
		assertTrue(powerUp.hasText());
		assertEquals("lorem ipsum", powerUp.getText());

	}
	
	public void testResetPlayerColor() {
		final float[] RGB = new float[3];	

		Player player = new Player(new Vector2(),3.0f, 3, 1, 0);

		
		Vector2 position = new Vector2(1,2);
		PowerUpType powerUpType = PowerUpType.EXTRA_LIFE;
		float duration = 5;
		
		RGB[0] = 0.1f;
		RGB[1] = 0.2f;
		RGB[2] = 0.3f;
		player.setRGB(RGB);
	
		PowerUp powerUp = getNewPowerUp(position, powerUpType, duration);
		powerUp.resetPlayerColor(player);	
		
		assertEquals(Player.DEFAULT_COLOR_VALUE, player.getRGB()[0], 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, player.getRGB()[1], 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, player.getRGB()[2], 0.001);
	}
	
	public void testPowerUpDefaultColor() {
		PowerUp powerUp = 
				new PowerUp(new Vector2(), PowerUpType.DOUBLE_SCORE, 10) {
			@Override
			public void activateEffect(Player player) {}
		};



		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getR(), 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getG(), 0.001);
		assertEquals(Player.DEFAULT_COLOR_VALUE, powerUp.getB(), 0.001);
	}
	
	
	public void testActivatePowerUp() {
		Vector2 position = new Vector2(1,2);
		PowerUpType powerUpType = PowerUpType.EXTRA_LIFE;
		float duration = 5;
		Player player = new Player(new Vector2(),3.0f, 3, 1, 0);
		
		PowerUp powerUp = getNewPowerUp(position, powerUpType, duration);

		this.powerUpActivated = false;

		class Listener implements PropertyChangeListener {

			public boolean soundPlayed = false;

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				String name = event.getPropertyName();
				
				if(name.equals(Player.SOUND)) {
					final SoundType newV = (SoundType)event.getNewValue();
					
					if(newV == SoundType.POWERUP_SOUND)
						soundPlayed = true;
				}
			}
		}

		Listener listener = new Listener();
		player.addListener(listener);

		powerUp.activatePowerUp(player, false);


		assertTrue(this.powerUpActivated);
		assertTrue(listener.soundPlayed);

		float rgb[]  =player.getRGB();
		assertEquals(1, rgb[0], 0.001);
		assertEquals(0, rgb[1], 0.001);
		assertEquals(1, rgb[2], 0.001);

	}
	
	public void testGetText(){
		PowerUp powerUp = 
				new PowerUp(new Vector2(), PowerUpType.DOUBLE_SCORE, 10) {
			@Override
			public void activateEffect(Player player) {}
		};
		
		assertFalse(powerUp.hasText());
		assertEquals(null, powerUp.getText());
		
	}

}

