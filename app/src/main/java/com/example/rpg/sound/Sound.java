package com.example.rpg.sound;

//import javax.sound.sampled.*;
import android.media.AudioAttributes;
import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Sound extends AppCompatActivity implements Serializable {
    public static Sound sound = new Sound();

    public void startSounds(String place, SoundPool sound_pool, ArrayList<Integer> audio) {
        int sound_to_play = sortingSounds(place,audio);
        try {
            sound_pool.release();
            sound_pool.play(sound_to_play, 9.0f, 9.0f, 0, 0, 1);
        }catch(NullPointerException e) {
            sound_pool.play(sound_to_play, 9.0f, 9.0f, 0, 0, 1);
        }
    }
    public int sortingSounds(String place, ArrayList<Integer> audio) {
        int sound_to_play = 0;
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
