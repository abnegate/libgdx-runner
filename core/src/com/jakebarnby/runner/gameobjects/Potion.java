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

public class Potion extends Consumable {
	
	private Body body;

	public Potion() {
		setSprite(new Sprite(new Texture(Gdx.files.internal("img/potion.png"))));
		System.out.println("Potion spawned");
	}

	@Override
	public void createPhysics(World physicsWorld) {
		BodyDef bodyDef = new BodyDef();  
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set((float)RunnerGame.WIDTH / RunnerGame.PTM_RATIO + 1f, 1.2f);  
		

		// Create a body from the defintion and add it to the world
		body = physicsWorld.createBody(bodyDef);  
		// Create a polygon shape
		PolygonShape potionBox = new PolygonShape();  
		potionBox.setAsBox(getWidth()/2, getHeight()/2);
			
		body.createFixture(potionBox, 100.0f);
		potionBox.dispose();
		
		getFixture().setSensor(true);
	}
	

	@Override
	public void use() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (!isPickedUp()) {
			batch.draw(getSprite(), body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2, getWidth(), getHeight());
		} else {
			body.setActive(false);
		}
		
		if (!WorldManager.GAME_OVER) {
			getSprite().setPosition(body.getPosition().x, body.getPosition().y);
			body.setLinearVelocity(-3.1f, 0f);
		} else {
			body.setActive(false);
		}

	}
	
	@Override
	public void dispose() {
		super.dispose();
		body.destroyFixture(getFixture());
	}
	

	
	@Override
	public float getX() {
		return body.getPosition().x;
	}

	@Override
	public float getY() {
		return body.getPosition().y;
	}


	@Override
	public float getWidth() {
		return super.getSprite().getWidth() / RunnerGame.PTM_RATIO;
	}

	@Override
	public float getHeight() {
		return super.getSprite().getHeight() / RunnerGame.PTM_RATIO;
	}

	@Override
	public Fixture getFixture() {
		return body.getFixtureList().get(0);
	}
}
