package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.entity.shape.IShape;

import com.secondhand.model.Entity;

public abstract class EntityView implements PropertyChangeListener {
	
	protected Entity entity;
	protected IShape shape;
	
	public EntityView(Entity entity, IShape shape){
		this.entity = entity;
		this.shape=shape;
		entity.addPropertyChangeListener(this);
		
		//Will be here later
		//entity.setBody(entity.getPhysics().createType(shape,entity));
	}
	
	public Entity getEntity(){
		return entity;
	}
	
	public IShape getShape(){
		return shape;
	}
	
}
