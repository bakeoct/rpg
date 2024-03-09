package com.example.rpg.graphic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Store;
import com.example.rpg.R;

import java.util.function.Function;


public class TransitionActivity extends MainActivity {
    public static Activity save_transition_activity = null;
    public static Activity transition_activity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        }
        @Override
        protected void onResume() {
            super.onResume();

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(this, transition_activity.getClass());
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();  // 必要に応じてtransition_activity.getClass()を終了させる
            }, 1000);  // 1秒後に遷移
    }
}
