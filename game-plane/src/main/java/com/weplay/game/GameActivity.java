package com.weplay.game;

import android.app.Activity;
import android.os.Bundle;

import com.weplay.WePlay;
import com.weplay.game.widget.GameView;
import com.weplay.message.WePlayUser;


public class GameActivity extends Activity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        //WePlay.sendScore(long score)
        gameView.setOnScoreCallback(WePlay::sendScore);
        WePlay.setOnAccountCallback(new WePlay.OnAccountCallback() {
            @Override
            public boolean onLogin(WePlayUser wePlayUser) {
                gameView.setUserName(wePlayUser.userName + "@WePlay");
                gameView.start();
                return true;
            }

            @Override
            public boolean onLogout() {
                gameView.setUserName(null);
                gameView.destroy();
                return true;
            }
        });
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