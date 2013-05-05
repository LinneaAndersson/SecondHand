package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import com.secondhand.model.Entity;
import com.secondhand.view.opengl.Circle;

public class PlanetView extends EntityView {


	public PlanetView(Entity entity) {
		//Just for now.
		super(entity, new Circle(0,0,0));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
