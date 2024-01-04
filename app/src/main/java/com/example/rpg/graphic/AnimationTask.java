package com.example.rpg.graphic;

import android.content.res.Resources;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

interface AnimationTask {
    void start(Runnable onComplete);
}