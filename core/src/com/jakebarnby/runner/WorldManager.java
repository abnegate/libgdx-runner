package com.jakebarnby.runner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.jakebarnby.runner.gameobjects.Background;
import com.jakebarnby.runner.gameobjects.Consumable;
import com.jakebarnby.runner.gameobjects.Enemy;
import com.jakebarnby.runner.gameobjects.Platform;
import com.jakebarnby.runner.gameobjects.Player;
import com.jakebarnby.runner.gameobjects.Potion;
import com.jakebarnby.runner.gameobjects.Score;

/**
 * Maintains the world
 * @author Jake Barnby
 * 
 * 4 March 2015
 */
public class WorldManager {
	public static boolean GAME_OVER = false;
	
	private World physicsWorld = new World(new Vector2(0.0f, -10.0f), true);
	private Box2DDebugRenderer physicsRenderder = new Box2DDebugRenderer();

	private Background background = new Background();
	private Player player = new Player(RunnerGame.WIDTH/2/RunnerGame.PTM_RATIO, 1.5f);
	private Score score;
	private Music music;

	private Array<Platform> platforms = new Array<Platform>();
	private Array<Enemy> enemies = new Array<Enemy>();
	private Array<Consumable> consumables = new Array<Consumable>();
	
	private SpriteBatch batch;
	
	private float spawnGap = 5.0f;
	private float runTime = 0.0f;
	
	/**
	 * 
	 */
	public WorldManager(SpriteBatch batch) {
		background.createPhysics(physicsWorld);
		player.createPhysics(physicsWorld);
		
		score = new Score(new BitmapFont(Gdx.files.internal("fonts/buttonfont.fnt"), Gdx.files.internal("fonts/buttonfont.png"), true),
				"Score:",
				0.5f,
				0.5f);
		
		if (RunnerGame.SOUND_ON) {
			music = Gdx.audio.newMusic(Gdx.files.internal("audio/game_music.wav"));
			music.setLooping(true);
			music.play();
		}
		
		this.batch = batch;
		physicsWorld.setContactListener(new MyContactListener());
		
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				player.setJumping();
				return true;
			}
		});
	
		Box2D.init();
	}

	/**
	 * Draw the world
	 */
	public void draw() {
		runTime += Gdx.graphics.getDeltaTime();
		if (runTime % 1 == 0f) {
			score.incrementScore();
		}
		
		if (MathUtils.random(200) == 0) {
			consumables.add(new Potion());
			consumables.get(consumables.size-1).createPhysics(physicsWorld);
		}
		
		spawnGap -= Gdx.graphics.getDeltaTime();
		if (spawnGap < 0 && !GAME_OVER) {
			platforms.add(new Platform());
			platforms.get(platforms.size-1).createPhysics(physicsWorld);
			spawnGap = MathUtils.random(1.0f, 4.0f);
		}
		
		
		
		batch.begin();
		background.draw(batch);
		for (int i = 0; i < platforms.size; i++) {
			platforms.get(i).draw(batch);
			
			if (platforms.get(i).getX() < -platforms.get(i).getWidth()) {
				platforms.get(i).dispose();
				platforms.removeIndex(i);
			}
		}
	/*	for (Consumable consumable: consumables) {
			consumable.draw(batch);
		}*/
		
		player.draw(batch);
		score.draw(batch);
		batch.end();
		
		physicsRenderder.render(physicsWorld, batch.getProjectionMatrix());
		physicsWorld.step(1/45f, 6, 2);
	}
	
	/**
	 * 
	 */
	public void dispose() {
		background.dispose();
		player.dispose();
		batch.dispose();
	}
	
	public Array<Platform> getPlatforms() {
		return platforms;
	}
	
	
	
	private class MyContactListener implements ContactListener {
		@Override
		public void beginContact(Contact contact) {
			if (contact.getFixtureA().equals(player.getFixture()) || contact.getFixtureB().equals(player.getFixture())) {
				
				for (Platform platform: platforms) {
					if (contact.getFixtureA().equals(platform.getFixture()) || contact.getFixtureB().equals(platform.getFixture())) {
						if (player.getY() < (background.getHeight() + platform.getHeight())) {
							GAME_OVER = true;
							player.setDead(true);
						} else {
							player.setGrounded(true);
						}
					} else {
						player.setGrounded(true);
					}
				}
			}
		}

		@Override
		public void endContact(Contact contact) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {

		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			// TODO Auto-generated method stub
			
		}
	}
}
