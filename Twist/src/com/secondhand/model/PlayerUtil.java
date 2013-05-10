package com.secondhand.model;

import java.util.ArrayList;

import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUp;

// I decided to refactor some code from player because the that class was so large
public class PlayerUtil {

	private final Player player;
	private final PowerList list;
	private boolean isMirroredMovement;

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


}
