package com.secondhand.twirl;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class MainActivity extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	
	SmoothCamera camera;
	
	MainMenuScene mainMenuScene;
	
	@Override
	public Engine onLoadEngine() {
		
	    camera = new SmoothCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT, Float.MAX_VALUE, Float.MAX_VALUE, 1.0f);
	    
	    EngineOptions engineOptions = new EngineOptions(
	    		true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	    engineOptions.setNeedsSound(true);
	    Engine engine = new Engine(engineOptions);
	    
	    // initialize loader classes:
	    FontLoader.getInstance().initialize(this, engine);
	    
	    mainMenuScene = new MainMenuScene(camera);
	    
	    // TODO: we should probably save the engine somewhere now for easy access(in the controller?)
	    
	    return engine;	
	}

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		mainMenuScene.loadResources();
	}

	@Override
	public Scene onLoadScene() {
		// the FPS logger is useful for optimizing performance.(the FPS is shown in LogCat)
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		mainMenuScene.loadScene();
		this.mEngine.setScene(mainMenuScene);
		
		return mainMenuScene;
		/*
		Scene scene = new Scene();
		scene.setBackground(new ColorBackground(1.0f, 0, 0));
		return scene;*/
	}

	@Override
	public void onLoadComplete() {
		// nothing
	}
}
