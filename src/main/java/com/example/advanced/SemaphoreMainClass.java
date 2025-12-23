package com.example.advanced;

import java.util.ArrayList;
import java.util.List;

public class SemaphoreMainClass {
    public static void main(String[] args) {
        ResourcePool resourcePool = new ResourcePool(5);
        List<Thread> workers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Thread worker = new Thread(new SemaphoreWorker(resourcePool, i));
            worker.start();
            workers.add(worker);
        }

       for(Thread w: workers){
        try {
            w.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       }
    }

}
