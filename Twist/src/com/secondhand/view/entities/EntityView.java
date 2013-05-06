package com.secondhand.view.entities;

import java.beans.PropertyChangeListener;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.model.Entity;

public abstract class EntityView implements PropertyChangeListener {
	private final IShape shape;
	//Need this for physics.
	private final Entity entity;
	private final Body body;
	
	public EntityView(final Entity entity, final IShape shape){
		this.shape=shape;
		this.entity = entity;
		entity.addPropertyChangeListener(this);
		
		entity.setPhysics(physics);
		
		//Will be here later
		//if(this instanceof EnemyView)
		//body=entity.getPhysics().createType(shape,entity);
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public IShape getShape(){
		return shape;
	}
	
}
