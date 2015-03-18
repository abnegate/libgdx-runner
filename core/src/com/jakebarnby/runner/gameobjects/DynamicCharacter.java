package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jakebarnby.runner.RunnerGame;
import com.jakebarnby.runner.utils.AnimationController;

public class DynamicCharacter implements Drawable {
	public static final int TEXTURE_SCALE = 4;
	
	private AnimationController animations;
	private Body dynamicBody;
	
	private TextureRegion currentFrame;

	private float stateTime = 0f;
	private float x, y;
	
	public DynamicCharacter(float x, float y){
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(SpriteBatch batch) {
	}

	@Override
	public void dispose() {
		currentFrame.getTexture().dispose();
	}

	@Override
	public void createPhysics(World physicsWorld) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x - getWidth()/2, y - getHeight()/2);
		
		setDynamicBody(physicsWorld.createBody(bodyDef));

		PolygonShape playerBox = new PolygonShape();  
		playerBox.setAsBox(getWidth()/2, getHeight()/2);
		
		getDynamicBody().createFixture(playerBox, 1200.0f); 
		playerBox.dispose();
		
		getDynamicBody().setUserData(getCurrentFrame());
		getDynamicBody().setFixedRotation(true);
	}

	@Override
	public float getX() {
		return (getDynamicBody().getPosition().x - (getWidth()/2));
	}
	
	@Override
	public float getY() {
		return (getDynamicBody().getPosition().y - (getHeight()/2));
	}
	
	@Override
	public float getWidth() {
		return (getAnimations().getWidth(0)  * TEXTURE_SCALE) / RunnerGame.PTM_RATIO;
	}
	
	@Override
	public float getHeight() {
		return (getAnimations().getHeight(0) * TEXTURE_SCALE) / RunnerGame.PTM_RATIO;
	}

	/**
	 * 
	 * @return
	 */
	public AnimationController getAnimations() {
		return animations;
	}

	/**
	 * 
	 * @param animations
	 */
	public void setAnimations(AnimationController animations) {
		this.animations = animations;
	}

	/**
	 * 
	 * @return
	 */
	public Body getDynamicBody() {
		return dynamicBody;
	}

	/**
	 * 
	 * @param dynamicBody
	 */
	public void setDynamicBody(Body dynamicBody) {
		this.dynamicBody = dynamicBody;
	}

	/**
	 * 
	 * @return
	 */
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	/**
	 * 
	 * @param currentFrame
	 */
	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}

	/**
	 * 
	 * @return
	 */
	public float getStateTime() {
		return stateTime;
	}

	/**
	 * 
	 * @param stateTime
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
}
