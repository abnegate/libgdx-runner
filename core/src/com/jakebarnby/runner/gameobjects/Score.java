package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jakebarnby.runner.gameobjects.UILabel;
/**
 * 
 * @author Jake
 *
 */
public class Score extends UILabel {
	
	private int score;

	/**
	 * 
	 * @param font
	 * @param text
	 * @param x
	 * @param y
	 */
	public Score(BitmapFont font, String text, float x, float y) {
		super(font, text, x, y);
		this.score = 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * 
	 * @param score
	 */
	public void incrementScore() {
		score++;
	}
	
	@Override 
	public void draw(SpriteBatch batch) {
		getFont().draw(batch, getText() + " " + score, getX(), getY());
	}
}
