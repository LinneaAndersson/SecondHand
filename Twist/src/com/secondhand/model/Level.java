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
import com.secondhand.model.PowerUp.Effect;
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

		final Player player = new Player(new Vector2(50, 50), 20, pW);
		init(maxSize, pW, player , // NOPMD
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
		enemyList.add(new Enemy(new Vector2(800, 800), 30, physicsWorld)); // tmp

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
	// Preferable to at least change the entity list?
	public Level(final Level level) {
		this(level.getPlayerMaxSize(), level.getPhysicsWorld(), level
				.getPlayer(), level.getEntityList(), level.getLevelWidth(),
				level.getLevelHeight());
	}

	
	public static List<Entity> createTestPlanets(final PhysicsWorld physicsWorld) {
		final List<Entity> testPlanets = new ArrayList<Entity>();
		
		final TexturedPolygon polygon = new TexturedPolygon(200, 200,
				PolygonUtil.getRandomPolygon(),
				TextureRegions.getInstance().obstacleTexture);
		testPlanets.add(new Obstacle(polygon, physicsWorld));

		testPlanets.add(new PowerUp(new Vector2(100, 500), Effect.RANDOM_TELEPORT,
				TextureRegions.getInstance().powerUpTexture, physicsWorld));

		testPlanets.add(new PowerUp(new Vector2(20, 500), Effect.SHIELD,
				TextureRegions.getInstance().powerUpTexture, physicsWorld));
		
		testPlanets.add(new PowerUp(new Vector2(20, 700), Effect.SHIELD,
				TextureRegions.getInstance().powerUpTexture, physicsWorld));
		
		
		

		final int MAX_SIZE = 120;
		final int MIN_SIZE = 40;
		final int PLANETS = 20;
		final int HEIGHT = 1800;
		final int WIDTH = 1800;
		final Random rng = new Random();
		
		//MyDebug.d(("area unoccupied: " + PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(100,100), 100, 100, physicsWorld)));
		
		for(int i = 0; i < PLANETS; ++i) {
			final float radius = rng.nextFloat() * (MAX_SIZE - MIN_SIZE) + MIN_SIZE;
			float x;
			float y;
			
			while(true) {
				
				x = rng.nextInt(WIDTH);
				y = rng.nextInt(HEIGHT);
				
				if(PhysicsAreaChecker.isRectangleAreaUnoccupied(new Vector2(x,y), radius, radius, physicsWorld)) {
					break;
				}
			}
			
			testPlanets.add(new Planet(new Vector2(x, y), radius, RandomUtil.randomEnum(rng, PlanetType.class),
					physicsWorld));
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

	
	// if we don't want level to handle this we could just move it
	// TODO PowerUP is high priority so we somehow have to fix it
	public void activateEffect(final Effect effect) {
		switch (effect) {
		case RANDOM_TELEPORT:
			// remove old player
			// I think simply changing the position of the physics body is
			// enough(I hope).
			// You can get a circle shape from the entity using something like:
			// final CircleShape shape =
			// (CircleShape)player.getBody().getFixtureList().get(0).getShape();
			// and then simply set the position of this shape.
			
			/*
			final CircleShape shape = (CircleShape)player.getBody().getFixtureList().get(0).getShape();
			player.getBody().setTransform(new Vector2(50/32f, 50/32f), 0);
			shape.setPosition(new Vector2(50/32f, 50/32f));
			player.getShape().setPosition(50, 50);
			*/

			// needs to confirm empty position
			// use PhysicsAreaChecker to confirm this - Eric
		
			// add new player at new position
			player.setEffect(effect);
			break;
		case SHIELD:
			MyDebug.d("shield powerup");
			player.setEffect(effect);
			break;
		case SPEED_UP:
			player.setEffect(effect);
			break;
		case EAT_OBSTACLE:
			player.setEffect(effect);
			break;
		case SCORE_UP:
			// random number here?
			// TODO also add animation for "+100"?
			player.increaseScore(100);
			break;
		case NONE:
			break;
		default:
			break;

		}

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

	public void moveEntities(final Vector2 v) {

		/*
		 * The problem was that we got the coordinates of the player from the
		 * getBody(). This is a problem, since Box2D uses a coordinate system
		 * different from that of AndEngine; in this system, all AndEngine
		 * coordinates are first divided by 32 before they're feed into Box2D.
		 * So the body was using the coordinate system of Box2D, which was why
		 * we had to multiply the coordinates of the body coordintes with
		 * 30(with Linnea and Andreas found through trial and error) But IShape
		 * on the other hand, is a AndEngine class and it therefore uses the
		 * coordinate system of AndEngine, which is why we should it instead of
		 * the body.
		 * 
		 * See section 1.7 in http://www.box2d.org/manual.html for an
		 * explanation of why AndEngine does this division by 32.
		 */

		Vector2 movementVector = new Vector2((player.getCenterX() - v.x),
				player.getCenterY() - v.y);

		// the closer the touch is to the player, the more force do we need to
		// apply.

		// make it a bit slower depending on how big it is.
		movementVector = movementVector.mul(player.getRadius() * 0.001f);

		/*
		 * Made a better test, like I said the earlier test we did was not
		 * functional, and since the length of a vector is always positive, the
		 * Math.abs(player.getBody().getLinearVelocity().len()) has no effect.
		 * This runs rather smoothly. Try it!
		 */
		final float maxSpeed = 20;
		final Vector2 testVector = new Vector2(player.getBody()
				.getLinearVelocity());
		if (testVector.add(movementVector).len() > maxSpeed)
			// Check if new velocity doesn't exceed maxSpeed!
			return;

		player.getBody().applyLinearImpulse(movementVector,
				player.getBody().getWorldCenter());
	}

	public boolean checkPlayerBigEnough() {
		return player.getRadius() >= playerMaxSize;

	}
}
