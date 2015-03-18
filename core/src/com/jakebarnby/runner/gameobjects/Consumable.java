package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * An item that the player can consume
 * @author Jake Barnby
 * 4 March 2015
 */
public abstract class Consumable implements Drawable {
	private Sprite sprite;
	private boolean pickedUp = false;
	
	/**
	 * Use this consumable
	 */
	public abstract void use();

	@Override
	public abstract void draw(SpriteBatch batch);

	@Override
	public void dispose() {
		sprite.getTexture().dispose();
	}

	@Override
	public abstract void createPhysics(World physicsWorld);

	/**
	 * 
	 * @return
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * 
	 * @param sprite
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * 
	 * @return
	 */
	public abstract Fixture getFixture();
	
	/**
	 * 
	 * @return
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}

	/**
	 * 
	 * @param pickedUp
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
}
