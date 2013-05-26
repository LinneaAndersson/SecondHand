package com.secondhand.model.randomlevelgenerator.sat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import junit.framework.TestCase;

import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.entity.RandomLevelGenerator;
import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUpFactory;

public class RandomLevelGeneratorTest extends TestCase {

	private class GameWorldTest implements IGameWorld{
		int levelNumber;
		int levelWeight;
		int levelHeight;
		
		public GameWorldTest(int levelNumber){
			this.levelNumber=levelNumber;
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
			return levelNumber*1500;
		}

		@Override
		public int getLevelHeight() {
			// TODO Auto-generated method stub
			return levelNumber*1500;
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
	
	public void testConstructor(){
		GameWorldTest testGameWorld = new GameWorldTest(1);
		RandomLevelGenerator rLG = new RandomLevelGenerator(testGameWorld,new PowerUpFactory());
		
		assertEquals(testGameWorld.getLevelHeight(), rLG.levelHeight);
		assertEquals(testGameWorld.getLevelWidth(), rLG.levelWidth);
		
	}
}
