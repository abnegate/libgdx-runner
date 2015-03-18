package com.jakebarnby.runner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jakebarnby.runner.RunnerGame;
import com.jakebarnby.runner.WorldManager;

public class GameScreen implements Screen {
	
	private Stage stage = new Stage(new StretchViewport(RunnerGame.WIDTH / RunnerGame.PTM_RATIO, RunnerGame.HEIGHT / RunnerGame.PTM_RATIO));
	private WorldManager worldManager = new WorldManager((SpriteBatch) stage.getBatch());

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// Clear screen and set color to blue
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		worldManager.draw();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		worldManager.dispose();
	}

}
