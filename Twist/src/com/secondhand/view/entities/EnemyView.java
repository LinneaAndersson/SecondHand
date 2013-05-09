package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.Enemy;
import com.secondhand.model.Vector2;
import com.secondhand.view.resource.TextureRegions;

public class EnemyView extends BlackHoleView{

	public EnemyView(final PhysicsWorld physicsWorld, final Enemy enemy) {
		super(physicsWorld, enemy,TextureRegions.getInstance().enemySprite
				);

		getBody().setLinearDamping(1.2f);
	}

	public void stopMovement(){
		//this.getBody().setLinearVelocity(new Vector2());
		this.getBody().setAngularVelocity(0);
	}

}
