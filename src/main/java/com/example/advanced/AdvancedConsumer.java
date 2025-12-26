package com.example.advanced;

import java.util.concurrent.BlockingQueue;

public class AdvancedConsumer implements Runnable {
    private final BlockingQueue<String> queue;
    private final String poisonPill;

    public AdvancedConsumer(BlockingQueue<String> queue, String poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String item = queue.take();

                if (poisonPill.equals(item)) {
                    queue.put(poisonPill);
                    System.out.println(Thread.currentThread().getName() + " received POISON_PILL (stopping)");
                    break;
                }

                System.out.println(Thread.currentThread().getName() + " consumed: " + item);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
