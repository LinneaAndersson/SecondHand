package com.secondhand.scene;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.shape.IShape;

import android.content.Context;
import android.view.KeyEvent;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.controller.CollisionContactListener;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.Entity;
import com.secondhand.model.Level;
import com.secondhand.model.Player;
import com.secondhand.model.Universe;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.opengl.StarsBackground;
import com.secondhand.physics.PhysicsDestroyer;

public class GamePlayScene extends GameScene implements PropertyChangeListener, IGamePlaySceneView {
	
	private List<IShape> shapeList;
	private IShape player;
	
	private final Universe universe;
	
	public GamePlayScene(final Engine engine, final Context context) {
		super(engine, context);
		this.universe = new Universe();
	}
	
	public Universe getUniverse() {
		return universe; 
	}
	
	public void setShapes(final List<IShape> list){
		shapeList = list;
	}
	
	public void setPlayer(final IShape player){
		this.player = player;
	}

	public void registerNewLevel() {
	final Level currentLevel = universe.getLevel();
		
		// reset for the newly created physicsworld. 
		PhysicsDestroyer.getInstance().reset(engine, currentLevel.getPhysicsWorld());
		
		registerUpdateHandler(currentLevel.getPhysicsWorld());
		currentLevel.getPlayer().addListener(this);
		
		final List<IShape> shapes = new ArrayList<IShape>();

		setPlayer(currentLevel.getPlayer().getShape());

		for (final Entity entity : currentLevel.getEntityList()) {
			shapes.add(entity.getShape());
		}
		setShapes(shapes);
		
		currentLevel.getPhysicsWorld().setContactListener(new CollisionContactListener(universe));
		currentLevel.setView(this);
		
		final float width = universe.getLevel().getLevelWidth();
		final float height = universe.getLevel().getLevelHeight();
		
		// TODO: get this background to work.
		/*final List<TextureRegion> starsTextures = new ArrayList<TextureRegion>();
		starsTextures.add(TextureRegions.getInstance().starsTexture);
		this.attachChild(new RandomRepeatingBackground(starsTextures, width, height));*/
		
		this.attachChild(new StarsBackground(50, 5.0f, width, height));
		this.attachChild(new StarsBackground(100, 3.0f, width, height));
        this.attachChild(new StarsBackground(130, 1.0f, width, height));
		
		// now load the scene(attach all the entities)
        player.detachSelf();
		attachChild(player);
		for(final IShape shape : shapeList){
			shape.detachSelf();
			attachChild(shape);
		}
		
		this.smoothCamera.setBounds(0, width, 0, height);
		this.smoothCamera.setBoundsEnabled(true);
		
		engine.getCamera().setChaseEntity(player);
	}

	@Override
	public void loadScene() {
		 registerNewLevel();
	}
	
	// Undo camera lock on player
	public void resetCamera() {
		smoothCamera.setChaseEntity(null);
		engine.getCamera().setCenter(0, 0);
	}
	
	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_BACK
				&& pEvent.getAction() == KeyEvent.ACTION_DOWN) {
			final AllScenes parent = getParentScene();
			if (parent != null) {
				setScene(parent);
				resetCamera();
				return true;
			}
			else
				return false;
		} else {
			return false;
		}
	}
	
	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed){
		super.onManagedUpdate(pSecondsElapsed);
		if(universe.isGameOver()){
			MyDebug.d("GameOver");
			resetCamera();
			setScene(AllScenes.GAME_OVER_SCENE);
		}
		universe.onManagedUpdate(pSecondsElapsed);
		
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		final Player player = universe.getLevel().getPlayer();
		final PowerUp powerUp = ((PowerUp)event.getNewValue());
		engine.registerUpdateHandler(powerUp.getTimer(player));
	}

	@Override
	public void pickedUpScorePowerUp(final int score, final Vector2 position) {
		MyDebug.d("score: "+ score + " at pos " + position);
	
		this.attachChild(new AddScoreText(score, position));
		
	}

	@Override
	public void newLevelStarted() {
		MyDebug.d("new level!");
		registerNewLevel();
	}
}
