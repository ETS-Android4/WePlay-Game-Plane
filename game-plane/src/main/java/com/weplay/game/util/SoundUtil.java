package com.weplay.game.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.weplay.game.R;

public class SoundUtil {
    private final Context context;
    private MediaPlayer mediaPlayer;

    private final SoundPool soundPool;
    //播放音量
    private final float volume=1;

    private final int sound_plane_shoot;
    private final int sound_plane_bomb;

    public SoundUtil(Context context) {
        this.context = context;
//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        //获取当前音量
//        float streamVolumeCurrent = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        //获取系统最大音量
//        float streamVolumeMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        //计算得到播放音量
//        volume = streamVolumeCurrent / streamVolumeMax;

        soundPool = new SoundPool(Integer.MAX_VALUE, AudioManager.STREAM_MUSIC, 100);//创建SoundPool对象
        sound_plane_shoot = soundPool.load(context, R.raw.plane_shoot, 1);
        sound_plane_bomb = soundPool.load(context, R.raw.plane_bomb, 1);
    }

    /**
     * 射击
     */
    public void playShootEffect() {
        soundPool.play(sound_plane_shoot, volume* 0.8f, volume* 0.8f, 1, 0, 1.0f);
    }

    /**
     * 爆炸
     */
    public void playBombEffect() {
        soundPool.play(sound_plane_bomb, volume, volume, 1, 0, 1.0f);
    }

    ///////////////////////////////////////<背景音效>///////////////////////////
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.plane_bg);
        mediaPlayer.setVolume(volume * 0.8f, volume * 0.8f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    int currentPosition;

    public void pause() {
        if (mediaPlayer == null) return;
        if (!mediaPlayer.isPlaying()) return;
        currentPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
    }

    public void resume() {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying()) return;
        mediaPlayer.seekTo(currentPosition);
        mediaPlayer.start();
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void release() {
        soundPool.release();
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
