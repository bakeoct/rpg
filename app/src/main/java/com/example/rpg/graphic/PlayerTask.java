package com.example.rpg.graphic;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.effect.EffectContext;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.os.Handler;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;

import java.util.ArrayList;

public class PlayerTask implements AnimationTask{
    Monster2 monster;
    ImageView effect;
    FrameLayout frame_layout_player;
    FrameLayout frame_layout_monster;
    FrameLayout frame_layout_player_power_up;
    FrameLayout frame_layout_throw;
    Resources resources;
    FightItem item;
    int default_frame_layout_player;
    public int fire_effect_switching = 0;
    public int image_switching_number = 0;

    public Skill the_skill_of = new Skill();
    public final Handler handler = new Handler();
     public PlayerTask(Monster2 monster, ImageView effect, FrameLayout frame_layout_player, FrameLayout frame_layout_monster,FrameLayout frame_layout_throw, Resources resources) {
        this.monster = monster;
        this.effect = effect;
        this.frame_layout_player = frame_layout_player;
        this.frame_layout_monster = frame_layout_monster;
        this.resources = resources;
        the_skill_of.effect_drawable = monster.use_skill.effect_drawable;
        this.default_frame_layout_player = (int) frame_layout_player.getX();
        this.frame_layout_throw = frame_layout_throw;
    }
    public PlayerTask(ImageView effect,FrameLayout layout,Resources resources,FightItem item){
         this.frame_layout_player_power_up = layout;
         this.effect = effect;
         this.resources = resources;
         this.item = item;
    }

    @Override
    public void start(Runnable onComplete) {
         try {
             if (monster.use_skill == hit_attack) {
                 hitEffect(onComplete);
             } else if (monster.use_skill == throw_attack) {
                 throwEffect(onComplete);
             } else if (monster.use_skill == little_fire){
                 littleFireEffect(onComplete);
             }
         }catch (NullPointerException e){
             if (this.item.item_group.equals("heal")){
                 healEffect(onComplete);
             }
         }
    }
    @Override
    public void hitEffect(Runnable onComplete) {

        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_player.addView(effect);
        // ... アニメーションのロジック ...

        handler.postDelayed(() -> {
            if (image_switching_number >= the_skill_of.effect_drawable.length) {
                frame_layout_player.removeAllViews();
                frame_layout_monster.removeAllViews();
                image_switching_number = 0;
                effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                this.monster.use_skill = null;
                onComplete.run(); // アニメーションが完了したことを通知
            } else {
                effect.setImageResource(the_skill_of.effect_drawable[image_switching_number]);
                image_switching_number++;
                start(onComplete); // 次のフレームを実行
            }
        }, EFFECT_SPEED);
    }
    @Override
    public void littleFireEffect(Runnable onComplete){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_player.addView(effect);
        handler.postDelayed(() -> {
            if (frame_layout_player.getX() >= frame_layout_monster.getX()) {
                frame_layout_player.setX(default_frame_layout_player);
                image_switching_number = 0;
                fire_effect_switching = 0;
                this.monster.use_skill = null;
                effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                onComplete.run();
            }else {

                if (image_switching_number == 0) {
                    effect.setImageDrawable(resources.getDrawable(the_skill_of.effect_drawable[0]));
                    fire_effect_switching = 1;
                } else if (image_switching_number % 2 == 0) {
                    effect.setImageDrawable(resources.getDrawable(the_skill_of.effect_drawable[fire_effect_switching]));
                    if (fire_effect_switching == 0) {
                        fire_effect_switching = 1;
                    } else {
                        fire_effect_switching = 0;
                    }
                }
                frame_layout_player.setX(frame_layout_player.getX() + 100);
                effect.setX(effect.getX());
                image_switching_number++;
                start(onComplete);
            }
        },EFFECT_SPEED);
    }
    @Override
    public void throwEffect(Runnable onComplete) {
        ArrayList<FrameLayout> frame_throw_motion = new ArrayList<>();
        frame_throw_motion.add(frame_layout_player);
        frame_throw_motion.add(frame_layout_throw);
        frame_throw_motion.add(frame_layout_monster);

        handler.postDelayed(() -> {
            if (image_switching_number >= the_skill_of.effect_drawable.length) {
                for (FrameLayout frameLayout : frame_throw_motion){
                    frameLayout.removeAllViews();
                }
                image_switching_number = 0;
                effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                this.monster.use_skill = null;
                onComplete.run(); // アニメーションが完了したことを通知
            } else {
                for (FrameLayout frameLayout : frame_throw_motion){
                    frameLayout.removeAllViews();
                }
                frame_throw_motion.get(image_switching_number).addView(effect);
                effect.setImageResource(the_skill_of.effect_drawable[image_switching_number]);
                image_switching_number++;
                start(onComplete); // 次のフレームを実行
            }
        }, EFFECT_SPEED);
    }
    public void healEffect(Runnable onComplete){
        frame_layout_player_power_up.removeAllViews();
        frame_layout_player_power_up.addView(effect);
        handler.postDelayed(() -> {
            if (image_switching_number >= item.effect_drawable.length){
                frame_layout_player_power_up.removeAllViews();
                image_switching_number = 0;
                effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                effect.setAlpha(255);
                onComplete.run();
            }else {
                effect.setImageResource(item.effect_drawable[image_switching_number]);
                image_switching_number++;
                start(onComplete);
            }
        }, EFFECT_SPEED); // 0.25秒間隔で実行
    }
}