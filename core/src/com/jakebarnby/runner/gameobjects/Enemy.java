package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jakebarnby.runner.utils.AnimationController;

public class Enemy extends Character {

	private boolean dead;
	
	public Enemy(float x, float y) {
		super(x, y);
		setAnimations(new AnimationController(new String[] {}, new int[] {}, new int[] {}, 0.07f));
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (!dead) {
			
		}
	}
}
