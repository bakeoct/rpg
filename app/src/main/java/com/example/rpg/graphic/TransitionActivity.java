package com.example.rpg.graphic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;


public class TransitionActivity extends AppCompatActivity {
    public static TransitionActivity transition_activity = new TransitionActivity();
    public Activity save_transition_activity;
    public Activity to_activity;
    public Activity from_activity; //どのアクティビティから来ましたか？ってこと
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        }
        @Override
        protected void onResume() {
            super.onResume();

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(this, transition_activity.to_activity.getClass());
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();  // 必要に応じてtransition_activity.to_activity.getClass()を終了させる
            }, 1000);  // 1秒後に遷移
    }
}
