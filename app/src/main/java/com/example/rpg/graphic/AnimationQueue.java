package com.example.rpg.graphic;

import android.content.res.Resources;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;

import java.util.LinkedList;
import java.util.Queue;

public class AnimationQueue {
    private Queue<AnimationTask> queue = new LinkedList<>();
    private boolean isAnimating = false;

    public void enqueue(AnimationTask task) {
        queue.add(task);
        if (!isAnimating) {
            startNext();
        }
    }

    private void startNext() {
        if (queue.isEmpty()) {
            isAnimating = false;
            return;
        }
        isAnimating = true;
        AnimationTask task = queue.poll();
        task.start(this::startNext);
    }
}