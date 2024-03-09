package com.example.rpg.sound;

//import javax.sound.sampled.*;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;
import com.example.rpg.graphic.MainActivity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Sound extends MainActivity implements Serializable {
    public static Sound sound = new Sound();

    public void startSounds(String place, ArrayList<MediaPlayer> audio) {
        MediaPlayer sound_to_play = sortingSounds(place,audio);
        sound_to_play.start();
    }
    public MediaPlayer sortingSounds(String place, ArrayList<MediaPlayer> audio) {
        MediaPlayer sound_to_play = null;
        if(place.equals("stone")) {
            sound_to_play = audio.get(0);
        }else if(place.equals("wood")) {
            sound_to_play = audio.get(1);
        }else if(place.equals("海")) {
            sound_to_play = audio.get(2);
        }else if (place.equals("崖")) {
                sound_to_play = audio.get(3);
        }else if(place.equals("glass")) {
            sound_to_play = audio.get(4);
        }else if(place.equals("山")) {
            sound_to_play = audio.get(5);
        }else if(place.equals("door")) {
            sound_to_play = audio.get(6);
        }else if(place.equals("treasure_chest")) {
            sound_to_play = audio.get(7);
        }
        return sound_to_play;
    }
}
