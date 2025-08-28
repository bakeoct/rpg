package com.example.rpg.sound;
import android.app.Activity;
import android.media.MediaPlayer;

import com.example.rpg.R;

import java.util.ArrayList;

public class MediaPlayerManager {
    public ArrayList<MediaPlayer> audio = new ArrayList<>();
    public static MediaPlayer media_player = null;

    public void playMusic(Activity activity,int music){
        media_player.stop();
        media_player.release();
        media_player = MediaPlayer.create(activity, music);
        media_player.start();
    }
}