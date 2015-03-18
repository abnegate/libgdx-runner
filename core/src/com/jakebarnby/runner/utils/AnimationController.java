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
	private static final int WALK_COLS = 3;
	private static final int WALK_ROWS = 1;
	private static final int JUMP_COLS = 2;
	private static final int JUMP_ROWS = 1;
	
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	
	private Animation jumpAnimation;
	private Texture jumpSheet;
	private TextureRegion[] jumpFrames;
	
	private TextureRegion deathImage;

	/**
	 * 
	 */
	public AnimationController() {
		createWalkAnim();
		creatJumpAnim();
		
		deathImage = new TextureRegion(new Texture(Gdx.files.internal("img/death_image.png")));
	}
	
	/**
	 * 
	 */
	private void createWalkAnim() {
		walkSheet = new Texture(Gdx.files.internal("img/walk_sheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,walkSheet.getWidth() / WALK_COLS, walkSheet.getHeight() / WALK_ROWS);
		walkFrames = new TextureRegion[WALK_COLS * WALK_ROWS];

		int index = 0;
		for (int i = 0; i < WALK_ROWS; i++) {
			for (int j = 0; j < WALK_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.08f, walkFrames);
	}

	/**
	 * 
	 */
	private void creatJumpAnim() {
		jumpSheet = new Texture(Gdx.files.internal("img/jump_sheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(jumpSheet, jumpSheet.getWidth() / JUMP_COLS, jumpSheet.getHeight() / JUMP_ROWS);
		jumpFrames = new TextureRegion[JUMP_COLS * JUMP_ROWS];

		int index = 0;
		for (int i = 0; i < JUMP_ROWS; i++) {
			for (int j = 0; j < JUMP_COLS; j++) {
				jumpFrames[index++] = tmp[i][j];
			}
		}
		jumpAnimation = new Animation(0.08f, walkFrames);
	}
	
	/**
	 * 
	 * @return
	 */
	public Animation getWalkAnimation() {
		return walkAnimation;
	}
	
	/**
	 * 
	 * @return
	 */
	public Animation getJumpAnimation() {
		return jumpAnimation;
	}
	
	/**
	 * 
	 * @return
	 */
	public TextureRegion getDeathImage() {
		return deathImage;
	}
	
	public float getWidth() {
		return walkSheet.getWidth() / WALK_COLS;
	}
	
	public float getHeight() {
		return walkSheet.getHeight() / WALK_ROWS;
	}
}
