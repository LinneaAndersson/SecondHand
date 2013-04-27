package com.secondhand.model;

import java.util.List;


import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Polygon;
import com.secondhand.opengl.TexturedPolygon;
import com.secondhand.resource.TextureRegions;

public class Obstacle extends PolygonEntity {

	public Obstacle(final Vector2 position,  final List<Vector2> polygon, final GameWorld level) {
		this(new TexturedPolygon(position.x, position.y,
				polygon,
				TextureRegions.getInstance().obstacleTexture), level);
	}
	
	public Obstacle(final Polygon polygon, final GameWorld level) {
		super(polygon, false, level, FixtureDefs.OBSTACLE_FIXTURE_DEF);
	}
	
	@Override
	public float getScoreWorth() {
		// obstacles are hard to eat, so you should get lots of points for eating them!
		return 2;
	}
}
