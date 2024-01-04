package com.example.rpg.graphic;

import android.content.res.Resources;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.os.Handler;

import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import java.util.ArrayList;

public class HitAttackPlayerTask implements AnimationTask{
    Monster2 monster;
    ImageView effect;
    FrameLayout frame_layout_player;
    FrameLayout frame_layout_monster;
    Resources resources;
    public int image_switching_number = 0;

    public Skill the_skill_of = new Skill();
    public final Handler handler = new Handler();
     public HitAttackPlayerTask(Monster2 monster, ImageView effect, FrameLayout frame_layout_player, FrameLayout frame_layout_monster, Resources resources) {
        this.monster = monster;
        this.effect = effect;
        this.frame_layout_player = frame_layout_player;
        this.frame_layout_monster = frame_layout_monster;
        this.resources = resources;
        the_skill_of.effect_drawable = monster.use_skill.effect_drawable;
    }

    @Override
    public void start(Runnable onComplete) {
         if(monster.use_skill == hit_attack || monster.use_skill == little_fire) {
             hitEffect(onComplete);
         } else if(monster.use_skill == throw_attack) {
             throwEffect(onComplete);
        }
    }

    private void hitEffect(Runnable onComplete) {

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
                onComplete.run(); // アニメーションが完了したことを通知
            } else {
                effect.setImageResource(the_skill_of.effect_drawable[image_switching_number]);
                image_switching_number++;
                start(onComplete); // 次のフレームを実行
            }
        }, 300);
    }

    private void throwEffect(Runnable onComplete) {
        ArrayList<FrameLayout> frame_throw_motion = new ArrayList<>();
        frame_throw_motion.add(frame_layout_player);
        frame_throw_motion.add(frame_layout_monster);
        frame_throw_motion.add(frame_layout_monster);
        frame_throw_motion.add(frame_layout_monster);
        frame_throw_motion.add(frame_layout_monster);

        handler.postDelayed(() -> {
            if (image_switching_number >= the_skill_of.effect_drawable.length) {
                frame_layout_player.removeAllViews();
                frame_layout_monster.removeAllViews();
                image_switching_number = 0;
                effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
                onComplete.run(); // アニメーションが完了したことを通知
            } else {
                frame_layout_player.removeAllViews();
                frame_layout_monster.removeAllViews();

                if (image_switching_number > 0) {
                    frame_throw_motion.get(image_switching_number-1).removeAllViews();
                }
                for (FrameLayout frameLayout : frame_throw_motion){
                    frameLayout.removeAllViews();
                }
                frame_throw_motion.get(image_switching_number).addView(effect);
                effect.setImageResource(the_skill_of.effect_drawable[image_switching_number]);
                image_switching_number++;
                start(onComplete); // 次のフレームを実行
            }
        }, 300);
    }
}