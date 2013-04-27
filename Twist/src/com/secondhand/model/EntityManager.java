package com.secondhand.model;

import java.util.List;
import java.util.Stack;

import com.secondhand.debug.MyDebug;

// Manages all the entities of the GameWorld
public class EntityManager {

	private final List<Entity> entityList;
	private final List<Enemy> enemyList;
	private final Stack<Entity> scheduledForDeletionEntities;
	
	private final Player player;

	public EntityManager(final Player player, final List<Entity> entityList, final List<Enemy> enemyList) {
		this.scheduledForDeletionEntities = new Stack<Entity>();
		this.player = player;
		this.entityList = entityList;
		this.enemyList = enemyList;
	}
	
	public List<Entity> getEntityList() {
		return entityList;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	private void moveEnemies() {
		// enemies are in both lists because we want them
		// for easy access and for the posibility of attacking
		// each other. it could be preferable to change it later
		// if we can come up with a better way
		
		for (final Enemy enemy : enemyList) {
			enemy.moveEnemy(player, entityList);
		}
	}
	
	public void onManagedUpdate(final float pSecondsElapsed) {
		// remove bodies scheduled for deletion.
		while(!scheduledForDeletionEntities.empty()) {
			final Entity entity = scheduledForDeletionEntities.pop();
			entity.deleteBody();
		}
		
		moveEnemies();
		
		this.player.moveToNeededPositionIfNecessary();
		
		MyDebug.d("entities: " + this.entityList.size());
	}
	
	public void removeEntityFromList(final Entity entity) {
		this.entityList.remove(entity);
	}
	
	public void removeEnemyFromList(final Enemy enemy) {
		this.enemyList.remove(enemy);
	}

	public void scheduleEntityForDeletion(final Entity entity) {
		this.scheduledForDeletionEntities.add(entity);
	}
	
	public void removeAllEntitiesExpectForPlayer() {
		for(Entity entity: this.entityList) {
			if(entity != player)
				entity.destroyEntity();
		}
		
		entityList.clear();
		entityList.add(player);
	}
}
