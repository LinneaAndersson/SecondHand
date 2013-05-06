package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Enemy;

public class EnemyView extends BlackHoleView{

	public EnemyView(Enemy enemy) {
		super(enemy);

		getBody().setLinearDamping(1.2f);
		// TODO Auto-generated constructor stub
	}
	
	public void stopMovement(){
		this.getBody().setLinearVelocity(new Vector2());
		this.getBody().setAngularVelocity(0);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName().equalsIgnoreCase("stopMovement")){
			stopMovement();
		} else if (event.getPropertyName().equalsIgnoreCase("setConnector")){
			getEntity().getPhysics().setConnector(getShape());
		}
		
	}

}
