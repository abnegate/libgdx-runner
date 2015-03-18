package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
/**
 * 
 * @author Jake
 *
 */
public class UILabel implements Drawable {
	private BitmapFont font;
	private String text;

	private float x;
	private float y;
	
	public UILabel(BitmapFont font, String text, float x, float y) {
		this.font = font;
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		font.draw(batch, text, x, y);
	}
	
	@Override
	public void dispose() {
		font.dispose();
	}
	
	/**
	 * 
	 * @return
	 */
	public BitmapFont getFont() {
		return font;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void createPhysics(World physicsWorld) {}
	
	@Override
	public float getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public float getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
	}

}
