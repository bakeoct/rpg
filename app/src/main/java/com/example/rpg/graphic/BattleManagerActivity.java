package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.widget.ProgressBar;
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
    public ImageView effect = null;
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
        FrameLayout frame_layout_monster_power_up = findViewById(R.id.effectLayoutMonsterPowerUp);
        ProgressBar player_hp_ber = findViewById(R.id.player_hp);
        ProgressBar player_mp_ber = findViewById(R.id.player_mp);
        ProgressBar monster_hp_ber = findViewById(R.id.monster_hp);
        ProgressBar monster_mp_ber = findViewById(R.id.monster_mp);
        TextView player_hp_word = findViewById(R.id.player_hp_word);
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
                game.battle_manager.choose_skill(game.p.monsters2.get(my_side_monster_number),battle_chat,monster_of_player,enemy_monster,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,frame_layout_player_power_up,frame_layout_monster_power_up);
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
    public void finishBattle(){
        monster_cara_now = null;
        enemey_monster.randomNewEnemeyMonster();
        startActivity(new Intent(BattleManagerActivity.this,TransitionActivity.class));
        transition_activity = save_transition_activity;
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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
    public void finishEnemyMonster(ImageView enemy_monster,AnimationQueue queue){
        queue.enqueue(new MonsterTask(enemy_monster));
    }
    public void finishAllyMonster(ImageView monster_of_player,AnimationQueue queue){
        queue.enqueue(new PlayerTask(monster_of_player));
    }
}
