package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

import java.util.ArrayList;

public class Interval implements AnimationTask{

    Monster2 monster;
    ImageView effect;
    FrameLayout frame_layout_player;
    FrameLayout frame_layout_monster;
    FrameLayout frame_layout_throw;
    Resources resources;
    public int image_switching_number = 0;
    public Skill the_skill_of = new Skill();
    public final Handler handler = new Handler();
    public int fire_effect_switching = 0;
    int default_frame_layout_monster;
    public Interval(Monster2 monster, ImageView effect, FrameLayout frame_layout_player, FrameLayout frame_layout_monster,FrameLayout frame_layout_throw, Resources resources) {
        this.monster = monster;
        this.effect = effect;
        this.frame_layout_player = frame_layout_player;
        this.frame_layout_monster = frame_layout_monster;
        this.resources = resources;
        the_skill_of.effect_drawable = monster.use_skill.effect_drawable;
        this.frame_layout_throw = frame_layout_throw;
        this.default_frame_layout_monster = (int)frame_layout_monster.getX();
    }

    @Override
    public void start(Runnable onComplete) {
        handler.postDelayed(() -> {
        }, 10000);
    }
    @Override
    public void hitEffect(Runnable onComplete){
    }
    @Override
    public void throwEffect(Runnable onComplete){
    }
    @Override
    public void littleFireEffect(Runnable onComplete){
    }
}