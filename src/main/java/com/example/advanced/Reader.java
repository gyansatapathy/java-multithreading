package com.example.advanced;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Reader implements Runnable {
    private final ReadWriteCache cache;
    private final int id;
    private final Random rand = new Random();

    public Reader(ReadWriteCache cache, int id) {
        this.cache = cache;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            // Each reader performs 30 reads
            for (int i = 0; i < 30; i++) {
                // choose a key randomly from available keys
                Set<String> keys = cache.keySet();
                if (keys.isEmpty()) {
                    Thread.sleep(50);
                    continue;
                }
                int idx = rand.nextInt(keys.size());
                String key = new ArrayList<>(keys).get(idx);
                String value = cache.get(key);
                System.out.println("Reader-" + id + " read '" + key + "' => '" + value + "'");
                Thread.sleep(rand.nextInt(50, 151));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
