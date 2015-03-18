package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jakebarnby.runner.WorldManager;

/**
 * 
 * @author Jake
 *
 */
public class Barrel extends StaticCharacter {

	public Barrel(Sprite sprite, float x, float y) {
		super(sprite, x, y);
		getSprite().setX(x);
		getSprite().setY(y);
	}
	
	@Override 
	public void draw(SpriteBatch batch) {
		batch.draw(getSprite(), getKinematicBody().getPosition().x - getWidth()/2, getKinematicBody().getPosition().y - getHeight()/2, getWidth() * 4, getHeight() * 4);
		
		if (!WorldManager.GAME_OVER) {
			getSprite().setPosition(getKinematicBody().getPosition().x, getKinematicBody().getPosition().y);
			getKinematicBody().setLinearVelocity(-3.15f, 0f);
		} else {
			getKinematicBody().setActive(false);
		}
	}
}
