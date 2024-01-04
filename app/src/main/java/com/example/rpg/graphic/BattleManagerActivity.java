package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BattleManagerActivity extends AppCompatActivity {
    public boolean finish_battle = false;
    public static BattleManagerActivity battle_manager_activity = new BattleManagerActivity();
    public int image_switching_number = 0;
    public ImageView effect = null;
    public int fire_effect_switching = 0;
    public Skill the_skill_of = new Skill();
    public final Handler handler = new Handler();
//    public final Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            System.out.println("nakamuraTestTest");
//                if (image_switching_number < the_skill_of.effect_drawable.length) {
//                    effect.setImageResource(the_skill_of.effect_drawable[image_switching_number]);
//                    image_switching_number++;
//                    effect.bringToFront();
//                    handler.postDelayed(runnable, 1000); // 0.25秒間隔で実行
//                }
//                image_switching_number = 0;
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_manager);

        int attack_margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        int my_side_monster_number = 0;

        ImageView monster_of_player = findViewById(R.id.monster_of_player);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView fight_button = findViewById(R.id.fight_button);
        ImageView item_button = findViewById(R.id.item_button);
        ImageView run_button = findViewById(R.id.run_button);
        LinearLayout battle_chat = findViewById(R.id.battle_chat);
        FrameLayout frame_layout_throw = findViewById(R.id.effectLayoutThrow);
        Resources resources = getResources();
        FrameLayout frame_layout_player = findViewById(R.id.effectLayoutPlayer);
        FrameLayout frame_layout_monster = findViewById(R.id.effectLayoutMonster);
        FrameLayout frame_layout_player_power_up = findViewById(R.id.effectLayoutPlayerPowerUp);
        TextView battle_chat_text = new TextView(this);
        battle_chat_text.setTextColor(Color.RED);
        battle_chat_text.setText("戦いだ！");
        battle_chat.addView(battle_chat_text);
        monster_of_player.setImageDrawable(mySideMonsterDrawable(my_side_monster_number));
        enemy_monster.setImageDrawable(enemyMonsterDrawable());

        ImageView image_view_effect = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.addRule(RelativeLayout.RIGHT_OF, monster_of_player.getId());
        layoutParams.leftMargin = attack_margin;
        image_view_effect.setLayoutParams(layoutParams);
        effect = image_view_effect;

        for (Monster2 monster : game.p.monsters2){
            monster.display_skill.clear();
            for (Skill skill : monster.all_skill) {
                TextView text = new TextView(this);
                text.setTextColor(Color.RED);
                text.setText(skill.name + "  消費MP : " + skill.consumption_mp + "MP");
                System.out.println(text.getText());
                monster.display_skill.add(text);
            }
        }

        fight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battle_chat.removeAllViews();
                game.battle_manager.choose_skill(game.p.monsters2.get(my_side_monster_number),battle_chat,monster_of_player,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button);
                if (game.get_enemey_monster.hp<=0){
                    for (Monster2 monster : game.p.monsters2) {
                        monster.have_experince_point += game.get_enemey_monster.can_get_experince_point;
                    }
                    game.p.have_experince_point +=game.get_enemey_monster.can_get_experince_point;
                    game.level.upLevel(game.p);
                    if  (game.get_enemey_monster.name.equals("竜王") && game.mission_dragon_king.progress){
                        game.mission_sab.missionProgres(game.mission_dragon_king);
                        System.out.println(game.mission_dragon_king.name+"を達成した！");
                        //Storeで報酬を入手できる
                    }
                    finishBattle();
                }
            }
        });
        item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battle_chat.removeAllViews();
                game.battle_manager.useItem(game.p.monsters2.get(my_side_monster_number),battle_chat,battle_chat_text,frame_layout_player_power_up,effect,resources);
            }
        });
        run_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_battle = true;
                finishBattle();
            }
        });
    }
    private Drawable mySideMonsterDrawable(int my_side_monster_number){
        Drawable drawable = null;
        if (game.p.monsters2.get(my_side_monster_number).name.equals("竜王")){
            drawable = getResources().getDrawable(R.drawable.dragon_king_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name.equals("メタルスライム")){
            drawable = getResources().getDrawable(R.drawable.metal_slime_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name.equals("ゴーレム")){
            drawable = getResources().getDrawable(R.drawable.gorlem_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name.equals("プチスライム")){
            drawable = getResources().getDrawable(R.drawable.puti_slime_right);
        }
        return drawable;
    }
    private Drawable enemyMonsterDrawable(){
        Drawable drawable = null;
        if (game.get_enemey_monster.name.equals("竜王")){
            drawable = getResources().getDrawable(R.drawable.dragon_king_left);
        }else if (game.get_enemey_monster.name.equals("メタルスライム")){
            drawable = getResources().getDrawable(R.drawable.metal_slime_left);
        }else if (game.get_enemey_monster.name.equals("ゴ－レム")){
            drawable = getResources().getDrawable(R.drawable.gorlem_left);
        }else if (game.get_enemey_monster.name.equals("プチスライム")){
            drawable = getResources().getDrawable(R.drawable.puti_slime_left);
        }
        return drawable;
    }
    private void finishBattle(){
        if (finish_battle) {
            monster_cara_now = null;
            enemey_monster.randomNewEnemeyMonster();
            startActivity(new Intent(BattleManagerActivity.this,TransitionActivity.class));
            transition_activity = save_transition_activity;
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    }
    public void drawThrowAttack(Monster2 monster, ImageView effect,boolean player_first,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw){
        the_skill_of = monster.use_skill;
        ArrayList<FrameLayout> frame_throw_motion = new ArrayList<>();
        image_switching_number = 0;
        if (player_first) {
            frame_throw_motion.add(frame_layout_player);
            frame_throw_motion.add(frame_layout_throw);
            frame_throw_motion.add(frame_layout_monster);
            drawGraphicThrowAttackPlayer(effect,resources,frame_throw_motion);
        }else {
            frame_throw_motion.add(frame_layout_monster);
            frame_throw_motion.add(frame_layout_throw);
            frame_throw_motion.add(frame_layout_player);
            drawGraphicThrowAttackMonster(effect,resources,frame_throw_motion);
        }
    }
    public void drawGraphicThrowAttackPlayer(ImageView effect,Resources resources,ArrayList<FrameLayout> frame_throw_motion){
        if (image_switching_number >= the_skill_of.effect_drawable.length) {
            frame_throw_motion.get(image_switching_number-1).removeAllViews();
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));

            return;
        }
        if (image_switching_number > 0) {
            frame_throw_motion.get(image_switching_number-1).removeAllViews();
        }
        for (FrameLayout frameLayout : frame_throw_motion){
            frameLayout.removeAllViews();
        }
        frame_throw_motion.get(image_switching_number).addView(effect);
        System.out.println(effect);
        effect.setImageResource(the_skill_of.effect_drawable[image_switching_number]);
        image_switching_number++;
        handler.postDelayed(() -> {
            drawGraphicThrowAttackPlayer(effect, resources, frame_throw_motion);
            }, 300); // 0.25秒間隔で実行
    }
    public void drawGraphicThrowAttackMonster(ImageView effect,Resources resources,ArrayList<FrameLayout> frame_throw_motion){
        if (image_switching_number >= the_skill_of.effect_drawable.length) {
            frame_throw_motion.get(image_switching_number-1).removeAllViews();
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            return;
        }
        for (FrameLayout frameLayout : frame_throw_motion){
            frameLayout.removeAllViews();
        }
        frame_throw_motion.get(image_switching_number).addView(effect);
        Bitmap effect_img = BitmapFactory.decodeResource(resources,the_skill_of.effect_drawable[image_switching_number]);
        Matrix matrix = new Matrix();
        matrix.preScale(-1,1);
        Bitmap bitmap = Bitmap.createBitmap(effect_img,0,0,effect_img.getWidth(),effect_img.getHeight(),matrix,false);
        System.out.println(effect);
        effect.setImageBitmap(bitmap);
        image_switching_number++;
        handler.postDelayed(() -> {
            drawGraphicThrowAttackMonster(effect,resources,frame_throw_motion);
        }, 300); // 0.25秒間隔で実行
    }
    public void drawLittleFireAttack(Monster2 monster,ImageView effect,boolean player_first,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster){
        the_skill_of = monster.use_skill;
        image_switching_number = 0;
        if (player_first) {
            int default_frame_layout_player = (int) frame_layout_player.getX();
            drawGraphicLittleFireAttackPlayer(effect,frame_layout_player,frame_layout_monster,resources,default_frame_layout_player);
        }else {
            int default_frame_layout_monster = (int) frame_layout_monster.getX();
            drawGraphicLittleFireAttackMonster(effect,frame_layout_player,frame_layout_monster,resources,default_frame_layout_monster);
        }
    }
    //再帰
    public void drawGraphicLittleFireAttackPlayer(ImageView effect,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,Resources resources,int default_frame_layout_player){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_player.addView(effect);
        if (frame_layout_player.getX() >= frame_layout_monster.getX()) {
            frame_layout_player.setX(default_frame_layout_player);
            image_switching_number = 0;
            fire_effect_switching = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            return;
        }
        if (image_switching_number == 0){
            effect.setImageDrawable(resources.getDrawable(the_skill_of.effect_drawable[0]));
            fire_effect_switching = 1;
        }else if (image_switching_number % 100 == 0) {
            effect.setImageDrawable(resources.getDrawable(the_skill_of.effect_drawable[fire_effect_switching]));
            if (fire_effect_switching == 0) {
                fire_effect_switching = 1;
            } else {
                fire_effect_switching = 0;
            }
        }
        frame_layout_player.setX(frame_layout_player.getX()+5);
        effect.setX(effect.getX());
        image_switching_number++;
        handler.postDelayed(() -> {
            drawGraphicLittleFireAttackPlayer(effect,frame_layout_player,frame_layout_monster,resources,default_frame_layout_player);
        }, (long) 0.1); // 0.25秒間隔で実行
    }
    //再帰
    public void drawGraphicLittleFireAttackMonster(ImageView effect,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,Resources resources,int default_frame_layout_monster){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_monster.addView(effect);
        if (frame_layout_monster.getX() <= frame_layout_player.getX()) {
            frame_layout_monster.setX(default_frame_layout_monster);
            fire_effect_switching = 0;
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            return;
        }
        Matrix matrix = new Matrix();
        matrix.preScale(-1,1);
        if (image_switching_number == 0){
            Bitmap effect_img = BitmapFactory.decodeResource(resources,the_skill_of.effect_drawable[fire_effect_switching]);
            Bitmap bitmap = Bitmap.createBitmap(effect_img, (int)effect.getX(), (int)effect.getY(), effect_img.getWidth(), effect_img.getHeight(), matrix, false);
            effect.setImageBitmap(bitmap);
            fire_effect_switching = 1;
        }else if (image_switching_number % 100 == 0) {
            Bitmap effect_img = BitmapFactory.decodeResource(resources, the_skill_of.effect_drawable[fire_effect_switching]);
            Bitmap bitmap = Bitmap.createBitmap(effect_img, (int)effect.getX(), (int)effect.getY(), effect_img.getWidth(), effect_img.getHeight(), matrix, false);
            effect.setImageBitmap(bitmap);
            if (fire_effect_switching == 0) {
                fire_effect_switching = 1;
            } else {
                fire_effect_switching = 0;
            }
        }
        frame_layout_monster.setX(frame_layout_monster.getX()-5);
        image_switching_number++;
        handler.postDelayed(() -> {
            drawGraphicLittleFireAttackMonster(effect,frame_layout_player,frame_layout_monster,resources,default_frame_layout_monster);
        }, 300); // 0.25秒間隔で実行
    }
    public void drawHitAttack(Monster2 monster,ImageView effect,boolean player_first,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster){
        the_skill_of = monster.use_skill;
        image_switching_number = 0;
        // UIスレッドでUIを更新
        if (player_first) {
            drawGraphicHitAttackPlayer(effect, frame_layout_player, frame_layout_monster, resources);
        } else {
            drawGraphicHitAttackMonster(effect, frame_layout_player, frame_layout_monster, resources);
        }

    }
    //再帰
    public void drawGraphicHitAttackPlayer(ImageView effect,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,Resources resources){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_player.addView(effect);
        if (image_switching_number >= the_skill_of.effect_drawable.length) {
            frame_layout_player.removeAllViews();
            frame_layout_monster.removeAllViews();
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            return;
        }
        System.out.println(effect);
        effect.setImageResource(the_skill_of.effect_drawable[image_switching_number]);
        image_switching_number++;
        handler.postDelayed(() -> {
            drawGraphicHitAttackPlayer(effect,frame_layout_player,frame_layout_monster,resources);
        }, 300); // 0.25秒間隔で実行
    }
    //再帰
    public void drawGraphicHitAttackMonster(ImageView effect,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,Resources resources){
        frame_layout_player.removeAllViews();
        frame_layout_monster.removeAllViews();
        frame_layout_monster.addView(effect);
        if (image_switching_number >= the_skill_of.effect_drawable.length) {
            frame_layout_player.removeAllViews();
            frame_layout_monster.removeAllViews();
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            return;
        }
        Bitmap effect_img = BitmapFactory.decodeResource(resources,the_skill_of.effect_drawable[image_switching_number]);
        Matrix matrix = new Matrix();
        matrix.preScale(-1,1);
        Bitmap bitmap = Bitmap.createBitmap(effect_img,0,0,effect_img.getWidth(),effect_img.getHeight(),matrix,false);
        System.out.println(effect);
        effect.setImageBitmap(bitmap);
        image_switching_number++;
        handler.postDelayed(() -> {
            drawGraphicHitAttackMonster(effect,frame_layout_player,frame_layout_monster,resources);
        }, 300); // 0.25秒間隔で実行
    }
    public int graphic_skill(Monster2 monster2,LinearLayout battle_chat){
        battle_chat.removeAllViews();
        for (TextView textView : monster2.display_skill) {
            battle_chat.addView(textView);
        }
        return battle_chat.getChildCount();
    }

    public void graphicDie(LinearLayout battle_chat, ImageView monster_of_player){

    }
    public void graphicShortageMp(LinearLayout battle_chat, ImageView monster_of_player){

    }
    public void drawGraphicUsingItem(ImageView effect,FrameLayout layout,Resources resources){
        FightItem item = game.p.fight_items.get(game.p.choose_item);
        if (game.p.fight_items.get(game.p.choose_item).item_group.equals("heal")){
            effect.setAlpha(150);
            drawGraphicHealMotion(effect,layout,resources,item);
        }
    }
    public void drawGraphicHealMotion(ImageView effect,FrameLayout layout, Resources resources,FightItem item){
        layout.removeAllViews();
        layout.addView(effect);
        if (image_switching_number >= item.effect_drawable.length) {
            layout.removeAllViews();
            image_switching_number = 0;
            effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
            effect.setAlpha(255);
            return;
        }
        Bitmap effect_img = BitmapFactory.decodeResource(resources,item.effect_drawable[image_switching_number]);
        Matrix matrix = new Matrix();
        matrix.preScale(-1,1);
        Bitmap bitmap = Bitmap.createBitmap(effect_img,0,0,effect_img.getWidth(),effect_img.getHeight(),matrix,false);
        effect.setImageBitmap(bitmap);
        image_switching_number++;
        handler.postDelayed(() -> {
            drawGraphicHealMotion(effect,layout,resources,item);
        }, 250); // 0.25秒間隔で実行
    }
}
