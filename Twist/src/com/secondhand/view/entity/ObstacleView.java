package com.secondhand.view.entity;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.entity.Obstacle;
import com.secondhand.view.opengl.TexturedPolygon;
import com.secondhand.view.physics.FixtureDefs;
import com.secondhand.view.resource.TextureRegions;

public class ObstacleView extends PolygonView {

	public ObstacleView(final PhysicsWorld physicsWorld, final Obstacle obstacle) {
		// create polygon body.

		super(physicsWorld, obstacle, new TexturedPolygon(
				obstacle.getInitialPosition().x,
				obstacle.getInitialPosition().y, obstacle.getPolygon(),
				TextureRegions.getInstance().obstacleTexture),
				FixtureDefs.OBSTACLE_FIXTURE_DEF);
	}

}
