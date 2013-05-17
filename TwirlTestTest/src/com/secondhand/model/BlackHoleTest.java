package com.secondhand.model;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

import com.secondhand.model.entity.BlackHole;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.physics.Vector2;

public class BlackHoleTest extends TestCase {
	
	
	public BlackHole getNewBlackHole(Vector2 position, final float radius, final int score) {
		return new BlackHole(position,radius, score) {

		};
	}
	
	public void testConstructor() {
		Vector2 position = new Vector2();
		
		BlackHole blackHole = getNewBlackHole(position, 10, 0);
		
		assertEquals(false, blackHole.canEatInedibles());
	}	
	
	private boolean radiusPropChangeWasCorrect;
	private boolean propChangeSent;

	private class PropChangeTest implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			String name = event.getPropertyName();
			if(name.equals(BlackHole.RADIUS)) {
				final float old = (Float)event.getOldValue();
				final float newV = (Float)event.getNewValue();
				
				if((int)old == 10 && (int)newV == 3) 
					radiusPropChangeWasCorrect = true;
			}
			propChangeSent = true;
		}
		
	}
	
	public void testSetRadius() {

		Vector2 position = new Vector2();
		
		BlackHole blackHole = getNewBlackHole(position, 10, 10);

		// Cannot have a negative radius
		try {
			blackHole.setRadius(-1);
			assertTrue(false);
		} catch (AssertionError er) {
			assertTrue(true);
		}
		
		PropertyChangeListener listener = new PropChangeTest();
		blackHole.addListener(listener);
		this.propChangeSent = false;
		radiusPropChangeWasCorrect = false;
		
		blackHole.setRadius(3);
		assertEquals(3, (int)blackHole.getRadius());
		assertTrue(radiusPropChangeWasCorrect);
		assertTrue(propChangeSent);
		
		// make sure we can also remove listeners.
		blackHole.removeListener(listener);
		this.propChangeSent = false;
		blackHole.setRadius(4);
		assertFalse(propChangeSent);
	}
}
