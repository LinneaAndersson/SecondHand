package com.secondhand.view.entities;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.Enemy;
import com.secondhand.view.resource.TextureRegions;

public class EnemyView extends BlackHoleView{

	public EnemyView(final PhysicsWorld physicsWorld, final Enemy enemy) {
		super(physicsWorld, enemy,TextureRegions.getInstance().enemySprite
				);

		getBody().setLinearDamping(1.2f);
	}

}
