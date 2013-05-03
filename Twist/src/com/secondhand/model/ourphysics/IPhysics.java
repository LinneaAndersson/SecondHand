package com.secondhand.model.ourphysics;

import org.anddev.andengine.entity.shape.IShape;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.model.Entity;

public interface IPhysics {

	public void setWorldBounds(IShape[] shape);
	
	public void removeWorldBounds();
	
	public void registerBody(Entity entity, Body body, boolean rotatation);
	
	//public void deleteBody(Body body);
	
	public void applyImpulse(Body body, float posX, float posY);
}
