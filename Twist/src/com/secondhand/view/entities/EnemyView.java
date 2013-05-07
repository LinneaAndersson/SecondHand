package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.model.Enemy;

public class EnemyView extends BlackHoleView{

	public EnemyView(final PhysicsWorld physicsWorld, final Enemy enemy) {
		super(physicsWorld, enemy);

		getBody().setLinearDamping(1.2f);
	}
	
	public void stopMovement(){
		this.getBody().setLinearVelocity(new Vector2());
		this.getBody().setAngularVelocity(0);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		if(event.getPropertyName().equalsIgnoreCase("stopMovement")){
			stopMovement();
		} else if (event.getPropertyName().equalsIgnoreCase("setConnector")){
			//getEntity().getPhysics().setConnector(getShape());
		}
		
	}

}
