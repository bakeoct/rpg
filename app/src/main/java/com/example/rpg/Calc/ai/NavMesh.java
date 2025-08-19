package com.example.rpg.Calc.ai;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.widget.ImageView;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;

import java.util.Random;

public class NavMesh {
    public Monster wandering(int which_monster) {
        Monster monster = from_activity.monster_on_map.get(which_monster);
        game.monster_manager.walk(monster);
        try {
            Thread.sleep(25); //0.1秒イベント中断。値が小さいほど、高速で連続する
        } catch (InterruptedException e) {
        }
        return monster;
    }

    public void tracking(Monster monster) {
        float dx = game.player.image.getX() - monster.image.getX();
        float dy = game.player.image.getY() - monster.image.getY();
        float len = distanceFromPlayer(game.player.image, monster.image);
        if (50f < len) {//この数値はモンスターをプレイヤーの手前で止めるもの
            direction(dx, dy, monster);
            monster.x += dx * monster.speed / len;
            monster.y += dy * monster.speed / len;
            monster.image.setX(monster.x);
            monster.image.setY(monster.y);
        }
        try {
            Thread.sleep(25); //0.1秒イベント中断。値が小さいほど、高速で連続する
        } catch (InterruptedException e) {
        }
    }

    private void direction(float dx, float dy, Monster monster) {
        String dire_x;
        String dire_y;
        if (game.player.x < monster.x) {
            dire_x = "left";
        } else {
            dire_x = "right";
        }
        if (game.player.y < monster.y) {
            dire_y = "over";
        } else {
            dire_y = "behind";
        }
        System.out.println(dire_x);
        System.out.println(dire_y);
        if (Math.abs(dx) < Math.abs(dy)) {

            switch (dire_y) {
                case "behind":
                    monster.image.setImageResource(monster.monster_drawable[0]);
                    break;
                case "over":
                    monster.image.setImageResource(monster.monster_drawable[3]);
                    break;
            }
        } else {

            switch (dire_x) {
                case "right":
                    monster.image.setImageResource(monster.monster_drawable[2]);
                    break;
                case "left":
                    monster.image.setImageResource(monster.monster_drawable[1]);
                    break;
            }
        }
    }

    public float distanceFromPlayer(ImageView player_image, ImageView monster_image) {
        return (float) Math.sqrt(
                (float) Math.pow(player_image.getX() - monster_image.getX(), 2)
                        + (float) Math.pow(player_image.getY() - monster_image.getY(), 2));
    }

    public String directionMonster() {
        Random random = new Random();
        int i = random.nextInt(4);
        String direction = null;
        switch (i) {
            case 0:
                direction = "right";
                break;
            case 1:
                direction = "left";
                break;
            case 2:
                direction = "under";
                break;
            case 3:
                direction = "over";
        }
        return direction;
    }

}
