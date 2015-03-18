package com.jakebarnby.runner.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jakebarnby.runner.RunnerGame;

/**
 * A screen which displays a dialog instructing the user of how to play the game
 * @author Jake Barnby
 * 
 * 8 February 2015
 *
 */
public class HowToPlayScreen implements Screen {
	
	private Stage stage = new Stage(new StretchViewport(RunnerGame.WIDTH, RunnerGame.HEIGHT));


	@Override
	public void show() {
		//Creates the dialog and adds it to the stage
		HowToPlayDialog dialog = new HowToPlayDialog("How To Play", new Skin(						
				Gdx.files.internal("skins/menuSkin.json"),
				new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack"))));
		dialog.pack();
		dialog.setPosition(RunnerGame.WIDTH/2 - dialog.getWidth()/2, RunnerGame.HEIGHT/2 - dialog.getHeight()/2);
		stage.addActor(dialog);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(stage);
		
		stage.act();
		stage.draw();		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override
	public void pause() {}

	@Override
	public void resume() {}
	
	/**
	 * A dialog which contains instructions on how to play the game
	 * @author Jake Barnby
	 * 
	 * 8 February 2015
	 *
	 */

	public class HowToPlayDialog extends Dialog {
		
		//Textures needed to create images
		private Texture[] textures;
		
		//Images of droplets to display in the dialog
		private Image[] images;
		
		//Skin for labels added to content table
		private Skin skin;

		/**
		 * Creates a new dialog with instructions on how to play the game
		 * @param title
		 * @param skin
		 */
		public HowToPlayDialog(String title, Skin skin) {
			super(title, skin);
			this.skin = skin;
			
			button("Ok", "Ok");
			createContentTable();
		}
		
		/**
		 * Add images and descriptions of each drops function to the dialog
		 */
		private void createContentTable() {
			getContentTable().add("\n").row();
			getContentTable().add();
			getContentTable().add(new Label("Water drops must be caught", skin)).align(Align.left);
			getContentTable().row();
			
			getContentTable().add();
			getContentTable().add(new Label("Fire drops must not be caught", skin)).align(Align.left);
			getContentTable().row();
			
			getContentTable().add();
			getContentTable().add(new Label("Ice drops must not be caught", skin)).align(Align.left);
			getContentTable().row();
			
			getContentTable().add();
			getContentTable().add(new Label("Gold drops may be caught", skin)).align(Align.left);
			getContentTable().add("\n").row();
			getContentTable().add("\n").row();
			
			Label label = new Label("When a gold drop is caught,      \ngolden bucket mode is      \nactivated for 20 seconds      \nand any kind of drop      \nmay be caught.      \n", skin);
			label.setAlignment(Align.center);
			
			getContentTable().add("");
			getContentTable().add(label).align(Align.left);
			getContentTable().row();
			getContentTable().add("\n").row();
		}

		@Override 
		protected void result(Object object) {
			//User pressed ok
			if (((String)object).equals("Ok")) {
				dispose();
				((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		}
		
		/**
		 * Dispose of assets used when user leaves the dialog
		 */
		private void dispose() {
			textures[0].dispose();
			textures[1].dispose();
			textures[2].dispose();
			textures[3].dispose();
			skin.dispose();
		}
		
	}
	

}
