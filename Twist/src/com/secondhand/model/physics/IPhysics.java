package com.secondhand.model.physics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.secondhand.model.Enemy;
import com.secondhand.model.Entity;
import com.secondhand.model.GameWorld;
import com.secondhand.view.scene.GamePlayScene;


public interface IPhysics {

	
	//TODO: Will remove this later.
	PhysicsWorld getPhysicsWorld();
	
	void setWorldBounds(final int levelWidth, final int levelHeight);
	
	void removeWorldBounds();
	
	void registerBody(final Entity entity, final Body body, final boolean rotatation);

	void deleteBody(final boolean scheduledForDeletion);
	
	void setConnector(IShape shape);

	//void deleteBody(Body body);
	
	void applyImpulse(final Body body, final float posX, final float posY);

	boolean isStraightLine(final Entity entity, final Enemy enemy);

	
	void checkCollision(final Contact contact);

	boolean isAreaUnOccupied(final float x, final float y, final float r);
	
	void setContactListener();

	Body createType(final IShape shape, final Entity entity);

	void registerUpdateHandler(final GamePlayScene gamePlayScene);

	void setGameWorld(final GameWorld gameWorld);
}
