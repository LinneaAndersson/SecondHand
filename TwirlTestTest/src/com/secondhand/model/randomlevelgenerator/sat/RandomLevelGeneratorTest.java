package com.secondhand.model.randomlevelgenerator.sat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import junit.framework.TestCase;

import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.GameSettings;
import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.entity.Obstacle;
import com.secondhand.model.entity.Planet;
import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.entity.RandomLevelGenerator;
import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.model.powerup.PowerUpFactory;

public class RandomLevelGeneratorTest extends TestCase {

	private class GameWorldTest implements IGameWorld {
		int levelNumber;
		int levelWeight;
		int levelHeight;
		public static final float PLAYER_STARTING_SIZE = 30f;

		public GameWorldTest(int levelNumber) {
			this.levelNumber = levelNumber;
		}

		@Override
		public void addListener(PropertyChangeListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeListener(PropertyChangeListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public PropertyChangeSupport getPropertyChangeSupport() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getLevelNumber() {
			// TODO Auto-generated method stub
			return levelNumber;
		}

		@Override
		public IPhysicsWorld getPhysics() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getLevelWidth() {
			// TODO Auto-generated method stub
			return levelNumber * 1500;
		}

		@Override
		public int getLevelHeight() {
			// TODO Auto-generated method stub
			return levelNumber * 1500;
		}

		@Override
		public Player getPlayer() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Entity> getEntityList() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isGameOver() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void updateGameWorld() {
			// TODO Auto-generated method stub

		}

		@Override
		public void updateWithTouchInput(Vector2 v) {
			// TODO Auto-generated method stub

		}

		@Override
		public PowerUpList getPowerUpList() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public float getCompletion() {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	public void testConstructor() {
		GameSettings.getInstance().setHasEnemies(true);
		int nObstacles = 0, nPlanets = 0, nEnemies = 0, nPowerUps = 0;
		GameWorldTest testGameWorld = new GameWorldTest(1);
		RandomLevelGenerator rLG = new RandomLevelGenerator(testGameWorld,
				new PowerUpFactory());

		// Testing public variables
		assertEquals(testGameWorld.getLevelHeight(), rLG.levelHeight);
		assertEquals(testGameWorld.getLevelWidth(), rLG.levelWidth);
		assertEquals(testGameWorld.PLAYER_STARTING_SIZE, rLG.playerInitialSize);
		assertEquals(testGameWorld.PLAYER_STARTING_SIZE
				* (testGameWorld.levelNumber + 1), rLG.playerMaxSize * 1f);
		
		// Testing the entity and enemyList		
		for (Entity entity : rLG.entityList) {
		
			if (entity instanceof Enemy) {
				nEnemies++;
			} else if (entity instanceof Obstacle) {
				nObstacles++;
			} else if (entity instanceof Planet) {
				nPlanets++;
			} else if (entity instanceof PowerUp) {
				nPowerUps++;
			}
		}
		
		assertEquals((7+5+5+14)*testGameWorld.levelNumber,rLG.entityList.size());
		
		assertEquals(nEnemies, testGameWorld.levelNumber*5);
		assertEquals(nEnemies, rLG.enemyList.size());
		
		assertEquals(nObstacles, testGameWorld.levelNumber*5);
		
		assertEquals(nPlanets,14*testGameWorld.levelNumber);
		
		assertEquals(nPowerUps, testGameWorld.levelNumber*7);

	}
}
