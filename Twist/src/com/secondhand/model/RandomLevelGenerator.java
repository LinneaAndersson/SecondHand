package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.math.PolygonUtil;
import com.secondhand.model.powerup.RandomTeleport;
import com.secondhand.model.powerup.Shield;
import com.secondhand.physics.PhysicsAreaChecker;
import com.secondhand.resource.PlanetType;
import com.secondhand.util.RandomUtil;

public class RandomLevelGenerator {

	private final PhysicsWorld physicsWorld;
	private final int levelNumber;
	
	public final Player player;
	public final int levelWidth;
	public final int levelHeight;
	public final int playerMaxSize;
	
	
	public final List<Entity> entityList;
	public final List<Enemy> enemyList;
	
	
	RandomLevelGenerator(final int levelNumber, final PhysicsWorld physicsWorld) {
		this.physicsWorld = physicsWorld;
		this.levelNumber = levelNumber;
		
		this.player = new Player(new Vector2(50, 50), 20, physicsWorld, 20);
		
		this.levelWidth = 2000 * levelNumber;
		this.levelHeight = 2000 * levelNumber;
	
		this.playerMaxSize = 800 * levelNumber;
		
		this.entityList = new ArrayList<Entity>();
		placeOutLevelEntities();
		
		this.enemyList = new ArrayList<Enemy>();
		placeOutEnemies();
	}
	

	private void placeOutEnemies() {
		enemyList.add(new Enemy(new Vector2(800, 800), 30, physicsWorld, 10));

		for (Enemy enemy : enemyList) {
			entityList.add(enemy);
		}

	}

	
	private void placeOutLevelEntities() {
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
	}
}
