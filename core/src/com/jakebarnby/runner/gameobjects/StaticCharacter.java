package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jakebarnby.runner.RunnerGame;

public class StaticCharacter implements Drawable {
	private Body kinematicBody;

	private Sprite sprite;

	public StaticCharacter(Sprite sprite, float x, float y) {
		this.sprite = sprite;
	}

	@Override
	public void draw(SpriteBatch batch) {
	}

	@Override
	public void dispose() {
		getSprite().getTexture().dispose();
		getKinematicBody().destroyFixture(getFixture());
		getKinematicBody().getWorld().destroyBody(getKinematicBody());
	}

	@Override
	public void createPhysics(World physicsWorld) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX() , sprite.getY());
		
		setKinematicBody(physicsWorld.createBody(bodyDef));

		PolygonShape groundBox = new PolygonShape();  
		groundBox.setAsBox(getWidth()/2, getHeight()/2);
 
		getKinematicBody().createFixture(groundBox, 2400f); 
		groundBox.dispose();
		
		getKinematicBody().setUserData(sprite);
		getKinematicBody().getFixtureList().get(0).setFriction(0);
		getKinematicBody().setFixedRotation(true);
	}

	@Override
	public float getX() {
		return sprite.getX();
	}
	
	@Override
	public float getY() {
		return sprite.getY();
	}

	@Override
	public float getHeight() {
		return sprite.getHeight() / RunnerGame.PTM_RATIO;
	}
	
	@Override
	public float getWidth() {
		return sprite.getWidth() / RunnerGame.PTM_RATIO;
	}

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
	public Body getKinematicBody() {
		return kinematicBody;
	}

	/**
	 * 
	 * @param kinematicBody
	 */
	public void setKinematicBody(Body kinematicBody) {
		this.kinematicBody = kinematicBody;
	}

	/**
	 * 
	 * @return
	 */
	public Fixture getFixture() {
		return kinematicBody.getFixtureList().get(0);
	}
}
