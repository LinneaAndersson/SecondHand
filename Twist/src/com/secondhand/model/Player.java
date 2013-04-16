package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public class Player extends BlackHole {

	private int score;

	public Player(Vector2 position, float radius) {
		super(position, radius);
		//setShape(new Sprite(0, 0, TextureRegionLoader.getInstance()
			//	.loadTextureRegion("player.png", 512, 512)));
		this.score = 0;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore(int increase) {
		score += increase;
	}

}
