package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.R;

public class BattleManagerActivity extends AppCompatActivity {
    public boolean finish_battle = false;
    public static BattleManagerActivity battle_manager_activity = new BattleManagerActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_manager);

        int my_side_monster_number = 0;

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
                battle_chat.removeAllViews();
                game.battle_manager.choose_skill(game.p.monsters2.get(my_side_monster_number),battle_chat);
                if (game.get_enemey_monster.hp<=0){
                    for (Monster2 monsters : game.p.monsters2) {
                        monsters.have_experince_point += game.get_enemey_monster.can_get_experince_point;
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
                game.battle_manager.useItem(game.p.monsters2.get(my_side_monster_number),battle_chat);
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
        if (game.p.monsters2.get(my_side_monster_number).name == "dragon_king"){
            drawable = getResources().getDrawable(R.drawable.dragon_king_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name == "metal_slime"){
            drawable = getResources().getDrawable(R.drawable.metal_slime_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name == "gorlem"){
            drawable = getResources().getDrawable(R.drawable.gorlem_right);
        }else if (game.p.monsters2.get(my_side_monster_number).name == "puti_slime"){
            drawable = getResources().getDrawable(R.drawable.puti_slime_right);
        }
        return drawable;
    }
    private Drawable enemyMonsterDrawable(){
        Drawable drawable = null;
        if (game.get_enemey_monster.name == "dragon_king"){
            drawable = getResources().getDrawable(R.drawable.dragon_king_left);
        }else if (game.get_enemey_monster.name == "metal_slime"){
            drawable = getResources().getDrawable(R.drawable.metal_slime_left);
        }else if (game.get_enemey_monster.name == "gorlem"){
            drawable = getResources().getDrawable(R.drawable.gorlem_left);
        }else if (game.get_enemey_monster.name == "puti_slime"){
            drawable = getResources().getDrawable(R.drawable.puti_slime_left);
        }
        return drawable;
    }
    private void finishBattle(){
        if (finish_battle) {
            monster_cara_now = null;
            game.get_enemey_monster = getMonsterRandomly(save_transition_activity.findViewById(R.id.enemy_monster));
            enemey_monster.randomNewEnemeyMonster();
            startActivity(new Intent(BattleManagerActivity.this,TransitionActivity.class));
            transition_activity = save_transition_activity;
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    }
    public void meetEnemyMonster(Activity activity){
        if (game.battle_manager.meet_enemy_monster){
            transition_activity = battle_manager_activity;
            save_transition_activity = activity;
            game.battle_manager.meet_enemy_monster = false;
            startActivity(new Intent(activity.getApplicationContext(),TransitionActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }
    }
    public void graphicHitAttack(ImageView monster_of_player,Monster2 monster){
        if (game.get_enemey_monster == monster) {
        }else {
            int attack_margin = getResources().getDimensionPixelSize(R.dimen.attack_of_monster_margin);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.addRule(RelativeLayout.RIGHT_OF, monster_of_player.getId());
            layoutParams.leftMargin = attack_margin;
            ImageView effect = new ImageView(battle_manager_activity);
            effect.setLayoutParams(layoutParams);
            effect.setImageDrawable(game.hit_attack.effect_drawable.get(0));
            new Handler().postDelayed(() -> {
                effect.setImageDrawable(game.hit_attack.effect_drawable.get());
            },500);
        }
    }
    public void graphicDie(LinearLayout battle_chat, ImageView monster_of_player){

    }
    public void graphicShortageMp(LinearLayout battle_chat, ImageView monster_of_player){

    }
}
