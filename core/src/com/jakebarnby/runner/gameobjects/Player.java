package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.jakebarnby.runner.RunnerGame;
import com.jakebarnby.runner.utils.AnimationController;
/**
 * 
 * @author Jake
 *
 */
public class Player extends DynamicCharacter {
	private static final float MAX_JUMP_HEIGHT = 1.7f;

	private Inventory inventory = new Inventory();
	private TextureRegion deathImage = new TextureRegion(new Texture(Gdx.files.internal("img/death_image.png")));

	private boolean dead = false;
	private boolean jumping = false;
	private boolean grounded = true;

	/**
	 * 
	 */
	public Player(float x, float y) {
		super(x, y);
		setAnimations(new AnimationController(new String[] {"img/walk_sheet.png", "img/jump_sheet.png"}, new int[] {1, 1}, new int[] {3, 2}, 0.08f));
	}

	@Override
	public void draw(SpriteBatch batch) {
		setStateTime(getStateTime() + Gdx.graphics.getDeltaTime());
		
		if (jumping) {
			setCurrentFrame(getAnimations().getAnimation(1).getKeyFrame(getStateTime(), false));
			getDynamicBody().applyLinearImpulse(0, 150, getDynamicBody().getPosition().x, getDynamicBody().getPosition().y, true);
			if (getDynamicBody().getPosition().y > MAX_JUMP_HEIGHT) {
				jumping = false;
			}
		}
		else if (dead) {	
			setCurrentFrame(deathImage);
		}
		else if (grounded) {
			setCurrentFrame(getAnimations().getAnimation(0).getKeyFrame(getStateTime(), true));
		}
		inventory.draw(batch);
		batch.draw(getCurrentFrame(), 
				getDynamicBody().getPosition().x - (getWidth()/2), 
				getDynamicBody().getPosition().y - (getHeight()/2),
				(float)(getCurrentFrame().getRegionWidth() * TEXTURE_SCALE) / RunnerGame.PTM_RATIO,
				(float)(getCurrentFrame().getRegionHeight() * TEXTURE_SCALE) / RunnerGame.PTM_RATIO
				);	
	}

	@Override
	public void dispose() {
		super.dispose();
		deathImage.getTexture().dispose();
	}
	
	/**
	 * Return whether this player is currently jumping
	 * @return
	 */
	public boolean isJumping() {
		return jumping;
	}
	
	/**
	 * Make this player jump
	 */
	public void setJumping() {
		if (!jumping) {
			jumping = true;
			grounded = false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * 
	 * @param dead
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isGrounded() {
		return grounded;
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	/**
	 * 
	 * @return
	 */
	public Fixture getFixture() {
		return getDynamicBody().getFixtureList().get(0);
	}
}
