package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.secondhand.model.BlackHole;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.physics.FixtureDefs;

public class BlackHoleView extends CircleView {

	private final BlackHole mBlackHole;

	public BlackHoleView(final PhysicsWorld physicsWorld,
			final BlackHole blackHole) {
		super(physicsWorld, blackHole, new Circle(blackHole.getCenterX(), blackHole.getCenterY(), blackHole.getRadius()),
				FixtureDefs.BLACK_HOLE_FIXTURE_DEF);

		mBlackHole = blackHole;
	}

	protected void changeSize() {
		final CircleShape shape = (CircleShape) getShape();
		shape.setRadius(mBlackHole.getRadius()
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);


		//If it dont work this way.
		/*final CircleShape shape = (CircleShape) getBody().getFixtureList()
				.get(0).getShape();
		shape.setRadius(this.getRadius()
				/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);*/
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		if (event.getPropertyName().equalsIgnoreCase("radius")) {
			changeSize();
		} else if(event.getPropertyName().equalsIgnoreCase("move")){
			//mBlackHole.getPhysics().applyImpulse(getBody(), (Float)event.getOldValue(), (Float)event.getNewValue(), mBlackHole.getMaxSpeed());
		}
	}
}
