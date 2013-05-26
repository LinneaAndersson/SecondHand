package com.secondhand.model.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;

public interface IGameWorld {

	void addListener(final PropertyChangeListener listener);

	void removeListener(final PropertyChangeListener listener);

	PropertyChangeSupport getPropertyChangeSupport();

	int getLevelNumber();

	IPhysicsWorld getPhysics();

	int getLevelWidth();

	int getLevelHeight();

	Player getPlayer();

	List<Entity> getEntityList();

	boolean isGameOver();

	void updateGameWorld();

	void updateWithTouchInput(final Vector2 v);

	PowerUpList getPowerUpList();

	float getCompletion();

}
