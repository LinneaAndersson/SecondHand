package com.secondhand.view.entity;


import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.entity.Enemy;

public class EnemyView extends BlackHoleView{

	public EnemyView(final PhysicsWorld physicsWorld, final Enemy enemy) {
		super(physicsWorld, enemy);
		this.shape.setColor(1f, 0, 0);

		getBody().setLinearDamping(1.2f);
	}

}
