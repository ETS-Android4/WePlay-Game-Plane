package com.weplay.game;

import android.app.Activity;
import android.os.Bundle;

import com.weplay.WePlay;
import com.weplay.ad.AdType;
import com.weplay.game.widget.GameView;
import com.weplay.message.WePlayUser;


public class GameActivity extends Activity {
    private GameView gameView;
    private String adId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        /*
        WePlay.sendScore(long score)
        上报分数
        */
        gameView.setOnScoreCallback(WePlay::sendScore);
        /*
        WePlay.greatMoment()
        精彩时刻(当前时刻回退15s的录屏内容)
        */
        gameView.setGreatMomentCallback(WePlay::greatMoment);
        gameView.setAdCallback(new GameView.OnShowAdCallback() {
            @Override
            public void showInterstitialAd() {
                /*
                 * 展示插屏广告
                 * */
                WePlay.showAd(AdType.TYPE_INTERSTITIAL, null);
            }

            @Override
            public void showRewardedVideoAd() {
                adId = String.valueOf(System.currentTimeMillis());
                /*
                 * 展示激励广告
                 * 自定义adId，用户观看后会回调adId
                 * */
                WePlay.showAd(AdType.TYPE_REWARDED_VIDEO, adId);
            }
        });
        /*
         * 用户观看激励广告的回调
         * adID 广告Id
         * */
        WePlay.setOnAdCallback(adId -> {
            if (adId.equals(GameActivity.this.adId)) {
                GameActivity.this.adId = null;
                //give the gift
                gameView.addBomb();
            }
        });
        /*
         * 登陆与退出回调
         * */
        WePlay.setOnAccountCallback(new WePlay.OnAccountCallback() {
            /*
             * 登陆
             * WePlay主动下达用户资料，游戏收到资料，执行登陆
             * 如果接受该用户登陆 返回 true
             * */
            @Override
            public boolean onLogin(WePlayUser wePlayUser) {
                gameView.setUserName(wePlayUser.userName + "@WePlay");
                gameView.start();
                return true;
            }

            /*
             * 可以退出返回 true
             * */
            @Override
            public boolean onLogout() {
                gameView.setUserName(null);
                gameView.start();
                gameView.postInvalidate();
                return true;
            }
        });
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