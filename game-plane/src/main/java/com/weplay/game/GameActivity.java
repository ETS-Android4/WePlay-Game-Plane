package com.weplay.game;

import android.app.Activity;
import android.os.Bundle;

import com.weplay.game.widget.GameView;
import com.weplay.WePlay;


public class GameActivity extends Activity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        gameView = new GameView(this);
        setContentView(gameView);
        //WePlay.sendScore(long score)
        gameView.setOnScoreCallback(WePlay::sendScore);
        gameView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (gameView != null)
            gameView.resume();
    }

    @Override
    protected void onPause() {
        if (gameView != null)
            gameView.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (gameView != null)
            gameView.destroy();
        gameView = null;
        super.onDestroy();
    }
}