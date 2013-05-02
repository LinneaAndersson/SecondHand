package com.secondhand.model;

import java.util.ArrayList;

import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.powerup.PowerUp;

// I decided to refactor some code from player because the that class was so large
public class PlayerUtil {

	private Player player;
	private PowerList list;
	private boolean isMirroredMovement;

	public PlayerUtil(Player player) {
		this.player = player;
		list = new PowerList(player);
	}

	public boolean isMirroredMovement() {
		return this.isMirroredMovement;
	}

	public void setMirroredMovement(boolean mirrored) {
		this.isMirroredMovement = mirrored;
	}

	public ArrayList<PowerUp> getPowerUpList() {
		return list;
	}

	private class PowerList extends ArrayList<PowerUp> {

		private Player player;
		private static final long serialVersionUID = 1L;

		public PowerList(Player player) {
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

	public void reachToTouch(Vector2 touch) {
		Vector2 forcePosition;

		if (this.isMirroredMovement()) {
			final Vector2 v1 = new Vector2(touch.x - player.getCenterX(),
					touch.y - player.getCenterY());
			v1.mul(-1);
			final Vector2 v2 = new Vector2(player.getCenterX(),
					player.getCenterY());
			v2.add(v1);
			forcePosition = new Vector2(v2.x
					/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, v2.y
					/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		} else {

			forcePosition = new Vector2(touch.x
					/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, touch.y
					/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		}

		final Vector2 force = new Vector2((player.getCenterX() - touch.x),
				player.getCenterY() - touch.y);

		if (this.isMirroredMovement) {
			force.mul(-1);
		}

		force.x = force.x / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
		force.y = force.y / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

		force.x = force.x / force.len();
		force.y = force.y / force.len();

		force.mul(3);

		player.getBody().applyLinearImpulse(force, forcePosition);

		MyDebug.d("force: " + force.x + force.y);
	}

}
