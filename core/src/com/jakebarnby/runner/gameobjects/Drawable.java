package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

/**
 * 
 * @author Jake
 *
 */
public interface Drawable {
	
	/**
	 * Draw this object
	 * @param batch
	 */
	public void draw(SpriteBatch batch);
	
	/**
	 * Dispose of this object
	 */
	public void dispose();
	
	/**
	 * Set up the physics for this object
	 * @param physicsWorld The world that controls the physics
	 */
	public void createPhysics(World physicsWorld);
	
	public float getX();
	public float getY();
	public float getWidth();
	public float getHeight();
}
