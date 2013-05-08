package com.secondhand.view.entities;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.BlackHole;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.physics.FixtureDefs;

public class BlackHoleView extends CircleView {

	public BlackHoleView(final PhysicsWorld physicsWorld,
			final BlackHole blackHole) {
		super(physicsWorld, blackHole, new Circle(blackHole.getInitialPosition().x, blackHole.getInitialPosition().y, blackHole.getRadius()),
				FixtureDefs.BLACK_HOLE_FIXTURE_DEF);
	}
	
}
