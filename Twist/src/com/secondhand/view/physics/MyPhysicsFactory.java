package com.secondhand.view.physics;

import static org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.physics.Vector2;
import com.secondhand.view.opengl.Polygon;

public final class MyPhysicsFactory {
	
	private MyPhysicsFactory() {} 

	public static Body createPolygonBody(final PhysicsWorld physicsWorld,
			final Polygon polygon, final BodyType bodyType,
			final FixtureDef fixtureDef) {
		MyDebug.d("creating polygon body");
		com.badlogic.gdx.math.Vector2 vertices[] = new com.badlogic.gdx.math.Vector2[polygon
				.getPolygon().size()];
		int i = 0;
		for (final Vector2 vector : polygon.getPolygon()) {
			vertices[i++] = new com.badlogic.gdx.math.Vector2(vector.x
					/ PIXEL_TO_METER_RATIO_DEFAULT, vector.y
					/ PIXEL_TO_METER_RATIO_DEFAULT);
		}

		
			Body b = PhysicsFactory.createPolygonBody(physicsWorld, polygon,
					vertices, bodyType, fixtureDef);
		
		MyDebug.d("done creating polygon body");
		
		return b;
	}	
}
