package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.model.BlackHole;
import com.secondhand.model.Obstacle;
import com.secondhand.model.PolygonEntity;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.opengl.TexturedPolygon;
import com.secondhand.view.physics.FixtureDefs;
import com.secondhand.view.resource.TextureRegions;

public class ObstacleView extends PolygonEntity {

/*
	new TexturedPolygon(position.x, position.y,
			polygon,
			TextureRegions.getInstance().obstacleTexture)
	*/
	public ObstacleView(final PhysicsWorld physicsWorld, final Obstacle obstacle) {
		// create polygon body. 
		super(physicsWorld, obstacle, new TexturedPolygon, FixtureDefs.OBSTACLE_FIXTURE_DEF);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		
	}

}
