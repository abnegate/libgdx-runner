package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
/**
 * A players inventory
 * @author Jake Barnby
 * 
 * 4 March 2015
 */
public class Inventory implements Drawable {
	
	private Array<Consumable> items = new Array<Consumable>();		//The items this player is currently holding
	
	/**
	 * Creates a new inventory
	 */
	public Inventory() {
		
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPhysics(World physicsWorld) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


}
