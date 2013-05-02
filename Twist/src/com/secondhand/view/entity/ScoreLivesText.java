package com.secondhand.view.entity;

import java.util.Arrays;

import org.anddev.andengine.entity.text.ChangeableText;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.view.resource.Fonts;

// used to show the score and lives in the HUD.
public final class ScoreLivesText extends ChangeableText {

	private static final int MAX_SCORE_DIGITS = 10;
	private static final int MAX_LIVES_DIGITS = 4;
	
	public static String getPlaceHolderString(final int length) {
		// a length of 20 should be good enough.
		final char[] exmarks = new char[length];
		Arrays.fill(exmarks, ' ');
		return new String(exmarks);
	}
	
	public static String constructString(final String score, final String lives) {
		return "Score: " + score + "  Lives: " + lives;
	}
	
	public static String constructString(final int score, final int lives) {
		return constructString(score + "", lives + "");
	}
	
	private int lives;
	private int score;
	
	public ScoreLivesText(final Vector2 position, final int score, final int lives) {
		super(position.x, position.y, Fonts.getInstance().menuItemFont,
				constructString(getPlaceHolderString(MAX_SCORE_DIGITS),
						getPlaceHolderString(MAX_LIVES_DIGITS)));
		
		this.lives = lives;
		this.score = score;
		
		setText(constructString(score, lives));
	}
	
	public void setScore(final int newScore) {
		setText(constructString(newScore, lives));	
		this.score = newScore;
	}
	
	public void setLives(final int newLives) {
		setText(constructString(score, newLives));	
		this.lives = newLives;
	}
	
}
