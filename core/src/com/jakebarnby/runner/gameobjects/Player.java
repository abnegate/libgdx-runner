package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jakebarnby.runner.RunnerGame;
import com.jakebarnby.runner.utils.AnimationController;

public class Player implements Drawable {
	
	private Body dynamicBody;
	private Inventory inventory = new Inventory();
	private AnimationController anim = new AnimationController();
	
	private TextureRegion currentFrame;
	private int TEXTURE_SCALE = 4;

	private boolean dead = false;
	private boolean jumping = false;
	private boolean grounded = true;
	
	float stateTime = 0f;
	
	
	private static final float MAX_JUMP_HEIGHT = 1.7f;

	/**
	 * 
	 */
	public Player() {

	}

	@Override
	public void createPhysics(World physicsWorld) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(RunnerGame.WIDTH/2/RunnerGame.PTM_RATIO - getWidth()/2, 1.5f);
		
		dynamicBody = physicsWorld.createBody(bodyDef);
		// Create a polygon shape
		PolygonShape playerBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		playerBox.setAsBox(getWidth()/2, getHeight()/2);
		// Create a fixture from our polygon shape and add it to our ground body
		
		dynamicBody.createFixture(playerBox, 1020.0f); 
		// Clean up after ourselves
		playerBox.dispose();
		
		dynamicBody.setUserData(currentFrame);
		dynamicBody.setFixedRotation(true);
	}

	@Override
	public void draw(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		
		if (jumping) {
			currentFrame = anim.getJumpAnimation().getKeyFrame(stateTime, false);
			dynamicBody.applyLinearImpulse(0, 150, dynamicBody.getPosition().x, dynamicBody.getPosition().y, true);
			if (dynamicBody.getPosition().y > MAX_JUMP_HEIGHT) {
				jumping = false;
			}
		}
		
		else if (dead) {	
			currentFrame = anim.getDeathImage();
		}
		
		else if (grounded) {
			currentFrame = anim.getWalkAnimation().getKeyFrame(stateTime, true);
		}
		
		inventory.draw(batch);
		
		batch.draw(currentFrame, 
				dynamicBody.getPosition().x - (getWidth()/2), 
				dynamicBody.getPosition().y - (getHeight()/2),
				(float)(currentFrame.getRegionWidth() * TEXTURE_SCALE) / RunnerGame.PTM_RATIO,
				(float)(currentFrame.getRegionHeight() * TEXTURE_SCALE) / RunnerGame.PTM_RATIO
				);	
	}

	@Override
	public void dispose() {
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
	public float getX() {
		return (dynamicBody.getPosition().x - (getWidth()/2));
	}
	
	/**
	 * 
	 * @return
	 */
	public float getY() {
		return (dynamicBody.getPosition().y - (getHeight()/2));
	}
	
	/**
	 * 
	 * @return
	 */
	public float getWidth() {
		return (anim.getWidth() * TEXTURE_SCALE) / RunnerGame.PTM_RATIO;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getHeight() {
		return (anim.getHeight() * TEXTURE_SCALE) / RunnerGame.PTM_RATIO;
	}
	
	/**
	 * 
	 * @return
	 */
	public Fixture getFixture() {
		return dynamicBody.getFixtureList().get(0);
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
}
