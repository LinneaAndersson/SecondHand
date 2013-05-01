package com.secondhand.model;

import java.util.ArrayList;

import com.secondhand.model.powerup.PowerUp;

public class PlayerUtil {

	private Player player;

	public PlayerUtil(Player player) {
		this.player = player;
	}

	public ArrayList<PowerUp> createPowerUpList() {
		return new PowerList(player);
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

}
