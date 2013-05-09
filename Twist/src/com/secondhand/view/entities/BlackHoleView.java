package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.secondhand.model.BlackHole;
import com.secondhand.view.physics.FixtureDefs;

public class BlackHoleView extends CircleView {
	BlackHole mBlackHole;
	public BlackHoleView(final PhysicsWorld physicsWorld,
			final BlackHole blackHole, final TextureRegion textureRegion) {
		super(physicsWorld, blackHole, new Sprite(
				blackHole.getInitialPosition().x, 
				blackHole.getInitialPosition().y, 
				blackHole.getRadius() * 2,
				blackHole.getRadius() * 2,
				textureRegion
				),
				FixtureDefs.BLACK_HOLE_FIXTURE_DEF);
	}
	protected void changeSize(int size) {
		final CircleShape shape = (CircleShape) getShape();
		shape.setRadius(size
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);


		//If it dont work this way.
		/*final CircleShape shape = (CircleShape) getBody().getFixtureList()
		.get(0).getShape();
		shape.setRadius(this.getRadius()
		/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);*/
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		/*if (event.getPropertyName().equalsIgnoreCase("radius")) {
			this.changeSize((Integer) event.getNewValue());
		}*/
	}

}
