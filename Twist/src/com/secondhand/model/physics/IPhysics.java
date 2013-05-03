package com.secondhand.model.physics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.controller.CollisionContactListener;
import com.secondhand.model.Enemy;
import com.secondhand.model.GameWorld;
import com.badlogic.gdx.physics.box2d.Contact;

import com.secondhand.model.Entity;
import com.secondhand.view.opengl.Circle;
import com.secondhand.view.opengl.Polygon;
import com.secondhand.view.scene.GamePlayScene;

public interface IPhysics {

	
	//TODO: Will remove this later.
	public PhysicsWorld getPhysicsWorld();
	
	public void setWorldBounds(final int levelWidth, final int levelHeight);
	
	public void removeWorldBounds();
	
	public void registerBody(Entity entity, Body body, boolean rotatation);

	public void deleteBody(Boolean scheduledForDeletion);
	
	public void setConnector(IShape shape);

	//public void deleteBody(Body body);
	
	public void applyImpulse(Body body, float posX, float posY);

	public boolean isStraightLine(Entity entity, Enemy enemy);

	
	public void checkCollision(Contact contact);

	public boolean isAreaUnOccupied(float x, float y, float r);
	
	public void setContactListener();

	Body createType(IShape shape, Entity entity);

	public void registerUpdateHandler(GamePlayScene gamePlayScene);

	public void setGameWorld(GameWorld gameWorld);
}
