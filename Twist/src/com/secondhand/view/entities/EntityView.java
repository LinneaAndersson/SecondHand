package com.secondhand.view.entities;

import java.beans.PropertyChangeListener;

import com.secondhand.model.Entity;

public abstract class EntityView implements PropertyChangeListener {
	Entity entity;
	
	public EntityView(Entity entity){
		this.entity = entity;
		entity.addPropertyChangeListener(this);
	}
	
	public Entity getEntity(){
		return entity;
	}
	
}
