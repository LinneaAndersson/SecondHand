package com.secondhand.scene;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;
import android.view.KeyEvent;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.Player;
import com.secondhand.model.Universe;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.opengl.RandomRepeatingBackground;
import com.secondhand.opengl.StarsBackground;
import com.secondhand.resource.TextureRegions;

public class GamePlayScene extends GameScene implements PropertyChangeListener {
	
	
	private List<IShape> shapeList;
	private IShape player;
	
	private final Universe universe = Universe.getInstance();
	
	private PowerUp currentEffect = null;
	
	public GamePlayScene(final Engine engine, final Context context) {
		super(engine, context);
	}
	
	public void setShapes(final List<IShape> list){
		shapeList = list;
	}
	
	public void setPlayer(final IShape player){
		this.player = player;
	}

	@Override
	public void loadResources() {
		// load the resources of this scene
	}

	@Override
	public void loadScene() {
	
		// level width. it's the camera width and height for now.
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
		
		// set the level width here.
		this.smoothCamera.setBounds(0, width, 0, height);
		
		this.smoothCamera.setBoundsEnabled(true);
		
		engine.getCamera().setChaseEntity(player);
		
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
		final Player player = Universe.getInstance().getLevel().getPlayer();
		final PowerUp powerUp = ((PowerUp)event.getNewValue());
		engine.registerUpdateHandler(powerUp.getTimer(player));
	}
}
