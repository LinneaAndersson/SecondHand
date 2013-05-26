package com.secondhand.view.entity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.secondhand.model.entity.BlackHole;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.physics.FixtureDefs;

public class BlackHoleView extends CircleView implements PropertyChangeListener {

	public BlackHoleView(final PhysicsWorld physicsWorld,
			final BlackHole blackHole) {
		super(physicsWorld, blackHole, new Circle(
				blackHole.getInitialPosition().x,
				blackHole.getInitialPosition().y, blackHole.getRadius()),
				FixtureDefs.BLACK_HOLE_FIXTURE_DEF);

	}

	protected void changeSize(final float size) {
		final CircleShape shape = (CircleShape) this.body.getFixtureList()
				.get(0).getShape();
		shape.setRadius(size / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		final Circle circle = (Circle) this.shape;
		circle.setRadius(size);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {

		if (event.getPropertyName().equalsIgnoreCase(BlackHole.RADIUS)) {

			final float newValue = (Float) event.getNewValue();

			this.changeSize(newValue);
		}
	}

}
