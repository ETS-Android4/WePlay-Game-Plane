package com.weplay.game.widget;

import android.graphics.Bitmap;

/**
 * 大敌机类，体积大，抗打击能力强
 */
public class BigEnemyPlane extends EnemyPlane {

    public BigEnemyPlane(Bitmap bitmap) {
        super(bitmap);
        setPower(10);//大敌机抗抵抗能力为10，即需要10颗子弹才能销毁大敌机
        setValue(300);//销毁一个大敌机可以得300分
    }

    @Override
    public void explode(GameView gameView) {
        super.explode(gameView);
        if (gameView.greatMomentCallback != null)
            gameView.greatMomentCallback.onGreatMoment();
    }
}