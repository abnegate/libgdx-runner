package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jakebarnby.runner.WorldManager;
import com.jakebarnby.runner.utils.AnimationController;

public class RedEnemy extends DynamicCharacter {
	
	private TextureRegion deathImage = new TextureRegion(new Texture("img/enemy_red_dead.png"));
	
	public RedEnemy(float x, float y) {
		super(x, y);
		setAnimations(new AnimationController(new String[] {"img/enemy_red.png"}, new int[] {1}, new int[] {6}, 0.07f));
	}

	@Override
	public void draw(SpriteBatch batch) {
		setStateTime(getStateTime() + Gdx.graphics.getDeltaTime());
		
		if (!isDead()) {
			setCurrentFrame(getAnimations().getAnimation(0).getKeyFrame(getStateTime()));
			batch.draw(getCurrentFrame(), getX(), getY(), getWidth(), getHeight());
			getDynamicBody().setLinearVelocity(-2.8f, 0.0f);
		} else {
			setCurrentFrame(deathImage);
		}
		if (WorldManager.GAME_OVER) {
			getDynamicBody().setActive(false);
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		deathImage.getTexture().dispose();
	}
}
