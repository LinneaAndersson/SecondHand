package com.secondhand.model.randomlevelgenerator.sat;

import com.secondhand.model.physics.Vector2;

public class Circle extends Shape {
	
	public Vector2 position;
	public float radius;
	
	public Circle(final Vector2 position,  final float radius) {
		super();
		this.position = position;
		this.radius = radius;
	}
		
}
