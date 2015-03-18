package com.jakebarnby.runner.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jakebarnby.runner.RunnerGame;
import com.jakebarnby.runner.WorldManager;

/**
 * Renders the background and displays it on the screen
 * @author Jake Barnby
 * 
 * 4 March 2015
 *
 */
public class Background implements Drawable {
	
	private Sprite ground1 = new Sprite(new Texture(Gdx.files.internal("img/ground.png")));
	private Sprite back1 = new Sprite(new Texture(Gdx.files.internal("img/background.png")));
	private Sprite ground2 = new Sprite(ground1);
	private Sprite back2 = new Sprite(back1);
	private Body groundBody;
	private float moveSpeed = 0.07f;
	
	/**
	 * Creates a new background
	 */
	public Background() {
		//Set background image X values
		ground1.setX(0);
		ground2.setX(ground1.getWidth() / RunnerGame.PTM_RATIO);
		back1.setX(0);
		back2.setX(back1.getWidth() / RunnerGame.PTM_RATIO);
		
		//Set background image Y values
		ground1.setY(0);
		ground2.setY(0);
		back1.setY(RunnerGame.HEIGHT / RunnerGame.PTM_RATIO - back1.getHeight() / RunnerGame.PTM_RATIO);
		back2.setY(RunnerGame.HEIGHT / RunnerGame.PTM_RATIO - back2.getHeight() / RunnerGame.PTM_RATIO);
		
		System.out.println(getHeight());
	}
	
	@Override
	public void createPhysics(World physicsWorld) {
		BodyDef groundBodyDef =new BodyDef();  
		groundBodyDef.position.set(new Vector2(0f, 0f));  

		// Create a body from the defintion and add it to the world
		groundBody = physicsWorld.createBody(groundBodyDef);  

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();  
		groundBox.setAsBox(RunnerGame.WIDTH / RunnerGame.PTM_RATIO + 3,ground1.getHeight() / RunnerGame.PTM_RATIO);
		groundBody.createFixture(groundBox, 0.0f); 
		groundBox.dispose();
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(back1, back1.getX(), back1.getY(), back1.getWidth() / RunnerGame.PTM_RATIO, back1.getHeight() / RunnerGame.PTM_RATIO);
		batch.draw(back2, back2.getX(), back2.getY(), back2.getWidth() / RunnerGame.PTM_RATIO, back2.getHeight() / RunnerGame.PTM_RATIO);
		batch.draw(ground1, ground1.getX(), ground1.getY(), ground1.getWidth() / RunnerGame.PTM_RATIO, ground1.getHeight() / RunnerGame.PTM_RATIO);
		batch.draw(ground2, ground2.getX(), ground2.getY(), ground2.getWidth() / RunnerGame.PTM_RATIO, ground2.getHeight() / RunnerGame.PTM_RATIO);
		
		//Move the background images 2 pixels to the left
		if (!WorldManager.GAME_OVER) {
			back1.translateX(-moveSpeed );
			back2.translateX(-moveSpeed);
			ground1.translateX(-moveSpeed);
			ground2.translateX(-moveSpeed);
		}
		
		//If the second background image is filling the screen, loop back to the first
		if (back2.getX() < 0) {
			back1.setX(0);
			back2.setX((back1.getX() + back1.getWidth()) / RunnerGame.PTM_RATIO);	
		}
		
		if (ground2.getX() < 0) {
			ground1.setX(0);
			ground2.setX((ground2.getX() + ground2.getWidth()) / RunnerGame.PTM_RATIO);
		}
	}
	
	@Override
	public void dispose() {
		ground1.getTexture().dispose();
		back1.getTexture().dispose();
		ground2.getTexture().dispose();
		back2.getTexture().dispose();
	}

	/**
	 * 
	 * @return
	 */
	public Fixture getFixture() {
		return groundBody.getFixtureList().get(0);
	}

	@Override
	public float getX() {
		return back2.getX() / RunnerGame.PTM_RATIO;
	}

	@Override
	public float getY() {
		return back1.getY() / RunnerGame.PTM_RATIO;
	}

	@Override
	public float getWidth() {
		return back1.getWidth() / RunnerGame.PTM_RATIO + back2.getWidth() / RunnerGame.PTM_RATIO;
	}
	
	@Override
	public float getHeight() {
		return ground1.getHeight() / RunnerGame.PTM_RATIO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
