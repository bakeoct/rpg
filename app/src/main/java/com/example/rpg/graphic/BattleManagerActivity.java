package com.example.rpg.graphic;

import static com.example.rpg.Calc.BattleManager.setPercent;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Gorlem.gorlem;
import static com.example.rpg.Calc.Monsters.MetalSlime.metal_slime;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Monsters.EnemeyMonster;
import com.example.rpg.Calc.Monsters.Gorlem;
import com.example.rpg.Calc.Monsters.MetalSlime;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;
import com.example.rpg.sound.MediaPlayerManager;

import java.util.ArrayList;
import java.util.Queue;

public class BattleManagerActivity extends AppCompatActivity {
    public static BattleManagerActivity battle_manager_activity = new BattleManagerActivity();
    public ImageView effect = null;
    public static int my_side_monster_number = 0;
    public ArrayList<ImageView> game_over_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_manager);
        MediaPlayerManager.mediaPlayer.stop();
        MediaPlayerManager.mediaPlayer.release();
        MediaPlayerManager.mediaPlayer = MediaPlayer.create(this, R.raw.battlemusic);
        MediaPlayerManager.mediaPlayer.start();
        int attack_margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView monster_of_player = findViewById(R.id.monster_of_player);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView fight_button = findViewById(R.id.fight_button);
        ImageView item_button = findViewById(R.id.item_button);
        ImageView run_button = findViewById(R.id.run_button);
        ImageView finish_button = findViewById(R.id.finish_button);
        ImageView retry_button = findViewById(R.id.retry);
        ImageView game_over = findViewById(R.id.gameover);
        retry_button.setVisibility(View.GONE);
        game_over.setVisibility(View.GONE);
        game_over_list.add(game_over);
        game_over_list.add(retry_button);
        finish_button.setVisibility(View.GONE);
        LinearLayout battle_chat = findViewById(R.id.battle_chat);
        FrameLayout frame_layout_throw = findViewById(R.id.effectLayoutThrow);
        Resources resources = getResources();
        FrameLayout frame_layout_player = findViewById(R.id.effectLayoutPlayer);
        FrameLayout frame_layout_monster = findViewById(R.id.effectLayoutMonster);
        FrameLayout frame_layout_player_power_up = findViewById(R.id.effectLayoutPlayerPowerUp);
        FrameLayout frame_layout_monster_power_up = findViewById(R.id.effectLayoutMonsterPowerUp);
        ArrayList<ArrayList<ProgressBar>> ber_gauge = new ArrayList<>();
        ArrayList<ProgressBar> ally_ber = new ArrayList<>();
        ProgressBar player_hp_ber = findViewById(R.id.player_hp);
        ProgressBar player_mp_ber = findViewById(R.id.player_mp);
        ally_ber.add(player_hp_ber);
        ally_ber.add(player_mp_ber);
        ArrayList<ProgressBar> monster_ber = new ArrayList<>();
        ProgressBar monster_hp_ber = findViewById(R.id.monster_hp);
        ProgressBar monster_mp_ber = findViewById(R.id.monster_mp);
        monster_ber.add(monster_hp_ber);
        monster_ber.add(monster_mp_ber);
        ber_gauge.add(ally_ber);
        ber_gauge.add(monster_ber);
        ArrayList<ArrayList<TextView>> text_gauge = new ArrayList<>();
        ArrayList<TextView> ally_word = new ArrayList<>();
        TextView player_hp_word = findViewById(R.id.player_hp_word);
        TextView player_mp_word = findViewById(R.id.player_mp_word);
        ally_word.add(player_hp_word);
        ally_word.add(player_mp_word);
        ArrayList<TextView> monster_word = new ArrayList<>();
        TextView monster_hp_word = findViewById(R.id.monster_hp_word);
        TextView monster_mp_word = findViewById(R.id.monster_mp_word);
        monster_word.add(monster_hp_word);
        monster_word.add(monster_mp_word);
        text_gauge.add(ally_word);
        text_gauge.add(monster_word);
        TextView battle_chat_text = new TextView(this);
        battle_chat_text.setTextColor(Color.RED);
        battle_chat_text.setText("戦いだ！");
        battle_chat.addView(battle_chat_text);
        monster_of_player.setImageDrawable(mySideMonsterDrawable());
        enemy_monster.setImageDrawable(enemyMonsterDrawable());

        ber_gauge.get(0).get(0).setProgress((int)setPercent(game.p.monsters2.get(battle_manager_activity.my_side_monster_number).hp,game.p.monsters2.get(battle_manager_activity.my_side_monster_number).limit_hp));
        text_gauge.get(0).get(0).setText(game.p.monsters2.get(battle_manager_activity.my_side_monster_number).hp+"/"+game.p.monsters2.get(battle_manager_activity.my_side_monster_number).limit_hp);
        ber_gauge.get(0).get(1).setProgress((int)setPercent(game.p.monsters2.get(battle_manager_activity.my_side_monster_number).mp,game.p.monsters2.get(battle_manager_activity.my_side_monster_number).limit_mp));
        text_gauge.get(0).get(1).setText(game.p.monsters2.get(battle_manager_activity.my_side_monster_number).mp+"/"+game.p.monsters2.get(battle_manager_activity.my_side_monster_number).limit_mp);
        ber_gauge.get(1).get(0).setProgress((int)setPercent(game.get_enemey_monster.hp,game.get_enemey_monster.limit_hp));
        text_gauge.get(1).get(0).setText(game.get_enemey_monster.hp+"/"+game.get_enemey_monster.limit_hp);
        ber_gauge.get(1).get(1).setProgress((int)setPercent(game.get_enemey_monster.mp,game.get_enemey_monster.limit_mp));
        text_gauge.get(1).get(1).setText(game.get_enemey_monster.mp+"/"+game.get_enemey_monster.limit_mp);

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
                game.battle_manager.choose_skill(game.p.monsters2.get(my_side_monster_number),battle_chat,monster_of_player,enemy_monster,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_player_power_up,frame_layout_monster_power_up,ber_gauge,text_gauge);
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
                finishBattle();
            }
        });
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishBattle();
            }
        });
        retry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryApp();
            }
        });
    }
    private Drawable mySideMonsterDrawable(){
        Drawable drawable = null;
        if (game.p.monsters2.get(my_side_monster_number).name.equals("竜王")){
            drawable = getResources().getDrawable(R.drawable.dragon_king_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name.equals("メタルスライム")){
            drawable = getResources().getDrawable(R.drawable.metal_slime_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name.equals("ゴ－レム")){
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
    public void finishBattle(){
        if (my_side_monster_number < game.p.monsters2.size()) {
            monster_cara_now = null;
            enemey_monster.randomNewEnemeyMonster();
            startActivity(new Intent(BattleManagerActivity.this, TransitionActivity.class));
            transition_activity = save_transition_activity;
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }else {
            for (ImageView view : game_over_list){
                MediaPlayerManager.mediaPlayer.stop();
                MediaPlayerManager.mediaPlayer.release();
                MediaPlayerManager.mediaPlayer = MediaPlayer.create(this, R.raw.futta_gameover);
                MediaPlayerManager.mediaPlayer.start();
                view.setVisibility(View.VISIBLE);
                view.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_fadein));
            }
        }
    }
    public int graphic_skill(Monster2 monster2,LinearLayout battle_chat){
        battle_chat.removeAllViews();
        for (TextView textView : monster2.display_skill) {
            battle_chat.addView(textView);
        }
        return battle_chat.getChildCount();
    }
    public void drawGraphicUsingItem(ImageView effect,FrameLayout layout,Resources resources){
        AnimationQueue queue = new AnimationQueue();
        FightItem item = game.p.fight_items.get(game.p.choose_item);
        if (game.p.fight_items.get(game.p.choose_item).item_group.equals("heal")){
            effect.setAlpha(150);
            queue.enqueue(new PlayerTask(effect,layout,resources,item));
        }
    }
    private void retryApp(){
        game.p.monsters2.clear();
        metal_slime = new MetalSlime();
        gorlem = new Gorlem();
        enemey_monster.area = "メインマップ";
        enemey_monster.monster_serve_x = 12;
        enemey_monster.monster_serve_y = 4;
        enemey_monster.x = 12;
        enemey_monster.y = 4;
        enemey_monster.monster_place = "over";
        game.p.monsters2.add(metal_slime);
        game.p.monsters2.add(gorlem);
        game.p.items.clear();
        game.p.lv = 1;
        game.p.field_items.clear();
        game.p.fight_items.clear();
        game.p.monster_items.clear();
        game.p.x = 12;
        game.p.y = 6;
        game.p.serve_x = 12;
        game.p.serve_y = 6;
        game.p.have_item = null;
        game.p.area = "メインマップ";
        game.p.choose_item = 0;
        game.p.money = 100;
        game.p.need_experience_point = 100;
        game.p.have_experience_point = 0;
        monster_cara_now = null;
        my_side_monster_number = 0;
        startActivity(new Intent(BattleManagerActivity.this, TransitionActivity.class));
        transition_activity = new MainActivity();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
