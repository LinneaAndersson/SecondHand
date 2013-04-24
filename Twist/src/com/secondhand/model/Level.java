package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.debug.MyDebug;
import com.secondhand.math.PolygonUtil;
import com.secondhand.model.powerup.RandomTeleport;
import com.secondhand.model.powerup.Shield;
import com.secondhand.opengl.TexturedPolygon;
import com.secondhand.physics.PhysicsAreaChecker;
import com.secondhand.resource.PlanetType;
import com.secondhand.resource.TextureRegions;
import com.secondhand.util.RandomUtil;

public class Level {

	private List<Entity> entityList;
	private List<Enemy> enemyList;
	private int playerMaxSize;
	private PhysicsWorld physicsWorld;

	private Player player;

	private int levelWidth;
	private int levelHeight;

	private int levelNumber;

	public Level() {
		this(1);
	}
	
	public Level(final int levelNumber) {
		this.levelNumber = levelNumber;
		prepareLevel();
	}

	public void prepareLevel() {

		this.physicsWorld  = new PhysicsWorld(new Vector2(), true);
		
		computeLevelConstants();
		
		layoutLevelEntities();
		
		setupEnemies();
		

		setupWorldBounds();
	}
	
	// compute things like level width and max size for the current level
	private void computeLevelConstants() {
		this.player = new Player(new Vector2(50, 50), 20, this.physicsWorld, 20);
		
		this.levelWidth = 2000 * this.levelNumber;
		this.levelHeight = 2000 * this.levelNumber;
	
		this.playerMaxSize = 800 * this.levelNumber;
	}
	
	private void setupEnemies() {
		enemyList = new ArrayList<Enemy>();
		enemyList.add(new Enemy(new Vector2(800, 800), 30, physicsWorld, 10));

		for (Enemy enemy : enemyList) {
			entityList.add(enemy);
		}

	}

	// TODO rename method as it can be used not only as a test but in creating
	// random levels. also make sure that there are some smaller planets so that
	// you can actually win
	
	// everything except for enemies is laid out in this method,
	public List<Entity> layoutLevelEntities() {
		
		entityList = new ArrayList<Entity>();
		
		entityList.add(new Obstacle(new Vector2(200, 200), PolygonUtil.getRandomPolygon() , physicsWorld));

		entityList.add(new RandomTeleport(new Vector2(100, 500), physicsWorld));

		entityList.add(new Shield(new Vector2(20, 500), physicsWorld));

		entityList.add(new Shield(new Vector2(20, 700), physicsWorld));

		final float K = 1.2f;
		
		final float MAX_SIZE = 80f * this.levelNumber * K;
		final float MIN_SIZE = player.getRadius() - 5;
		final int PLANETS =(int)( 20 * this.levelNumber * K);
		
		// make sure they don't get too close to the edges.
		final int HEIGHT = (int)(this.levelHeight - MAX_SIZE - 50);
		final int WIDTH = (int)(this.levelWidth - MAX_SIZE - 50);
		
		final Random rng = new Random();

		for (int i = 0; i < PLANETS; ++i) {
			final float radius = rng.nextFloat() * (MAX_SIZE - MIN_SIZE)
					+ MIN_SIZE;
			float x;
			float y;

			while (true) {

				x = rng.nextInt(WIDTH);
				y = rng.nextInt(HEIGHT);

				if (PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(x,
						y), radius, radius, physicsWorld))
					break;
			}

			entityList.add(new Planet(new Vector2(x, y), radius, RandomUtil
					.randomEnum(rng, PlanetType.class), physicsWorld));
		}

		return entityList;
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

	public final void setupWorldBounds() {

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

	public void moveEnemies() {
		// enemies are in both lists because we want them
		// for easy access and for the posibility of attacking
		// each other. it would be preferable to change it later
		// if we can come up with a better way

		for (Enemy enemy : enemyList) {
			enemy.moveEnemy(player);
			for (Entity entity : entityList) {
				if (!(entity instanceof Enemy)) {
					enemy.moveEnemy(entity);
				}
			}
		}

	}

	public void sendTouchInput(final Vector2 v) {
		this.player.reachToTouch(v);
	}

	public boolean checkPlayerBigEnough() {
		return player.getRadius() >= playerMaxSize;

	}
}
