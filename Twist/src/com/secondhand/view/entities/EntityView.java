package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.model.Entity;
import com.secondhand.view.opengl.Polygon;
import com.secondhand.view.physics.MyPhysicsEntity;

public abstract class EntityView implements PropertyChangeListener {
	protected final IShape shape;
	//Need this for physics.
	private final Entity entity;
	private final Body body;
	
	public EntityView(final PhysicsWorld physicsWorld, final Entity entity , final IShape shape,
			final Body body){
		this.shape=shape;
		this.entity = entity;
		entity.addPropertyChangeListener(this);
		this.body = body;
		
		// TODO: figure out some better way to do this.
		if(shape instanceof Polygon) {
			((Polygon) shape).setBody(body);
		}
		
		entity.setPhysics(	new MyPhysicsEntity(physicsWorld, entity, shape, body));
	}
	
	public Body getBody() {
		return this.body;
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public IShape getShape(){
		return shape;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
	}
}
