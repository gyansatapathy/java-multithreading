package com.example.advanced;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


/*
 * Runner class represents a participant in a relay race with three legs.
 * Each runner has a unique ID and performs three distinct legs of the race.
 * The class implements Runnable to allow each runner to execute in its own thread.
 * It uses CountDownLatch for overall race completion and CyclicBarrier for synchronization at each leg checkpoint.
 */
public class Runner implements Runnable {
    private Random random = new Random();
    private String id;
    private CountDownLatch countDownLatch;
    private CyclicBarrier leg1;
    private CyclicBarrier leg2;
    private CyclicBarrier leg3;
    private long raceStartTime;  // NEW: Global race clock
    private long[][] legTimes;   // NEW: Thread-safe array
    
    public Runner(String id, CountDownLatch countDownLatch, CyclicBarrier leg1, 
                  CyclicBarrier leg2, CyclicBarrier leg3, long raceStartTime, long[][] legTimes) {
        this.id = id;
        this.countDownLatch = countDownLatch;
        this.leg1 = leg1;
        this.leg2 = leg2;
        this.leg3 = leg3;
        this.raceStartTime = raceStartTime;
        this.legTimes = legTimes;
    }

    @Override
    public void run() {
        int runnerIndex = Integer.parseInt(id) - 1;  // 0-3 for array
        
        // LEG 1
        runLeg();
        long leg1Time = (System.currentTimeMillis() - raceStartTime);
        legTimes[runnerIndex][0] = leg1Time;  // Write OWN slot only
        System.out.printf("Runner %s reached checkpoint of leg 1 at %d ms%n", id, leg1Time);
        try {
            leg1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            return;
        }
        
        // LEG 2  
        runLeg();
        long leg2Time = (System.currentTimeMillis() - raceStartTime);
        legTimes[runnerIndex][1] = leg2Time;
        System.out.printf("Runner %s reached checkpoint of leg 2 at %d ms%n", id, leg2Time);
        try {
            leg2.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            return;
        }
        
        // LEG 3 - countDown FIRST to unblock main!
        runLeg();
        long leg3Time = (System.currentTimeMillis() - raceStartTime);
        legTimes[runnerIndex][2] = leg3Time;
        System.out.printf("Runner %s reached checkpoint of leg 3 at %d ms%n", id, leg3Time);
        
        countDownLatch.countDown();  // UNBLOCK MAIN THREAD
        
        // leg3.await() AFTER countDown - optional for rankings
        try {
            leg3.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }

    private long runLeg() {
        int effort = 50 + random.nextInt(151);  // 50-200ms ✓
        try {
            Thread.sleep(effort);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        return effort;
    }
}