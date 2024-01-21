package com.example.rpg.graphic;

import android.content.res.Resources;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

interface AnimationTask {
    int EFFECT_SPEED = 250;
    int INTERVAL = 1000;
    void start(Runnable onComplete);
    void hitEffect(Runnable onComplete);
    void throwEffect(Runnable onComplete);
    void littleFireEffect(Runnable onComplete);
    void shortageMpEffect(Runnable onComplete);
    void damageEffect(Runnable onComplete);
    void dieEffect(Runnable onComplete);
}