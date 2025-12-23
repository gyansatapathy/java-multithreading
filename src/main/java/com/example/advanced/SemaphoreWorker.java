package com.example.advanced;

import java.util.Random;

public class SemaphoreWorker implements Runnable {
    private final ResourcePool resourcePool;
    private final int workerId;
    private final Random random = new Random();

    public SemaphoreWorker(ResourcePool resourcePool, int workerId) {
        this.resourcePool = resourcePool;
        this.workerId = workerId;
    }

    @Override
    public void run() {
        // The README requires each worker to acquire/release 3 times.
        for (int i = 0; i < 3; i++) {
            try {
                // First, acquire a permit. This may block or be interrupted.
                resourcePool.acquire();

                // If acquire succeeds, enter the try-finally block to guarantee release.
                try {
                    System.out.println("Worker " + workerId + " acquired a resource (run " + (i + 1) + "/3)");
                    // Simulate doing work with the resource.
                    Thread.sleep(getRandomDurationinMilliseconds());
                } finally {
                    // This is guaranteed to be called after the work is done or an exception occurs.
                    System.out.println("Worker " + workerId + " released a resource (run " + (i + 1) + "/3)");
                    resourcePool.release();
                }
            } catch (InterruptedException e) {
                // This catch block handles interruption during acquire() or sleep().
                System.out.println("Worker " + workerId + " was interrupted.");
                // It's good practice to restore the interrupted status.
                Thread.currentThread().interrupt();
                // If the worker was interrupted, stop its work.
                break;
            }
        }
    }

    private final int getRandomDurationinMilliseconds() {
        return random.nextInt(100, 500);
    }

}
