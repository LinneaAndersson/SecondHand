package com.secondhand.model;

import org.anddev.andengine.entity.shape.IShape;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.secondhand.view.scene.GamePlayScene;


public interface IPhysics {

	//Make your Entities stay on the screen
	void setWorldBounds(final int levelWidth, final int levelHeight);
	
	//Remove your WorldBounds.
	void removeWorldBounds();
	
	//Add your body to the physicWorld
	void registerBody(final Entity entity, final Body body);

	//delete your body from physicsWorld
	void deleteBody(final boolean scheduledForDeletion);
	
	//
	void setConnector(IShape shape);
	
	// apply linear impulse on the body
	void applyImpulse(final Body body, final float posX, final float posY, final float maxSpeed);

	// checks if enemy has staight line to the entity
	boolean isStraightLine(final Entity entity, final Enemy enemy);

	// check the collision between entities
	void checkCollision(final Contact contact);

	//checks if the rectangular area i UnOccupied
	boolean isAreaUnOccupied(final float x, final float y, final float r);
	
	//making this a listener of PhysicsWorld
	void setContactListener();

	//creating a new body depending on entity.
	Body createType(final IShape shape, final Entity entity);

	void registerUpdateHandler(final GamePlayScene gamePlayScene);

	//connect this to gameWorld
	void setGameWorld(final GameWorld gameWorld);
}
