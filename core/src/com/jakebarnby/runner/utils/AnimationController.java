package com.jakebarnby.runner.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 
 * @author Jake
 *
 */
public class AnimationController {
	private int[] rows;
	private int[] cols;
	
	private Animation[] anims;
	private Texture[] animSheets;
	private TextureRegion[][] frames;
	
	/**
	 * 
	 */
	public AnimationController(String[] sheets, int[] rows, int[] cols, float stateTime) {
		this.rows = rows;
		this.cols = cols;
		
		anims = new Animation[sheets.length];
		animSheets = new Texture[sheets.length];
		frames = new TextureRegion[sheets.length][];
		
		for (int i = 0; i < sheets.length; i++) {
			animSheets[i] = new Texture(Gdx.files.internal(sheets[i]));
			TextureRegion[][] tmp = TextureRegion.split(animSheets[i], animSheets[i].getWidth() / cols[i], animSheets[i].getHeight() / rows[i]);
			frames[i] = new TextureRegion[cols[i] * rows[i]];
			
			int index = 0;
			for (int j = 0; j < rows[i]; j++) {
				for (int k = 0; k < cols[i]; k++) {
					frames[i][index++] = tmp[j][k];
				}
			}
			anims[i] = new Animation(stateTime, frames[i]);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Animation getAnimation(int index) {
		return anims[index];
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public float getWidth(int index) {
		return animSheets[index].getWidth() / cols[index];
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public float getHeight(int index) {
		return animSheets[index].getHeight() / rows[index];
	}
}
