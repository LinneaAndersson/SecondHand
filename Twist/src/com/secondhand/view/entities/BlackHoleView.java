package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.BlackHole;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.physics.FixtureDefs;

public class BlackHoleView extends CircleView implements PropertyChangeListener{

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
	protected void changeSize(float size) {
		final CircleShape shape = (CircleShape) this.body.getFixtureList().get(0)
				.getShape();
		shape.setRadius(size / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		if(this instanceof BlackHoleView){
		final RectangularShape circle = (RectangularShape) this.shape;
		circle.setSize(size*2, size*2);
		} else {
			final Circle circle = (Circle) this.shape;
			circle.setRadius(size);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
		if (event.getPropertyName().equalsIgnoreCase("radius")) {
			float newValue = (Float) event.getNewValue();
			this.changeSize(newValue);
			MyDebug.d("what the duck!!");
		}
	}

}
