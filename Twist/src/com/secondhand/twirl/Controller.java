package com.secondhand.twirl;

import org.anddev.andengine.engine.Engine;

import com.secondhand.loader.FontLoader;

public class Controller {

	private Engine engine;
	
	private static Controller instance = null;
	
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}

		return instance;
	}
	
	public void initialize(Engine enigne) {
		this.engine = engine;
	}

}