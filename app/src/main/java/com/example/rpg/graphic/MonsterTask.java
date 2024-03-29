package com.example.rpg.graphic;

import static com.example.rpg.Calc.BattleManager.*;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.ShortageMP.shortage_mp;
import static com.example.rpg.Calc.skill.Throw.throw_attack;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

import java.util.ArrayList;

public class MonsterTask implements AnimationTask{
    Monster2 monster;
    ImageView effect;
    FrameLayout frame_layout_player;
    FrameLayout frame_layout_monster;
    FrameLayout frame_layout_throw;
    Resources resources;
    ImageView fight_button;
    ImageView item_button;
    ImageView run_button;
    ImageView finish_button;
    ImageView monster_of_player;
    FrameLayout frame_layout_player_power_up;
    ImageView die_enemy_monster;
    ArrayList<ArrayList<ProgressBar>> ber_gauge;
    ArrayList<ArrayList<TextView>> text_gauge;
    int default_rotation = 0;
    public int image_switching_number = 0;
    public Skill the_skill_of = new Skill();
    public final Handler handler = new Handler();
    public int fire_effect_switching = 0;
    int default_frame_layout_monster = 0;
    final int DAMAGE_ROTATION = -45;
    final int DIE_ROTATION = 90;
    public MonsterTask(Monster2 monster, ImageView effect, FrameLayout frame_layout_player, FrameLayout frame_layout_monster, FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button,ImageView finish_button, ImageView monster_of_player, FrameLayout frame_layout_player_power_up, Resources resources, ImageView die_enemy_monster, ArrayList<ArrayList<ProgressBar>> ber_gauge, ArrayList<ArrayList<TextView>> text_gauge) {
        this.monster = monster;
        this.effect = effect;
        this.frame_layout_player = frame_layout_player;
        this.frame_layout_monster = frame_layout_monster;
        this.resources = resources;
        this.fight_button = fight_button;
        this.item_button = item_button;
        this.run_button = run_button;
        this.finish_button = finish_button;
        the_skill_of.effect_drawable = game.get_enemey_monster.use_skill.effect_drawable;
        this.frame_layout_throw = frame_layout_throw;
        this.default_frame_layout_monster = (int)frame_layout_monster.getX();
        this.monster_of_player = monster_of_player;
        this.frame_layout_player_power_up = frame_layout_player_power_up;
        this.die_enemy_monster = die_enemy_monster;
        this.ber_gauge = ber_gauge;
        this.text_gauge = text_gauge;
    }

    @Override
    public void start(Runnable onComplete) {
        if (game.get_enemey_monster.hp > 0 || !monster_die_effect) {
            if (game.get_enemey_monster.mp >= game.get_enemey_monster.use_skill.consumption_mp) {
                if (game.get_enemey_monster.use_skill == hit_attack) {
                    hitEffect(onComplete);
                } else if (game.get_enemey_monster.use_skill == throw_attack) {
                    throwEffect(onComplete);
                } else if (game.get_enemey_monster.use_skill == little_fire) {
                    littleFireEffect(onComplete);
                }
            } else {
                shortageMpEffect(onComplete);
            }
        }else {
            dieEffect(onComplete);
        }
    }

    @Override
    public void hitEffect(Runnable onComplete){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_monster.addView(effect);
        handler.postDelayed(() -> {
        if (image_switching_number >= the_skill_of.effect_drawable.length) {
            frame_layout_player.removeAllViews();
            frame_layout_monster.removeAllViews();
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            game.get_enemey_monster.use_skill = null;
            damageEffect(onComplete);// ダメージのエフェクトを表示
        }else {
            Bitmap effect_img = BitmapFactory.decodeResource(resources, the_skill_of.effect_drawable[image_switching_number]);
            Matrix matrix = new Matrix();
            matrix.preScale(-1, 1);
            Bitmap bitmap = Bitmap.createBitmap(effect_img, 0, 0, effect_img.getWidth(), effect_img.getHeight(), matrix, false);
            effect.setImageBitmap(bitmap);
            image_switching_number++;
            start(onComplete); // 次のフレームを実行
        }
        }, EFFECT_SPEED);
    }
    @Override
    public void throwEffect(Runnable onComplete){
        ArrayList<FrameLayout> frame_throw_motion = new ArrayList<>();
        frame_throw_motion.add(frame_layout_monster);
        frame_throw_motion.add(frame_layout_throw);
        frame_throw_motion.add(frame_layout_player);
        handler.postDelayed(() -> {
        if (image_switching_number >= the_skill_of.effect_drawable.length) {
            for (FrameLayout frameLayout : frame_throw_motion){
                frameLayout.removeAllViews();
            }
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            game.get_enemey_monster.use_skill = null;
            damageEffect(onComplete);
        }else {
            for (FrameLayout frameLayout : frame_throw_motion) {
                frameLayout.removeAllViews();
            }
            frame_throw_motion.get(image_switching_number).addView(effect);
            Bitmap effect_img = BitmapFactory.decodeResource(resources, the_skill_of.effect_drawable[image_switching_number]);
            Matrix matrix = new Matrix();
            matrix.preScale(-1, 1);
            Bitmap bitmap = Bitmap.createBitmap(effect_img, 0, 0, effect_img.getWidth(), effect_img.getHeight(), matrix, false);
            effect.setImageBitmap(bitmap);
            image_switching_number++;
            start(onComplete);
        }
        }, EFFECT_SPEED); // 0.25秒間隔で実行
    }
    @Override
    public void littleFireEffect(Runnable onComplete){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_monster.addView(effect);
        handler.postDelayed(() -> {
            if (frame_layout_monster.getX() <= frame_layout_player.getX()) {
                frame_layout_monster.setX(default_frame_layout_monster);
                image_switching_number = 0;
                fire_effect_switching = 0;
                game.get_enemey_monster.use_skill = null;
                effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                damageEffect(onComplete);
            }else {
                if (image_switching_number == 0) {
                    Bitmap effect_img = BitmapFactory.decodeResource(resources,the_skill_of.effect_drawable[0]);
                    Matrix matrix = new Matrix();
                    matrix.preScale(-1,1);
                    Bitmap bitmap = Bitmap.createBitmap(effect_img,0,0,effect_img.getWidth(),effect_img.getHeight(),matrix,false);
                    effect.setImageBitmap(bitmap);
                    fire_effect_switching = 1;
                } else if (image_switching_number % 2 == 0) {
                    Bitmap effect_img = BitmapFactory.decodeResource(resources,the_skill_of.effect_drawable[fire_effect_switching]);
                    Matrix matrix = new Matrix();
                    matrix.preScale(-1,1);
                    Bitmap bitmap = Bitmap.createBitmap(effect_img,0,0,effect_img.getWidth(),effect_img.getHeight(),matrix,false);
                    effect.setImageBitmap(bitmap);
                    if (fire_effect_switching == 0) {
                        fire_effect_switching = 1;
                    } else {
                        fire_effect_switching = 0;
                    }
                }
                frame_layout_monster.setX(frame_layout_monster.getX() - 100);
                image_switching_number++;
                start(onComplete);
            }
        },EFFECT_SPEED);
    }
    @Override
    public void shortageMpEffect(Runnable onComplete){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_monster.addView(effect);
        handler.postDelayed(() -> {
            if (image_switching_number >= shortage_mp.effect_drawable.length){
                frame_layout_monster.removeAllViews();
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
        default_rotation = (int)monster_of_player.getRotation();
        monster_of_player.setRotation(DAMAGE_ROTATION);
        frame_layout_player_power_up.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_player.removeAllViews();
        frame_layout_throw.removeAllViews();
        monster_of_player.setImageDrawable(resources.getDrawable(monster.monster_drawable_damage_ally[0]));
        frame_layout_player_power_up.addView(effect);
        effect.setImageDrawable(resources.getDrawable(R.drawable.damage));
        player_die_effect = true;
        ber_gauge.get(0).get(0).setProgress((int)setPercent(monster.hp,monster.limit_hp));
        text_gauge.get(0).get(0).setText(monster.hp+"/"+monster.limit_hp);
        ber_gauge.get(1).get(1).setProgress((int)setPercent(game.get_enemey_monster.mp,game.get_enemey_monster.limit_mp));
        text_gauge.get(1).get(1).setText(game.get_enemey_monster.mp+"/"+game.get_enemey_monster.limit_mp);
        handler.postDelayed(() -> {
            monster_of_player.setRotation(default_rotation);
            default_rotation = 0;
            monster_of_player.setImageDrawable(resources.getDrawable(monster.monster_drawable_usually[2]));
            frame_layout_player_power_up.removeAllViews();
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            if (player_first && monster.hp != 0) {
                fight_button.setVisibility(View.VISIBLE);
                item_button.setVisibility(View.VISIBLE);
                run_button.setVisibility(View.VISIBLE);
            }
            onComplete.run();
        },INTERVAL);
    }
    @Override
    public void dieEffect(Runnable onComplete) {
        die_enemy_monster.setRotation(DIE_ROTATION);
        die_enemy_monster.setImageDrawable(resources.getDrawable(game.get_enemey_monster.monster_drawable_damage_enemy[0]));
        handler.postDelayed(() ->{
            for (Monster2 monster : game.p.monsters2) {
                monster.have_experince_point += game.get_enemey_monster.can_get_experince_point;
            }
            game.p.have_experience_point +=game.get_enemey_monster.can_get_experince_point;
            game.level.upLevel(game.p);
            if  (game.get_enemey_monster.name.equals("竜王") && game.mission_dragon_king.progress){
                game.mission_sab.missionProgres(game.mission_dragon_king);
                System.out.println(game.mission_dragon_king.name+"を達成した！");
                //Storeで報酬を入手できる
            }
            fight_button.setVisibility(View.GONE);
            item_button.setVisibility(View.GONE);
            run_button.setVisibility(View.GONE);
            finish_button.setVisibility(View.VISIBLE);
        },INTERVAL * 2);
    }
}