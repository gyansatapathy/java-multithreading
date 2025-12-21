package com.example.poc;

/**
 * Java Multithreading Assignment
 * 
 * Complete the following tasks:
 * 
 * TASK 1: Create a simple thread that prints numbers 1-5
 *    - Create a class that implements Runnable
 *    - Override the run() method to print numbers 1 to 5
 *    - Create and start a thread in the main method
 * 
 * TASK 2: Create multiple threads that run concurrently
 *    - Create 3 threads that each count from 1 to 10
 *    - Each thread should print its thread number and current count
 *    - Use Thread.sleep(100) to simulate work
 * 
 * TASK 3: Implement thread synchronization
 *    - Create a shared counter class that multiple threads will increment
 *    - Implement synchronized methods to prevent race conditions
 *    - Create 5 threads that each increment the counter 10 times
 *    - Print the final count (should be 50)
 * 
 * TASK 4: Implement Producer-Consumer pattern with wait/notify
 *    - Create a Buffer class with fixed capacity (bounded buffer)
 *    - Implement synchronized put(int value) method that:
 *         * Blocks producer if buffer is full (use wait())
 *         * Adds item to buffer when space is available
 *         * Notifies waiting consumers after adding
 *    - Implement synchronized get() method that:
 *         * Blocks consumer if buffer is empty (use wait())
 *         * Removes and returns item from buffer when available
 *         * Notifies waiting producers after removing
 *    - Create 2 PRODUCER threads that each produce 5 items (10 total)
 *    - Create 2 CONSUMER threads that each consume 5 items (10 total)
 *    - Print each produce/consume action with timestamps
 *    - Buffer should prevent overflow (producers wait when full)
 *    - Buffer should prevent underflow (consumers wait when empty)
 *    - Demonstrate proper synchronization without deadlocks
 */

public class MultithreadingAssignment {
    
    // ===== TASK 1: Simple Runnable Implementation =====
    // TODO: Create a class that implements Runnable and prints numbers 1-5
    // Hint: The class should have a run() method
    
    
    // ===== TASK 2: Multiple Concurrent Threads =====
    // TODO: Create a class that implements Runnable for concurrent execution
    // Hint: The class should accept a thread identifier in constructor
    
    
    // ===== TASK 3: Thread Synchronization =====
    // TODO: Create a Counter class with a synchronized increment method
    // Hint: Use synchronized keyword to prevent race conditions
    
    
    // ===== TASK 4: Producer-Consumer Pattern =====
    // TODO: Create a Buffer class with:
    //   - private int[] buffer array of fixed size (capacity)
    //   - private int size (tracks number of items in buffer)
    //   - synchronized put(int value) - adds to buffer, waits if full
    //   - synchronized get() - removes from buffer, waits if empty
    //   - Use wait() when buffer is full/empty
    //   - Use notifyAll() after put/get operations
    // TODO: Create Producer class implementing Runnable:
    //   - Takes Buffer and producer ID in constructor
    //   - Produces multiple items with some delay
    // TODO: Create Consumer class implementing Runnable:
    //   - Takes Buffer and consumer ID in constructor
    //   - Consumes multiple items with some delay
    
    
    public static void main(String[] args) {
        System.out.println("=== Java Multithreading Assignment ===\n");
        
        // TASK 1: Uncomment and implement
        System.out.println("TASK 1: Simple Thread");
        Thread task1Thread = new Thread(() ->{
            for (int i = 1; i <= 5; i++) {
                System.out.println(i);
            }
        });
        task1Thread.start();
        
        
        // // TASK 2: Uncomment and implement
        System.out.println("\nTASK 2: Multiple Concurrent Threads");
        Runnable r = () ->{
            for (int i = 1; i<=10; i++){
                System.out.println("Thread "+Thread.currentThread().getName()+": "+i);
            }
        };
        Thread t1 = new Thread(r);
        t1.setName("1");
        Thread t2 = new Thread(r);
        t2.setName("2");
        Thread t3 = new Thread(r);
        t3.setName("3");
        t1.start();
        t2.start();
        t3.start();
        
        
        // // TASK 3: Uncomment and implement
        // System.out.println("\nTASK 3: Thread Synchronization");
        Counter counter = new Counter();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    counter.increment();
                }
            });
            thread.setName(Integer.toString(i));
            thread.start();
        }
        try {
            Thread.sleep(1000);
            System.out.println("Final Count: " + counter.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        // TASK 4: Uncomment and implement
        System.out.println("\nTASK 4: Producer-Consumer Pattern (Bounded Buffer)");
        Buffer buffer = new Buffer(3);  // Buffer capacity of 3
        
        // Create and start 2 producers
        Thread producer1 = new Thread(new Producer(buffer, 1, 5));
        Thread producer2 = new Thread(new Producer(buffer, 2, 5));
        
        // Create and start 2 consumers
        Thread consumer1 = new Thread(new Consumer(buffer, 1, 5));
        Thread consumer2 = new Thread(new Consumer(buffer, 2, 5));
        
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        
        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
            System.out.println("\nAll threads completed successfully!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
