package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jakebarnby.runner.RunnerGame;
import com.jakebarnby.runner.WorldManager;
/**
 * 
 * @author Jake
 *
 */
public class Platform implements Drawable {
	private Body kinematicBody;
	
	private Sprite platform = new Sprite(new Texture(Gdx.files.internal("img/platform.png")));
	
	public Platform() {
		platform.setX(RunnerGame.WIDTH/RunnerGame.PTM_RATIO + getWidth());
		platform.setY(1.46f);
	}

	@Override
	public void createPhysics(World physicsWorld) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(platform.getX() , platform.getY());
		
		kinematicBody = physicsWorld.createBody(bodyDef);

		PolygonShape groundBox = new PolygonShape();  
		groundBox.setAsBox(getWidth()/2, getHeight()/2);
 
		kinematicBody.createFixture(groundBox, 2400f); 
		groundBox.dispose();
		
		kinematicBody.setUserData(platform);
		kinematicBody.getFixtureList().get(0).setFriction(0);
		kinematicBody.setFixedRotation(true);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(platform, kinematicBody.getPosition().x - getWidth()/2, kinematicBody.getPosition().y - getHeight()/2, getWidth(), getHeight());
		
		if (!WorldManager.GAME_OVER) {
			platform.setPosition(kinematicBody.getPosition().x, kinematicBody.getPosition().y);
			kinematicBody.setLinearVelocity(-3.15f, 0f);
		} else {
			kinematicBody.setActive(false);
		}
	}

	@Override
	public void dispose() {
		platform.getTexture().dispose();
		kinematicBody.destroyFixture(getFixture());
		kinematicBody.getWorld().destroyBody(kinematicBody);
	}
	
	/**
	 * Get the X value of this platform
	 * @return The X value of this platform
	 */
	public float getX() {
		return platform.getX();
	}
	
	/**
	 * 
	 * @return
	 */
	public float getY() {
		return platform.getY();
	}

	/**
	 * 
	 * @return
	 */
	public float getHeight() {
		return platform.getHeight() / RunnerGame.PTM_RATIO;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getWidth() {
		return platform.getWidth() / RunnerGame.PTM_RATIO;
	}
	
	/**
	 * 
	 * @return
	 */
	public Fixture getFixture() {
		return kinematicBody.getFixtureList().get(0);
	}

}
