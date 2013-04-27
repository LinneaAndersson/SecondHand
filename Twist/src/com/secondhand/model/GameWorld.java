package com.secondhand.model;

import java.util.List;
import java.util.Stack;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.debug.MyDebug;
import com.secondhand.scene.IGamePlaySceneView;

// This class was formerly known as level. 
public class GameWorld {

	private List<Entity> entityList;
	private List<Enemy> enemyList;
	private Stack<Entity> scheduledForDeletionEntities;
	
	private int playerMaxSize;
	private PhysicsWorld physicsWorld;
	private boolean gameOver = false;

	private Player player;

	private int levelWidth;
	private int levelHeight;

	private int levelNumber;
	
	private IGamePlaySceneView view;
	
	public void setView(final IGamePlaySceneView view) {
		this.view = view;
	}
	
	public IGamePlaySceneView getView() {
		return this.view;
	}
	
	public boolean hasView() {
		return view != null;
	}

	public GameWorld() {
		this(2);
	}
	
	public GameWorld(final int levelNumber) {
		this.levelNumber = levelNumber;
		prepareLevel();
	}

	public void prepareLevel() {
		
		this.scheduledForDeletionEntities = new Stack<Entity>();
		
		this.gameOver = false;

		this.physicsWorld  = new PhysicsWorld(new Vector2(), true);
		
		// you can try lowering the values of these if the game starts lagging too much.
		this.physicsWorld.setVelocityIterations(16);
		this.physicsWorld.setPositionIterations(16);

		
		final RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(this);
		
		this.player = randomLevelGenerator.player;
		this.playerMaxSize = randomLevelGenerator.playerMaxSize;
		this.levelWidth = randomLevelGenerator.levelWidth;
		this.levelHeight = randomLevelGenerator.levelHeight;
		
		this.entityList = randomLevelGenerator.entityList;
		this.enemyList = randomLevelGenerator.enemyList;
		
		setupWorldBounds();
	}
	

	public int getLevelNumber() {
		return levelNumber;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public PhysicsWorld getPhysicsWorld() {
		return physicsWorld;
	}

	private final void setupWorldBounds() {

		final Shape[] worldBounds = new Shape[4];

		// put some invisible, static rectangles that keep the player within the
		// world bounds:
		// we do not do this using registerEntity, because these bodies are
		// static.

		worldBounds[0] = new Rectangle(0, levelHeight - 2, levelWidth, 2);
		worldBounds[1] = new Rectangle(0, 0, levelWidth, 2);
		worldBounds[2] = new Rectangle(0, 0, 2, levelHeight);
		worldBounds[3] = new Rectangle(levelWidth - 2, 0, 2, levelHeight);
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,
				0.5f, 0.5f);
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[0],
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[1],
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[2],
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, worldBounds[3],
				BodyType.StaticBody, wallFixtureDef);

	}

	public Player getPlayer() {
		return player;
	}

	public int getLevelWidth() {
		return levelWidth;
	}

	public int getLevelHeight() {
		return levelHeight;
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
	
	// for debugging

	private boolean nextLevelAdvanced = false;

	public void nextLevel() {
			
		MyDebug.d("advancing to next level");
		clearLevel();
			/*
			final IGamePlaySceneView view = currentLevel.getView();
			
			// clear physics world expect for player.
			
			
			currentLevel = new Level(currentLevel.getLevelNumber()+1);
			MyDebug.d("now we tell the view to create the level");
			view.newLevelStarted();
			// and also register all new entities in the controller. */
	}

	
	public void onManagedUpdate(final float pSecondsElapsed) {
		if (checkPlayerBigEnough() && !nextLevelAdvanced) {
			nextLevelAdvanced = true;
			nextLevel();
		} else {

			// remove bodies scheduled for deletion.
			while(!scheduledForDeletionEntities.empty()) {
				final Entity entity = scheduledForDeletionEntities.pop();
				entity.deleteBody();
			}
			
			moveEnemies();
			
			this.player.moveToNeededPositionIfNecessary();
			
			if(this.player.lostAllLives()) {
				this.gameOver = true;
			}
			
			MyDebug.d("entities: " + this.entityList.size());
		}
	}

	public boolean isGameOver() {
		return this.gameOver;
	}
	

	public void checkCollision(final Contact contact) {
		CollisionResolver.checkCollision(contact);	
	}

	public void sendTouchInput(final Vector2 v) {
		this.player.reachToTouch(v);
	}

	public boolean checkPlayerBigEnough() {
		if(player.getRadius() < playerMaxSize)
			MyDebug.d("current played size: " + player.getRadius() + " goal: " + playerMaxSize);
		return player.getRadius() >= playerMaxSize;
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
	
	// remove every entity(both from the physics world and andengine rendering)
	// from the world expect for the player.
	public void clearLevel() {
		for(Entity entity: this.entityList) {
			if(entity != player)
				entity.destroyEntity();
		}
		
		entityList.clear();
		entityList.add(player);
	}

	public void updateWithTouchInput(final Vector2 v) {
		sendTouchInput(v);
	}
}
