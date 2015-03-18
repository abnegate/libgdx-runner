package com.jakebarnby.runner;

import com.badlogic.gdx.Game;
import com.jakebarnby.runner.screens.SplashScreen;
import com.jakebarnby.runner.utils.ActionResolver;

/**
 * Main entry point for runner game
 * @author Jake Barnby
 * 
 * 3 March 2015
 *
 */
public class RunnerGame extends Game {
	
	public static final int HEIGHT = 480;							//Global game height
	public static final int WIDTH = 800;							//Global game width
	public static final int PTM_RATIO = 100;
	public static boolean SOUND_ON = true;
	
	private ActionResolver actionResolver;							//Action resolver for platform specific code

	/**
	 * Create a new game instance with the given action resolver
	 * @param actionResolver The action resolver to use for this platform
	 */
	public RunnerGame(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
	}
	
	@Override
	public void create () {
		setScreen(new SplashScreen());
	}

	/**
	 * Get the action resolver for this platform
	 * @return The action resolver for this platform
	 */
	public ActionResolver getActionResolver() {
		return actionResolver;
	}
}
