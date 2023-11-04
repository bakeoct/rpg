package com.example.rpg.graphic;

import static com.example.rpg.Calc.BattleManager.meet_enemy_monster;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Game.get_enemey_monster;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.Person2.p;
import static com.example.rpg.Calc.Game.mission_dragon_king;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class BattleManagerActivity extends AppCompatActivity {
    public boolean finish_battle = false;
    public static BattleManagerActivity battle_manager_activity = new BattleManagerActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_manager);

        int my_side_monster_number = 0;

        ImageView player = findViewById(R.id.player);
        ImageView monster_of_player = findViewById(R.id.monster_of_player);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView fight_button = findViewById(R.id.fight_button);
        ImageView item_button = findViewById(R.id.item_button);
        ImageView run_button = findViewById(R.id.run_button);
        LinearLayout battle_chat = findViewById(R.id.battle_chat);

        TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setText("戦いだ！");
        battle_chat.addView(textView);
        monster_of_player.setImageDrawable(mySideMonsterDrawable(my_side_monster_number));
        enemy_monster.setImageDrawable(enemyMonsterDrawable());

        fight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.turnBattle(get_enemey_monster,mission_dragon_king);
            }
        });
        item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        run_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private Drawable mySideMonsterDrawable(int my_side_monster_number){
        Drawable drawable = null;
        if (game.store.p.monsters2.get(my_side_monster_number).name == "dragon_king"){
            drawable = getResources().getDrawable(R.drawable.dragon_king_right);
        }else if (game.store.p.monsters2.get(my_side_monster_number).name == "metal_slime"){
            drawable = getResources().getDrawable(R.drawable.metal_slime_right);
        }else if (game.store.p.monsters2.get(my_side_monster_number).name == "gorlem"){
            drawable = getResources().getDrawable(R.drawable.gorlem_right);
        }else if (game.store.p.monsters2.get(my_side_monster_number).name == "puti_slime"){
            drawable = getResources().getDrawable(R.drawable.puti_slime_right);
        }
        return drawable;
    }
    private Drawable enemyMonsterDrawable(){
        Drawable drawable = null;
        if (get_enemey_monster.name == "dragon_king"){
            drawable = getResources().getDrawable(R.drawable.dragon_king_left);
        }else if (get_enemey_monster.name == "metal_slime"){
            drawable = getResources().getDrawable(R.drawable.metal_slime_left);
        }else if (get_enemey_monster.name == "gorlem"){
            drawable = getResources().getDrawable(R.drawable.gorlem_left);
        }else if (get_enemey_monster.name == "puti_slime"){
            drawable = getResources().getDrawable(R.drawable.puti_slime_left);
        }
        return drawable;
    }
    private void finishBattle(){
        if (finish_battle) {
            monster_cara_now = null;
            get_enemey_monster = getMonsterRandomly(save_transition_activity.findViewById(R.id.enemy_monster));
            enemey_monster.randomNewEnemeyMonster();
            startActivity(new Intent(BattleManagerActivity.this,TransitionActivity.class));
            transition_activity = save_transition_activity;
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    }
    public void meetEnemyMonster(Activity activity){
        if (meet_enemy_monster){
            transition_activity = battle_manager_activity;
            save_transition_activity = activity;
            meet_enemy_monster = false;
            startActivity(new Intent(activity.getApplicationContext(),TransitionActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }
    }
}
