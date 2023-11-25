package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.Sound.OPEN_TREASURE_CHEST_AUDIO;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.treasure.TreasureChestLadder.treasure_chest_ladder;
import static com.example.rpg.graphic.GameActivity.game_activity;
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
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

import java.util.ArrayList;

public class BattleManagerActivity extends AppCompatActivity {
    public boolean finish_battle = false;
    public static BattleManagerActivity battle_manager_activity = new BattleManagerActivity();
    public int slash_number = 0;
    public ImageView effect = null;
    public final Handler handler = new Handler();
    public final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("0000000000000000000000000000000000000");
            if (slash_number == 0) {
                effect.setImageDrawable(hit_attack.effect_drawable.get(slash_number));
                handler.postDelayed(runnable, 250); // 0.25秒間隔で実行
            } else if (slash_number == 1) {
                effect.setImageDrawable(hit_attack.effect_drawable.get(slash_number));
                handler.postDelayed(runnable, 250); // 0.25秒間隔で実行
            } else if (slash_number == 2) {
                effect.setImageDrawable(hit_attack.effect_drawable.get(slash_number));
            }
        }
    };
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
                monster.display_skill.add(text);
            }
        }

        fight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battle_chat.removeAllViews();
                game.battle_manager.choose_skill(game.p.monsters2.get(my_side_monster_number),battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
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
        }else if (game.get_enemey_monster.name.equals("ゴーレム")){
            drawable = getResources().getDrawable(R.drawable.gorlem_left);
        }else if (game.get_enemey_monster.name.equals("プチスライム")){
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
    public void graphicHitAttack(ImageView monster_of_player,Monster2 monster,int attack_margin,ImageView effect){
        if (game.get_enemey_monster != monster) {
            handler.post(runnable);
        }
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
}
