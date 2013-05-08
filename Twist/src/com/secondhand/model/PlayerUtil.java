package com.secondhand.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.secondhand.model.powerup.PowerUp;

// I decided to refactor some code from player because the that class was so large
public class PlayerUtil {

	private final Player player;
	private final PowerList list;
	private boolean isMirroredMovement;
	private final PropertyChangeSupport sceneSupport = new PropertyChangeSupport(
			this);

	public PlayerUtil(final Player player) {
		this.player = player;
		list = new PowerList(player);
		this.isMirroredMovement = false;
	}

	public boolean isMirroredMovement() {
		return this.isMirroredMovement;
	}

	public void setMirroredMovement(final boolean mirrored) {
		this.isMirroredMovement = mirrored;
	}

	public PowerList getPowerUpList() {
		return list;
	}

	private class PowerList extends ArrayList<PowerUp> {

		private final Player player;
		private static final long serialVersionUID = 1L;

		public PowerList(final Player player) {
			super();
			this.player = player;
		}

		@Override
		public boolean add(final PowerUp object) {
			object.activateEffect(player);
			return super.add(object);
		}

		@Override
		public boolean remove(final Object object) {
			final boolean value = super.remove(object); // Priority: The list is
														// empty when you remove
														// last PowerUp
			((PowerUp) object).deactivateEffect(player);
			return value;
		}
	}

	public void reachToTouch(final Vector2 touch) {
		Vector2 forcePosition;

		// get rid of PIXEL_TO_METER_RATIO_DEFAULT to the view.
		if (this.isMirroredMovement()) {
			final Vector2 v1 = new Vector2(touch.x - player.getCenterX(),
					touch.y - player.getCenterY());
			v1.mul(-1);
			final Vector2 v2 = new Vector2(player.getCenterX(),
					player.getCenterY());
			v2.add(v1);
			forcePosition = new Vector2(v2.x, v2.y);
		} else {

			forcePosition = new Vector2(touch.x, touch.y);
		}

		final Vector2 force = new Vector2((player.getCenterX() - touch.x),
				player.getCenterY() - touch.y);

		if (this.isMirroredMovement) {
			force.mul(-1);
		}

		force.x = force.x / force.len();
		force.y = force.y / force.len();

		force.mul(40);
		
		player.physics.applyImpulse(force, forcePosition);
	
	}

	public void addListener(final PropertyChangeListener observer) {
		sceneSupport.addPropertyChangeListener(observer);
	}

	public void fireObject(final String name, final Object oldValue,
			final Object newValue) {
		sceneSupport.firePropertyChange(name, oldValue, newValue);
	}

	public void fireInt(final String name, final int oldValue, final int newValue) {
		sceneSupport.firePropertyChange(name, oldValue, newValue);
	}


}
