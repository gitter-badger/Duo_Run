package com.teamkarbon.android.duo_run;

/**
 * Created by Joe on 25/11/2014.
 */

public class GoogleServices implements derptest.IGoogleServices {
    private static GoogleServices ourInstance = new GoogleServices();

    public static GoogleServices getInstance() {
        return ourInstance;
    }

    public GoogleServices() {

    }

    @Override
    public void signIn() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public void rateGame() {

    }

    @Override
    public void submitScore(String id, long score) {

    }

    @Override
    public void showScores(String id) {

    }

    @Override
    public void submitNorAchievements(String id) {

    }

    @Override
    public void submitInAchievements(String id, int number) {

    }

    @Override
    public void showAchievements() {

    }

    @Override
    public boolean isSignedIn() {
        return false;
    }

    @Override
    public void onSignInSucceeded() {

    }
}
