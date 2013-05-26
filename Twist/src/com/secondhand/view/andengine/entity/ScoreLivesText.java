package com.secondhand.view.andengine.entity;

import java.util.Arrays;

import org.anddev.andengine.entity.text.ChangeableText;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.view.resource.Fonts;
import com.secondhand.view.resource.LocalizationStrings;

// used to show the score and lives, and the completion in the HUD.
public final class ScoreLivesText extends ChangeableText {

	private static final int MAX_SCORE_DIGITS = 10;
	private static final int MAX_LIVES_DIGITS = 4;
	
	public static String getPlaceHolderString(final int length) {
		// a length of 20 should be good enough.
		final char[] exmarks = new char[length];
		Arrays.fill(exmarks, ' ');
		return new String(exmarks);
	}
	
	private static String scoreString;
	private static String livesString;
	private static String completionString;
	
	public static String constructString(final String score, final String lives, final String completion) {
		
		// cache them
		if(scoreString == null || livesString == null) {
			scoreString = LocalizationStrings.getInstance().getLocalizedString("score_string");
			livesString = LocalizationStrings.getInstance().getLocalizedString("lives_string");
			completionString = LocalizationStrings.getInstance().getLocalizedString("completion_string");
		}
		
		return scoreString + score + " " +  livesString + lives + " " + completionString + completion;
	}
	
	public static String constructString(final int score, final int lives, final float completionRatio) {
		
		
		
		return constructString(score + "", lives + "", ((int)(100*completionRatio)) + "%");
	}
	
	private int lives;
	private int score;
	private float completionRatio;
	
	public ScoreLivesText(final Vector2 position, final int score, final int lives, final float completionRatio) {
		super(position.x, position.y, Fonts.getInstance().menuItemFont,
				constructString(getPlaceHolderString(MAX_SCORE_DIGITS),
						getPlaceHolderString(MAX_LIVES_DIGITS), getPlaceHolderString(MAX_LIVES_DIGITS)));
		
		this.lives = lives;
		this.score = score;
		this.completionRatio = 0;
		
		setText(constructString(score, lives, completionRatio));
	}
	
	public void setScore(final int newScore) {
		setText(constructString(newScore, lives, completionRatio));	
		this.score = newScore;
	}
	
	public void setLives(final int newLives) {
		setText(constructString(score, newLives, completionRatio));	
		this.lives = newLives;
	}
	
	public void setCompletionRatio(float newCompletionRatio) { // NOPMD
		
		if(newCompletionRatio > 1f) {
			newCompletionRatio = 1f;
		}
		setText(constructString(score, lives, newCompletionRatio));	

		this.completionRatio = newCompletionRatio;
		
	}
	
}
