package com.jakebarnby.runner.utils;

/**
 * Interface for resolving actions required for using google play game services
 * @author Jake Barnby
 *
 * 5 February 2015
 */
public interface ActionResolver {

	/**
	 * Get whether the user is currently signed in to google play game services
	 * @return Whether the user is currently signed in to google play game services
	 */
	public boolean getSignedInGPGS();
	
	/**
	 * Log the user in to google play game services
	 */
	public void loginGPGS();
	
	/**
	 * Submit the given score to the google play game services leaderboard
	 * @param score The score to submit to the leaderboard
	 */
	public void submitScoreGPGS(int score);
	
	/**
	 * Unlock the google play game services achievement with the given id
	 */
	public void unlockAchievementGPGS(String achievementId);
	
	/**
	 * Get and show a google play game services leaderboard
	 */
	public void getLeaderboardGPGS();
	
	/**
	 * Get and show google play game services achievements
	 */
	public void getAchievementGPGS();
	
	/**
	 * Show an interstitial ad on exit
	 */
	public void showInterstitial();
}
