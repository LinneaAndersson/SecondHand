package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import com.secondhand.model.Entity;
import com.secondhand.view.opengl.Circle;

public class PlanetView extends EntityView {


	public PlanetView(Entity entity) {
		super(entity, new Circle(0,0,0));
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
	}

}
