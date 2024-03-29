package com.example.rpg.graphic;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.effect.EffectContext;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

import static com.example.rpg.Calc.BattleManager.*;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.ShortageMP.shortage_mp;
import static com.example.rpg.Calc.skill.Throw.throw_attack;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import static com.example.rpg.graphic.BattleManagerActivity.my_side_monster_number;

import java.util.ArrayList;

public class PlayerTask implements AnimationTask{
    Monster2 monster;
    ImageView effect;
    ImageView fight_button;
    ImageView item_button;
    ImageView run_button;
    ImageView finish_button;
    FrameLayout frame_layout_player;
    FrameLayout frame_layout_monster;
    FrameLayout frame_layout_player_power_up;
    FrameLayout frame_layout_monster_power_up;
    FrameLayout frame_layout_throw;
    ImageView damage_monster;
    ImageView die_ally_monster;
    Resources resources;
    ArrayList<ArrayList<ProgressBar>> ber_gauge;
    ArrayList<ArrayList<TextView>> text_gauge;
    FightItem item;
    int default_rotation = 0;
    int default_frame_layout_player = 0;
    public int fire_effect_switching = 0;
    public int image_switching_number = 0;
    final int DAMAGE_ROTATION = 45;
    final int DIE_ROTATION = -90;

    public Skill the_skill_of = new Skill();
    public final Handler handler = new Handler();
     public PlayerTask(Monster2 monster, ImageView effect, FrameLayout frame_layout_player, FrameLayout frame_layout_monster, FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button,ImageView finish_button, ImageView damage_monster, FrameLayout frame_layout_monster_power_up, Resources resources, ImageView die_ally_monster, ArrayList<ArrayList<ProgressBar>> ber_gauge,ArrayList<ArrayList<TextView>> text_gauge) {
        this.monster = monster;
        this.effect = effect;
        this.damage_monster = damage_monster;
        this.frame_layout_player = frame_layout_player;
        this.frame_layout_monster = frame_layout_monster;
        this.resources = resources;
        this.fight_button = fight_button;
        this.item_button = item_button;
        this.run_button = run_button;
        this.finish_button = finish_button;
        the_skill_of.effect_drawable = monster.use_skill.effect_drawable;
        this.default_frame_layout_player = (int) frame_layout_player.getX();
        this.frame_layout_throw = frame_layout_throw;
        this.frame_layout_monster_power_up = frame_layout_monster_power_up;
        this.die_ally_monster = die_ally_monster;
        this.ber_gauge = ber_gauge;
        this.text_gauge = text_gauge;
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
             if (monster.hp > 0 || !player_die_effect) {
                 if (monster.mp >= monster.use_skill.consumption_mp) {
                     if (monster.use_skill == hit_attack) {
                         hitEffect(onComplete);
                     } else if (monster.use_skill == throw_attack) {
                         throwEffect(onComplete);
                     } else if (monster.use_skill == little_fire) {
                         littleFireEffect(onComplete);
                     }
                 } else {
                     shortageMpEffect(onComplete);
                 }
             } else {
                 dieEffect(onComplete);
             }
             } catch (NullPointerException e) {
                 healEffect(onComplete);
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
                damageEffect(onComplete);// ダメージエフェクトの表示
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
                frame_layout_player.removeAllViews();
                frame_layout_monster.removeAllViews();
                frame_layout_player.setX(default_frame_layout_player);
                image_switching_number = 0;
                fire_effect_switching = 0;
                this.monster.use_skill = null;
                effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                damageEffect(onComplete);
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
                damageEffect(onComplete);// ダメージエフェクトの表示
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
    @Override
    public void shortageMpEffect(Runnable onComplete){
         frame_layout_player.removeAllViews();
         frame_layout_monster.removeAllViews();
         frame_layout_player.addView(effect);
         handler.postDelayed(() -> {
             if (image_switching_number >= shortage_mp.effect_drawable.length){
                 frame_layout_player.removeAllViews();
                 image_switching_number = 0;
                 effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                 onComplete.run();
             }else {
                 effect.setImageResource(shortage_mp.effect_drawable[image_switching_number]);
                 image_switching_number++;
                 start(onComplete);
             }
         },EFFECT_SPEED);
    }
    @Override
    public void damageEffect(Runnable onComplete){
         default_rotation = (int)damage_monster.getRotation();
         damage_monster.setRotation(DAMAGE_ROTATION);
         frame_layout_monster_power_up.addView(effect);
        damage_monster.setImageDrawable(resources.getDrawable(game.get_enemey_monster.monster_drawable_damage_enemy[0]));
        Bitmap effect_img = BitmapFactory.decodeResource(resources, R.drawable.damage);
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        Bitmap bitmap = Bitmap.createBitmap(effect_img, 0, 0, effect_img.getWidth(), effect_img.getHeight(), matrix, false);
        effect.setImageBitmap(bitmap);
        ber_gauge.get(1).get(0).setProgress((int)setPercent(game.get_enemey_monster.hp,game.get_enemey_monster.limit_hp));
        text_gauge.get(1).get(0).setText(game.get_enemey_monster.hp+"/"+game.get_enemey_monster.limit_hp);
        ber_gauge.get(0).get(1).setProgress((int)setPercent(monster.mp,monster.limit_mp));
        text_gauge.get(0).get(1).setText(monster.mp+"/"+monster.limit_mp);
        handler.postDelayed(() -> {
            damage_monster.setRotation(default_rotation);
            default_rotation = 0;
            frame_layout_monster_power_up.removeAllViews();
            damage_monster.setImageDrawable(resources.getDrawable(game.get_enemey_monster.monster_drawable_usually[1]));
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            monster_die_effect = true;
            if (!player_first && game.get_enemey_monster.hp != 0) {
                fight_button.setVisibility(View.VISIBLE);
                item_button.setVisibility(View.VISIBLE);
                run_button.setVisibility(View.VISIBLE);
            }
            onComplete.run();
        },INTERVAL);
    }

    @Override
    public void dieEffect(Runnable onComplete) {
        default_rotation = (int)die_ally_monster.getRotation();
        die_ally_monster.setRotation(DIE_ROTATION);
        die_ally_monster.setImageDrawable(resources.getDrawable(monster.monster_drawable_damage_ally[0]));
        handler.postDelayed(() ->{
            if (my_side_monster_number < game.p.monsters2.size() - 1) {
                my_side_monster_number++;
                die_ally_monster.setRotation(default_rotation);
                default_rotation = 0;
                die_ally_monster.setImageDrawable(resources.getDrawable(game.p.monsters2.get(my_side_monster_number).monster_drawable_usually[2]));
                ber_gauge.get(0).get(0).setProgress((int)setPercent(game.p.monsters2.get(my_side_monster_number).hp,game.p.monsters2.get(my_side_monster_number).limit_hp));
                text_gauge.get(0).get(0).setText(game.p.monsters2.get(my_side_monster_number).hp+"/"+game.p.monsters2.get(my_side_monster_number).limit_hp);
                ber_gauge.get(0).get(1).setProgress((int)setPercent(game.p.monsters2.get(my_side_monster_number).mp,game.p.monsters2.get(my_side_monster_number).limit_mp));
                text_gauge.get(0).get(1).setText(game.p.monsters2.get(my_side_monster_number).mp+"/"+game.p.monsters2.get(my_side_monster_number).limit_mp);
                fight_button.setVisibility(View.VISIBLE);
                item_button.setVisibility(View.VISIBLE);
                run_button.setVisibility(View.VISIBLE);
            }else {
                my_side_monster_number++;
                fight_button.setVisibility(View.GONE);
                item_button.setVisibility(View.GONE);
                run_button.setVisibility(View.GONE);
                finish_button.setVisibility(View.VISIBLE);
            }
            player_die_effect = false;
        },INTERVAL * 2);
    }
}