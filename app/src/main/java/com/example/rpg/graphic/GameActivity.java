package com.example.rpg.graphic;

import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.monster_place;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import static com.example.rpg.Calc.Sound.OPEN_DOOR_AUDIO;
import static com.example.rpg.Calc.Sound.startAudio;
import static com.example.rpg.Calc.map.PersonHome1.PERSON_HOME1_INITIAL_X;
import static com.example.rpg.Calc.map.PersonHome1.PERSON_HOME1_INITIAL_Y;
import static com.example.rpg.graphic.InventoryActivity.inventory_activity;
import static com.example.rpg.graphic.PeopleHomeActivity.people_home_1_activity;
import static com.example.rpg.Calc.map.World_map.world_map;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_Y;
import static com.example.rpg.Calc.map.cave.Cave1.cave1;
import static com.example.rpg.graphic.Cave1Activity.cave_1_activity;
import static com.example.rpg.graphic.StoreActivity.store_activity;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import com.example.rpg.R;

import java.io.Serializable;

public class GameActivity extends AppCompatActivity implements Serializable {
    public static String monster_cara_now = null;
    public static String place = "over";
    public static GameActivity game_activity = new GameActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        assignmentItemDrawable();
        int image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView right = findViewById(R.id.right_game);
        ImageView over = findViewById(R.id.over_game);
        ImageView under = findViewById(R.id.under_game);
        ImageView left = findViewById(R.id.left_game);
        ImageView setting = findViewById(R.id.setting_inventory);
        ImageView do_button = findViewById(R.id.do_button_game);
        GridLayout gridLayout = findViewById(R.id.gridLayout_game);
        ImageView inventory_button = findViewById(R.id.inventory_button_inventory);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView yuusya = findViewById(R.id.yuusya_game);
        GameActivity game_activity = new GameActivity();
        System.out.println(monster_place);
        game.get_enemey_monster = getMonsterRandomly(enemy_monster);
        String[][] map = world_map;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Drawable myImageDrawable = drawMap(map,i,j);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = image_size;
                layoutParams.height = image_size;
                layoutParams.setMargins(margin, margin, margin, margin);
                ImageView imageView = new ImageView(this);
                imageView.setImageDrawable(myImageDrawable);
                imageView.setLayoutParams(layoutParams);
                gridLayout.addView(imageView);
            }
        }
        gridLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println(gridLayout.getX());
                System.out.println(gridLayout.getY());
                if (enemey_monster.area.equals("メインマップ")) {
                    enemy_monster.setX(gridLayout.getX() + image_size * enemey_monster.x);
                    enemy_monster.setY(gridLayout.getY() + image_size * enemey_monster.y);
                }
                yuusya.setX(gridLayout.getX() + image_size * game.p.x);
                yuusya.setY(gridLayout.getY() + image_size * game.p.y);

                // このリスナーは一度だけ実行させたいので、直後でリスナーを削除する
                gridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "right";
                game.gameTurn(game_activity,gridLayout, enemy_monster,yuusya,image_size);
                battle_manager_activity.meetEnemyMonster(game_activity);
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "over";
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
                battle_manager_activity.meetEnemyMonster(game_activity);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "left";
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
                battle_manager_activity.meetEnemyMonster(game_activity);
            }
        });
        under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "under";
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
                battle_manager_activity.meetEnemyMonster(game_activity);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWriteAndRead.write();
                Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = new MainActivity();
            }
        });
        do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map[game.p.y][game.p.x].equals("store")){
                    goStore();
                }else if (map[game.p.y][game.p.x].equals("cave1")){
                    goCave_1();
                }else if (map[game.p.y][game.p.x].equals("people_home_1")){
                    goPeople_home_1();
                }
            }
        });
        inventory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = inventory_activity;
                save_transition_activity = game_activity;
            }
        });
    }
    public Drawable drawMap(String[][] map,int i,int j){
        Drawable myImageDrawable = null;
        if (map[i][j].equals("海")) {
            myImageDrawable = getResources().getDrawable(R.drawable.sea, null);
        } else if (map[i][j].equals("山")) {
            myImageDrawable = getResources().getDrawable(R.drawable.glass, null);
        } else if (map[i][j].equals("崖")) {
            myImageDrawable = getResources().getDrawable(R.drawable.jyari, null);
        } else if (map[i][j].equals("glass")) {
            myImageDrawable = getResources().getDrawable(R.drawable.glass, null);
        } else if (map[i][j].equals("errer")) {
            myImageDrawable = getResources().getDrawable(R.drawable.errerzone, null);
        } else if (map[i][j].equals("store")) {
            myImageDrawable = getResources().getDrawable(R.drawable.store, null);
        } else if (map[i][j].equals("stone")) {
            myImageDrawable = getResources().getDrawable(R.drawable.stone, null);
        } else if (map[i][j].equals("cave1")) {
            myImageDrawable = getResources().getDrawable(R.drawable.cave_entrance, null);
        } else if (map[i][j].equals("people_home_1")) {
            myImageDrawable = getResources().getDrawable(R.drawable.home, null);
        }
        return myImageDrawable;
    }
    public void goStore(){
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity = store_activity;
    }
    public void goCave_1(){
        game.p.area = "洞窟1";
        game.p.x = CAVE1_INITIAL_X;
        game.p.y = CAVE1_INITIAL_Y;
        game.p.serve_x = CAVE1_INITIAL_X;
        game.p.serve_y = CAVE1_INITIAL_Y;
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity = cave_1_activity;

    }
    public void goPeople_home_1(){
        game.p.area = "民家1";
        startAudio(OPEN_DOOR_AUDIO);
        game.p.x = PERSON_HOME1_INITIAL_X;
        game.p.y = PERSON_HOME1_INITIAL_Y;
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity = people_home_1_activity;
    }
    private void assignmentItemDrawable(){
        game.store.ladder.item_drawable = getResources().getDrawable(R.drawable.ladder);
        game.store.ship.item_drawable = getResources().getDrawable(R.drawable.ship);
        game.store.steel_armor.item_drawable = getResources().getDrawable(R.drawable.steel_armer);
        game.store.super_sword.item_drawable = getResources().getDrawable(R.drawable.super_sword);
        game.store.heal_glass.item_drawable = getResources().getDrawable(R.drawable.heal_glass);
        game.store.metal_slime_merchandise.item_drawable = getResources().getDrawable(R.drawable.metal_slime);
        game.store.puti_slime_merchandise.item_drawable = getResources().getDrawable(R.drawable.puti_slime);
        game.store.dragon_king_merchandise.item_drawable = getResources().getDrawable(R.drawable.dragon_king);
        game.store.gorlem_merchandise.item_drawable = getResources().getDrawable(R.drawable.gorlem);
        game.throw_attack.effect_drawable.add(getResources().getDrawable(R.drawable.throw_1));
        game.throw_attack.effect_drawable.add(getResources().getDrawable(R.drawable.throw_2));
        game.throw_attack.effect_drawable.add(getResources().getDrawable(R.drawable.throw_3));
        game.hit_attack.effect_drawable.add(getResources().getDrawable(R.drawable.slashing_1));
        game.hit_attack.effect_drawable.add(getResources().getDrawable(R.drawable.slashing_2));
        game.hit_attack.effect_drawable.add(getResources().getDrawable(R.drawable.slashing_3));
        game.little_fire.effect_drawable.add(getResources().getDrawable(R.drawable.fire_1));
        game.little_fire.effect_drawable.add(getResources().getDrawable(R.drawable.fire_2));
    }
}
