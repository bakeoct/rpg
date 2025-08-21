package com.example.rpg.Calc.controlView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class JoyStickView extends View {

    public JoyStickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private float centerX, centerY; // 外円の中心
    private float baseRadius, hatRadius;
    private float touchX, touchY;

    public interface JoystickListener {
        void onJoystickMoved(float xPercent, float yPercent,MotionEvent event);
    }

    private JoystickListener joystickListener = null;

    public void setJoystickListener(JoystickListener l) {
        joystickListener = l;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        // ビューの中心をスティックの基準にする
        centerX = w / 2f;
        centerY = h / 2f;
        baseRadius = Math.min(w, h) / 3f;
        hatRadius = Math.min(w, h) / 6f;

        touchX = centerX;
        touchY = centerY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        // 外円
        paint.setARGB(255, 200, 200, 200);
        canvas.drawCircle(centerX, centerY, baseRadius, paint);

        // 内円
        paint.setARGB(255, 100, 100, 150);
        canvas.drawCircle(touchX, touchY, hatRadius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float dx = event.getX() - centerX;
        float dy = event.getY() - centerY;
        double dist = Math.sqrt(dx * dx + dy * dy);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (dist < baseRadius) {
                    touchX = event.getX();
                    touchY = event.getY();
                } else {
                    // 外円を超えたら制限
                    touchX = (float) (centerX + dx / dist * baseRadius);
                    touchY = (float) (centerY + dy / dist * baseRadius);
                }

                invalidate();

                if (joystickListener != null) {
                    float xPercent = (touchX - centerX) / baseRadius;
                    float yPercent = (touchY - centerY) / baseRadius;
                    joystickListener.onJoystickMoved(xPercent, yPercent,event);
                }
                break;

            case MotionEvent.ACTION_UP:
                // 中心に戻す
                touchX = centerX;
                touchY = centerY;
                invalidate();

                if (joystickListener != null) {
                    joystickListener.onJoystickMoved(0, 0,event);
                }
                break;
        }
        return true;
    }
}
