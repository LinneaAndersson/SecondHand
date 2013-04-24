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

	private static int levelNumber = 0;

	// many constructors necessary?
	// default maxsize?
	public Level() {
		this(100);

		// the only time we should call this constructor
		// is when starting a completely new game

		levelNumber = 0;
	}

	public Level(final int maxSize) {

		final PhysicsWorld pW = new PhysicsWorld(new Vector2(), true);

		final Player player = new Player(new Vector2(50, 50), 20, pW, 20);
		init(maxSize, pW, player, // NOPMD
				createTestPlanets(pW), 2000, 2000); // NOPMD
	}

	public void init(final int maxSize, final PhysicsWorld pW, final Player p,
			final List<Entity> otherEntities, final int levelWidth,
			final int levelHeight) {
		enemyList = new ArrayList<Enemy>();
		levelNumber += 1;
		this.playerMaxSize = maxSize;
		this.physicsWorld = pW;
		player = p;
		entityList = otherEntities;
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		enemyList.add(new Enemy(new Vector2(800, 800), 30, physicsWorld, 10)); // tmp

		for (Enemy enemy : enemyList) {
			entityList.add(enemy);
		}

		setupWorldBounds();
	}

	public Level(final int maxSize, final PhysicsWorld pW, final Player p,
			final List<Entity> otherEntities, final int levelWidth,
			final int levelHeight) {
		init(maxSize, pW, p, otherEntities, levelWidth, levelHeight); // NOPMD
	}

	// this constructor could be useful when creating
	// new levels and want to keep player and physics
	// from the last level
	// creates a new physicsWorld just in case...
	public Level(final Level level) {
		final PhysicsWorld pw = new PhysicsWorld(new Vector2(),true);
		
		init(level.getPlayerMaxSize(),pw, level // NOPMD
				.getPlayer(), createTestPlanets(pw), level //NOPMD
				.getLevelWidth(), level.getLevelHeight()); // NOPMD
	}

	// TODO rename method as it can be used not only as a test but in creating
	// random levels. also make sure that there are some smaller planets so that
	// you can actually win
	public static List<Entity> createTestPlanets(final PhysicsWorld physicsWorld) {
		final List<Entity> testPlanets = new ArrayList<Entity>();

		final TexturedPolygon polygon = new TexturedPolygon(200, 200,
				PolygonUtil.getRandomPolygon(),
				TextureRegions.getInstance().obstacleTexture);
		testPlanets.add(new Obstacle(polygon, physicsWorld));

		testPlanets.add(new RandomTeleport(new Vector2(100, 500), physicsWorld));

		testPlanets.add(new Shield(new Vector2(20, 500), physicsWorld));

		testPlanets.add(new Shield(new Vector2(20, 700), physicsWorld));

		final int MAX_SIZE = 120;
		final int MIN_SIZE = 40;
		final int PLANETS = 20;
		final int HEIGHT = 1800;
		final int WIDTH = 1800;
		final Random rng = new Random();

		// MyDebug.d(("area unoccupied: " +
		// PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(100,100),
		// 100, 100, physicsWorld)));

		for (int i = 0; i < PLANETS; ++i) {
			final float radius = rng.nextFloat() * (MAX_SIZE - MIN_SIZE)
					+ MIN_SIZE;
			float x;
			float y;

			while (true) {

				x = rng.nextInt(WIDTH);
				y = rng.nextInt(HEIGHT);

				if (PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(x,
						y), radius, radius, physicsWorld)) {
					break;
				}
			}

			testPlanets.add(new Planet(new Vector2(x, y), radius, RandomUtil
					.randomEnum(rng, PlanetType.class), physicsWorld));
		}

		return testPlanets;
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
