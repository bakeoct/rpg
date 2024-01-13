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

public class MonsterTask implements AnimationTask{

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
    public MonsterTask(Monster2 monster, ImageView effect, FrameLayout frame_layout_player, FrameLayout frame_layout_monster,FrameLayout frame_layout_throw, Resources resources) {
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
        if (monster.use_skill == hit_attack) {
            hitEffect(onComplete);
        } else if (monster.use_skill == throw_attack) {
            throwEffect(onComplete);
        } else if (monster.use_skill == little_fire){
            littleFireEffect(onComplete);
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
            onComplete.run(); // アニメーションが完了したことを通知
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
            onComplete.run();
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
                onComplete.run();
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
}