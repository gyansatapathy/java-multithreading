package com.example.advanced;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class AdvancedProducer implements Runnable {
    private final BlockingQueue<String> queue;
    private final int itemsToProduce;
    private final String poisonPill;

    public AdvancedProducer(BlockingQueue<String> queue, int itemsToProduce, String poisonPill) {
        this.queue = queue;
        this.itemsToProduce = itemsToProduce;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < itemsToProduce; i++) {
                String id = "ID-" + Thread.currentThread().getName() + "-" + UUID.randomUUID().toString().substring(0, 8);
                queue.put(id);
                System.out.println(Thread.currentThread().getName() + " produced: " + id);
                Thread.sleep(50);
            }
            // Add poison pill to signal end of production
            queue.put(poisonPill);
            System.out.println(Thread.currentThread().getName() + " finished producing");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
