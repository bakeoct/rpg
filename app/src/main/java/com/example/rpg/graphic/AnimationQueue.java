package com.example.rpg.graphic;

import java.util.LinkedList;
import java.util.Queue;

public class AnimationQueue {
    public Queue<AnimationTask> queue = new LinkedList<>();
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