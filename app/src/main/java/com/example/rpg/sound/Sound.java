package com.example.rpg.sound;

//import javax.sound.sampled.*;
import static com.example.rpg.Calc.Game.game;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.R;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Sound extends AppCompatActivity implements Serializable {

    public void startSounds(String tail_id) {
        MediaPlayer sound_to_play = sortingSounds(tail_id);
        sound_to_play.start();
    }
    public MediaPlayer sortingSounds(String tail_id) {
        MediaPlayer sound_to_play = null;
        if(tail_id.equals("stone")) {//Stone
            sound_to_play = game.mpm.audio.get(0);
        }else if(tail_id.equals("wood")) {
            sound_to_play = game.mpm.audio.get(1);
        }else if(tail_id.equals("ocean")) {
            sound_to_play = game.mpm.audio.get(2);
        }else if (tail_id.equals("grave")) {
                sound_to_play = game.mpm.audio.get(3);
        }else if(tail_id.equals("glass")) {
            sound_to_play = game.mpm.audio.get(4);
        } else if(equals("treasure_box")) {
            sound_to_play = game.mpm.audio.get(7);
        }
        return sound_to_play;
    }
}
