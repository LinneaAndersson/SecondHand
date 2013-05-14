package com.secondhand.model.powerup;

import java.util.ArrayList;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.entity.IPowerUp;
import com.secondhand.model.entity.Player;

public class PowerUpList extends ArrayList<IPowerUp> {

	
	// NOT FINISHED!
	
	private Player player;
	private static final long serialVersionUID = 1L;
	private static PowerUpList instance;

	public PowerUpList() {
		super();
	}

	@Override
	public boolean remove(final Object object) {
		final boolean value = super.remove(object); // Priority: The list is
		// empty when you remove
		// last PowerUp
		((IPowerUp) object).deactivateEffect();
		return value;
	}

	public static PowerUpList getInstance(){
		if(instance==null){
			instance = new PowerUpList();
		}
		return instance;
	}
}
