package com.secondhand.model;

import java.util.List;

import com.secondhand.view.opengl.Polygon;
import com.secondhand.view.opengl.TexturedPolygon;
import com.secondhand.view.resource.TextureRegions;

public class Obstacle extends PolygonEntity {

	public Obstacle(final Vector2 position,  final List<Vector2> polygon, final GameWorld level) {
		this(new TexturedPolygon(position.x, position.y,
				polygon,
				TextureRegions.getInstance().obstacleTexture), level);
	}
	
	public Obstacle(final Polygon polygon, final GameWorld level) {
		super(polygon, false, level);
	}
	
	@Override
	public float getScoreWorth() {
		// obstacles are hard to eat, so you should get lots of points for eating them!
		return 2;
	}
}
