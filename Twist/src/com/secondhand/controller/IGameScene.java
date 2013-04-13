package com.secondhand.controller;


import org.anddev.andengine.entity.scene.Scene;

import android.view.KeyEvent;

public interface IGameScene {
    void loadResources();

    void loadScene();
    
    Scene getScene();

	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent);
	
}