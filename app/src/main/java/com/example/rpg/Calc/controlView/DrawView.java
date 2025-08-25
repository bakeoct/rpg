package com.example.rpg.Calc.controlView;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.R;

import java.text.AttributedCharacterIterator;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private Thread gameThread;
    private boolean isPlaying;
    private SurfaceHolder holder;
    private Paint paint;
    private Bitmap[] tile_bitmaps;

    public DrawView(Context context, AttributeSet attr) {
        super(context, attr);
        holder = getHolder();
        holder.addCallback(this);
        paint = new Paint();
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!holder.getSurface().isValid()) {
                return;
            }
            while (isPlaying) {
                //マップの描画
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.BLACK);
                for (int i = 0; i < from_activity.map.length; i++) {
                    for (int j = 0; j < from_activity.map[i].length; j++) {
                        drawMap(canvas, i, j);
                    }
                }
                holder.unlockCanvasAndPost(canvas);
                //monsterの座標変更
                for (Monster monster : from_activity.monster_on_map) {
                    monster.image.setX(monster.world_x - game.player.world_x + game.player.screen_x);
                    monster.image.setY(monster.world_y - game.player.world_y + game.player.screen_y);
                }
            }
        }
    };

    private Bitmap arrangeScale(int D_id) {
        Bitmap original = BitmapFactory.decodeResource(getResources(), D_id);//下のマジックコードは隙間が出ないための調整↓
        return Bitmap.createScaledBitmap(original, game.image_size + 5, game.image_size + 5, false);
    }

    private void loadTileDrawable() {
        tile_bitmaps = new Bitmap[]{
                arrangeScale(R.drawable.cave_entrance),
                arrangeScale(R.drawable.cave_entrance),
                arrangeScale(R.drawable.door_wood_1),
                arrangeScale(R.drawable.errerzone),
                arrangeScale(R.drawable.glass),
                arrangeScale(R.drawable.cliff_on_glass),
                arrangeScale(R.drawable.store),
                arrangeScale(R.drawable.jyari),
                arrangeScale(R.drawable.sea),
                arrangeScale(R.drawable.stone),
                arrangeScale(R.drawable.cliff_on_jyari),
                arrangeScale(R.drawable.wood),
                arrangeScale(R.drawable.treasure_chest),

                // ... 他のタイル画像もここに登録
        };
    }

    public void drawMap(Canvas canvas, int i, int j) {
        int world_x = j * game.image_size;
        int world_y = i * game.image_size;
        int screen_x = world_x - game.player.world_x + game.player.screen_x;
        int screen_y = world_y - game.player.world_y + game.player.screen_y;
        if (screen_x + game.image_size < 0 || screen_x > getWidth()
                || screen_y + game.image_size < 0 || screen_y > getHeight()) {
            return;
        }
        canvas.drawBitmap(tile_bitmaps[from_activity.map[i][j].drawable], screen_x, screen_y, paint);
        from_activity.map[i][j].x_start = world_x;
        from_activity.map[i][j].x_end = world_x + game.image_size;
        from_activity.map[i][j].y_start = world_y;
        from_activity.map[i][j].y_end = world_y + game.image_size;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        isPlaying = true;
        loadTileDrawable();
        gameThread = new Thread(() -> {
            runnable.run();
        });
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
