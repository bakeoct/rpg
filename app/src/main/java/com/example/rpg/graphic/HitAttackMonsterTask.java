package com.example.rpg.graphic;

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

public class HitAttackMonsterTask implements AnimationTask{

    Monster2 monster;
    ImageView effect;
    FrameLayout frame_layout_player;
    FrameLayout frame_layout_monster;
    Resources resources;
    public int image_switching_number = 0;

    public Skill the_skill_of = new Skill();
    public final Handler handler = new Handler();
    public HitAttackMonsterTask(Monster2 monster, ImageView effect, FrameLayout frame_layout_player, FrameLayout frame_layout_monster, Resources resources) {
        this.monster = monster;
        this.effect = effect;
        this.frame_layout_player = frame_layout_player;
        this.frame_layout_monster = frame_layout_monster;
        this.resources = resources;
        the_skill_of.effect_drawable = monster.use_skill.effect_drawable;
    }

    @Override
    public void start(Runnable onComplete) {
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_monster.addView(effect);

        if (image_switching_number >= the_skill_of.effect_drawable.length) {
            frame_layout_player.removeAllViews();
            frame_layout_monster.removeAllViews();
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            onComplete.run(); // アニメーションが完了したことを通知
            return;
        }

        Bitmap effect_img = BitmapFactory.decodeResource(resources, the_skill_of.effect_drawable[image_switching_number]);
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        Bitmap bitmap = Bitmap.createBitmap(effect_img, 0, 0, effect_img.getWidth(), effect_img.getHeight(), matrix, false);
        effect.setImageBitmap(bitmap);
        image_switching_number++;

        handler.postDelayed(() -> {
            start(onComplete); // 次のフレームを実行
        }, 300);
    }
}