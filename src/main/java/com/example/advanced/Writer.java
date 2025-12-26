package com.example.advanced;

import java.util.Random;

public class Writer implements Runnable {
    private final ReadWriteCache cache;
    private final int id;
    private final Random rand = new Random();

    public Writer(ReadWriteCache cache, int id) {
        this.cache = cache;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            // Each writer performs 10 updates
            for (int i = 0; i < 10; i++) {
                String key = "key" + (rand.nextInt(5) + 1);
                String value = "w" + id + "-" + System.currentTimeMillis();
                cache.put(key, value);
                System.out.println("Writer-" + id + " wrote '" + key + "' => '" + value + "'");
                Thread.sleep(rand.nextInt(200, 401));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
