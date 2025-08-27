package com.example.rpg.Calc.Entity;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Entity extends AppCompatActivity implements Serializable {
    public int mpx;//マップの一cellあたりのx座標
    public int mpy;//マップの一cellあたりのy座標
    public int serve_mpx;//マップの一cellあたりのひとつ前のx座標
    public int serve_mpy;//マップの一cellあたりのひとつ前のy座標
    public int world_x;//マップ上のx座標
    public int world_y;//マップ上のy座標
    public double hp;//モンスターとプレイヤーのhp両方とも兼ねる
    public int attack;//モンスターとプレイヤーのattack両方とも兼ねる
    public double limit_hp;
    public int limit_attack;
    public int speed;//エンティティの移動速度。これとActionクラスの34行目(プレイヤー)とか128行目(モンスター)との合算で移動速度が決定する
    public String direction = null;//エンティティがどの方向に移動するかを確認する
    public ImageView image = null;//エンティティの画像
}
