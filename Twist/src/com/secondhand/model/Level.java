package com.secondhand.model;

import java.util.List;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.scene.IGamePlaySceneView;

public class Level {

	private List<Entity> entityList;
	private List<Enemy> enemyList;
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

	public Level() {
		this(1);
	}
	
	public Level(final int levelNumber) {
		this.levelNumber = levelNumber + 1;
		prepareLevel();
	}

	public void prepareLevel() {
		
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
	
	
	public int getPlayerMaxSize() {
		return playerMaxSize;
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
	
	public void onManagedUpdate(final float pSecondsElapsed){ 
		moveEnemies();
		
		this.player.moveToNeededPositionIfNecessary();
		
		if(this.player.lostAllLives()) {
			this.gameOver = true;
		}
		
		// move player if necessary. 
	}
	private void handleBlackHoleCollision(final Entity entityA,
			final Entity entityB) {

		BlackHole blackHole;
		Entity other;
		if (entityA instanceof BlackHole) {
			blackHole = (BlackHole) entityA;
			other = entityB;
		} else {
			other = entityA;
			blackHole = (BlackHole) entityB;
		}
		
		
		blackHole.eatEntity(other);


	}
	
	public boolean isGameOver() {
		return this.gameOver;
	}

	public void checkCollision(final Contact contact) {
		// if one or both is null, then we are dealing with a collision
		// involving one or
		// two non-entities
		// (ie, a black hole collides with the wall),
		// and we are not interested in handling such a collision
		if (contact.getFixtureA().getBody().getUserData() == null
				|| contact.getFixtureB().getBody().getUserData() == null) {
			return;
		}
		
		// now we know both the bodies are entities.
		final Entity entityA = (Entity) contact.getFixtureA().getBody()
				.getUserData();
		final Entity entityB = (Entity) contact.getFixtureB().getBody()
				.getUserData();

		
		// collisions involving black holes are the only ones we're interested in.
		if (entityA instanceof BlackHole || entityB instanceof BlackHole) {
			handleBlackHoleCollision(entityA, entityB);
		} 
	}

	public void sendTouchInput(final Vector2 v) {
		this.player.reachToTouch(v);
	}

	public boolean checkPlayerBigEnough() {
		return player.getRadius() >= playerMaxSize;

	}
}
