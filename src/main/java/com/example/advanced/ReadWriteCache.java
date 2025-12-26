package com.example.advanced;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCache {
    private final Map<String, String> map = new HashMap<>();
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        rw.writeLock().lock();
        try {
            map.put(key, value);
            System.out.println(LocalDateTime.now() + " [WRITE] key=" + key + " value=" + value);
        } finally {
            rw.writeLock().unlock();
        }
    }

    public String get(String key) {
        rw.readLock().lock();
        try {
            String v = map.get(key);
            System.out.println(LocalDateTime.now() + " [READ]  key=" + key + " value=" + v);
            return v;
        } finally {
            rw.readLock().unlock();
        }
    }

    public Set<String> keySet() {
        rw.readLock().lock();
        try {
            return new HashSet<>(map.keySet());
        } finally {
            rw.readLock().unlock();
        }
    }
}
