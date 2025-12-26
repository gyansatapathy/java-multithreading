package com.example.advanced;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.LocalDateTime;
import java.util.*;

/**
 * ADVANCED Java Multithreading Assignment
 * 
 * This assignment tests deep understanding of:
 * - Thread Pools and ExecutorService
 * - Callable and Future
 * - CountDownLatch and CyclicBarrier
 * - Semaphores and Locks
 * - Blocking Queues
 * - AtomicVariables
 * - Thread-safe Collections
 * 
 * ============================================================================
 * 
 * TASK 1: Bank Account Transfer with Deadlock Prevention
 * --------
 * Create a banking system where:
 * - Multiple accounts can transfer money to each other
 * - Each transfer is atomic (synchronized properly)
 * - Prevent deadlocks when Account A transfers to B and B transfers to A
 * - Use ordered locking (always lock accounts in same order: smaller ID first)
 * - Create 10 accounts with initial balance 1000
 * - Run 5 threads, each performing 20 random transfers
 * - Final total balance should remain 10,000
 * 
 * Hint: Use a Comparator to ensure consistent lock ordering
 * 
 * ============================================================================
 * 
 * TASK 2: Thread Pool with Callable and Future
 * -------
 * Create a web crawler simulator:
 * - Use ExecutorService with fixed thread pool (5 threads)
 * - Create 20 Callable tasks that "fetch" URLs and return page size
 * - Each task simulates network delay (random 100-500ms)
 * - Submit all tasks and collect Futures
 * - Wait for all tasks to complete using get() with timeout
 * - Print total bytes downloaded and average time per request
 * - Handle timeout exceptions gracefully
 * 
 * Hint: Use invokeAll() or manual Future.get() with timeout
 * 
 * ============================================================================
 * 
 * TASK 3: Synchronization Barriers (CountDownLatch & CyclicBarrier)
 * -------------------
 * Simulate a relay race:
 * - 4 runners, each with 3 legs
 * - All runners must reach a checkpoint before proceeding
 * - Use CountDownLatch for runners to finish
 * - Use CyclicBarrier for synchronization at each leg
 * - Print when each runner completes each leg
 * - Ensure fair progression (no runner gets too far ahead)
 * 
 * Hint: CountDownLatch for final completion, CyclicBarrier for intermediate
 * sync
 * 
 * ============================================================================
 * 
 * TASK 4: Resource Pool with Semaphore
 * -----
 * Create a thread-safe resource pool (like database connection pool):
 * - Limited resources (5 available)
 * - Multiple threads trying to acquire/release resources
 * - Create 10 worker threads
 * - Each acquires a resource, uses it (sleep 100-300ms), then releases
 * - Track which thread has which resource
 * - Prevent overallocation using Semaphore
 * - Print resource acquisition and release events
 * 
 * Hint: Use Semaphore with permits=5
 * 
 * ============================================================================
 * 
 * TASK 5: Read-Write Lock Pattern
 * --------
 * Create a shared cache with read-write locks:
 * - 3 writer threads updating cache
 * - 7 reader threads reading cache
 * - Writers should block readers (exclusive access)
 * - Multiple readers can read simultaneously
 * - Implement using ReentrantReadWriteLock
 * - Print read/write operations with timestamps
 * - Measure contention and performance
 * 
 * Hint: Use ReadWriteLock for concurrent reads, exclusive writes
 * 
 * ============================================================================
 * 
 * TASK 6: Producer-Consumer with Blocking Queue (Advanced)
 * -------
 * Enhanced producer-consumer with multiple producers/consumers:
 * - Use BlockingQueue<String> (not manual Buffer class)
 * - 3 producer threads generating unique IDs
 * - 2 consumer threads processing IDs
 * - Use take() and put() (blocking methods)
 * - Producers should stop after producing 30 items total
 * - Consumers should stop when queue is empty and all producers done
 * - Calculate throughput (items per second)
 * 
 * Hint: Use poison pill pattern or CountDownLatch for graceful shutdown
 * 
 * ============================================================================
 * 
 * TASK 7: Atomic Variables & Lock-Free Programming
 * --------
 * Create a high-frequency counter without locks:
 * - Use AtomicInteger instead of synchronized
 * - 10 threads each incrementing 1000 times
 * - Compare performance with synchronized version
 * - Measure execution time for both approaches
 * - Use System.nanoTime() for accurate timing
 * 
 * Hint: AtomicInteger has incrementAndGet() method
 * 
 * ============================================================================
 */

public class AdvancedMultithreadingAssignment {

    // ===== TASK 1: Bank Account Transfer with Deadlock Prevention =====
    // TODO: Implement Account class
    // TODO: Implement Bank class with transfer method
    // TODO: Create test with 10 accounts and 5 threads doing random transfers

    // ===== TASK 2: Thread Pool with Callable and Future =====
    // TODO: Create URLFetcher Callable class
    // TODO: Use ExecutorService to manage 5 worker threads
    // TODO: Submit 20 tasks and collect Futures
    // TODO: Handle timeouts with get(long timeout, TimeUnit unit)

    // ===== TASK 3: Synchronization Barriers =====
    // TODO: Create Runner class using CyclicBarrier for leg checkpoints
    // TODO: Use CountDownLatch for overall race completion
    // TODO: Create 4 runners each doing 3 legs

    // ===== TASK 4: Resource Pool with Semaphore =====
    // TODO: Create ResourcePool class with Semaphore
    // TODO: Create Worker class that acquires/releases resources
    // TODO: Create 10 workers contending for 5 resources

    // ===== TASK 5: Read-Write Lock Pattern =====
    // TODO: Create Cache class with ReentrantReadWriteLock
    // TODO: Create Reader and Writer classes
    // TODO: 7 readers and 3 writers accessing shared cache

    // ===== TASK 6: Producer-Consumer with BlockingQueue =====
    // TODO: Create AdvancedProducer class using BlockingQueue
    // TODO: Create AdvancedConsumer class using BlockingQueue
    // TODO: Use poison pill or CountDownLatch for shutdown coordination

    // ===== TASK 7: Atomic Variables & Lock-Free Programming =====
    // TODO: Create SynchronizedCounter using synchronized
    // TODO: Create AtomicCounter using AtomicInteger
    // TODO: Benchmark both approaches with 10 threads, 1000 increments each

    public static void main(String[] args) {
        System.out.println("=== ADVANCED Java Multithreading Assignment ===\n");

        // TASK 1: Uncomment when ready
        System.out.println("TASK 1: Bank Account Transfer with Deadlock Prevention");
        runTask1();

        // TASK 2: Uncomment when ready
        System.out.println("\nTASK 2: Thread Pool with Callable and Future");
        runTask2();

        // TASK 3: Uncomment when ready
        System.out.println("\nTASK 3: Synchronization Barriers");
        runTask3();

        // TASK 4: Uncomment when ready
        // System.out.println("\nTASK 4: Resource Pool with Semaphore");
        // runTask4();

        // TASK 5: Uncomment when ready
        System.out.println("\nTASK 5: Read-Write Lock Pattern");
        runTask5();

        // TASK 6: Uncomment when ready
        // System.out.println("\nTASK 6: Producer-Consumer with BlockingQueue");
        // runTask6();

        // TASK 7: Uncomment when ready
        // System.out.println("\nTASK 7: Atomic Variables & Lock-Free Programming");
        // runTask7();
    }

    private static void runTask1() {
        Bank bank = new Bank();
        for (int i = 1; i <= 10; i++) {
            Account account = new Account(i, 1000);
            bank.addAccount(account);
        }

        System.out.println("Initial total balance: $" + bank.getTotalBalance());
        System.out.println("Starting 5 threads with 20 random transfers each...\n");

        // 5 threads doing 20 random transactions from account id 1-10
        List<Thread> threads = new ArrayList<>();
        Random rand = new Random();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    int fromId = rand.nextInt(10) + 1;
                    int toId = rand.nextInt(10) + 1;
                    while (toId == fromId) {
                        toId = rand.nextInt(10) + 1;
                    }
                    int amount = rand.nextInt(200) + 1;
                    bank.transfer(fromId, toId, amount);
                }
            });
            threads.add(t);
            t.start();
        }

        // Wait for all threads to complete
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\n=== TASK 1 RESULTS ===");
        System.out.println("All transfers completed!");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Final total balance: $" + bank.getTotalBalance());
        System.out.println("Expected total: $10000");

        if (bank.getTotalBalance() == 10000) {
            System.out.println("✓ SUCCESS - No money lost, no deadlock!");
        } else {
            System.out.println("✗ FAIL - Balance mismatch!");
        }
        System.out.println();
    }

    private static void runTask2() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.rangeClosed(1, 20)
                .boxed()
                .map(v -> {
                    return executorService.submit(new UrlFetcher("someurl" + v.toString()));
                }).map(future -> {
                    try {
                        return future.get(400, TimeUnit.MILLISECONDS); // may timeout
                    } catch (TimeoutException e) {
                        // handle timeout
                        return "timeout";
                    } catch (ExecutionException e) {
                        // underlying task threw
                        return "error: " + e.getCause();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return "interrupted";
                    }
                })
                .forEach(System.out::println);
        executorService.shutdownNow();

    }

    private static void runTask3() {
        int numRunners = 4;
        long raceStartTime = System.currentTimeMillis(); // ✅ Global timer
        long[][] legTimes = new long[numRunners][3]; // ✅ Thread-safe array

        CountDownLatch raceCompletionLatch = new CountDownLatch(numRunners);

        CyclicBarrier leg1Barrier = new CyclicBarrier(numRunners, () -> printLegRanking(legTimes, 0, 1, "Leg 1")); // Leg
                                                                                                                   // 0
                                                                                                                   // in
                                                                                                                   // array

        CyclicBarrier leg2Barrier = new CyclicBarrier(numRunners, () -> printLegRanking(legTimes, 1, 2, "Leg 2"));

        CyclicBarrier leg3Barrier = new CyclicBarrier(numRunners, () -> printLegRanking(legTimes, 2, 3, "Leg 3"));

        List<Thread> runners = new ArrayList<>();
        System.out.println("Starting the race with " + numRunners + " runners...\n");

        for (int i = 1; i <= numRunners; i++) {
            Runner runner = new Runner(Integer.toString(i), raceCompletionLatch,
                    leg1Barrier, leg2Barrier, leg3Barrier, raceStartTime, legTimes);
            Thread t = new Thread(runner);
            runners.add(t);
            t.start();
        }

        try {
            raceCompletionLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        long totalTime = System.currentTimeMillis() - raceStartTime;
        System.out.println("\n=== TASK 3 RESULTS ===");
        System.out.println("All runners have finished the race!");
        System.out.printf("Total race time: %d ms%n", totalTime);
    }

    // HELPER METHOD - Add this method
    private static void printLegRanking(long[][] legTimes, int legIndex, int legNumber, String legName) {
        long minTime = Long.MAX_VALUE, maxTime = 0;
        int firstRunner = -1, lastRunner = -1;

        for (int r = 0; r < 4; r++) {
            long time = legTimes[r][legIndex];
            if (time < minTime) {
                minTime = time;
                firstRunner = r + 1;
            }
            if (time > maxTime) {
                maxTime = time;
                lastRunner = r + 1;
            }
        }

        // ✅ FIXED: %d for long integers, not %.0f
        System.out.printf("%s: Runner %d first (%d ms), Runner %d last (%d ms)%n",
                legName, firstRunner, minTime, lastRunner, maxTime);
    }

    private static void runTask4() {
        ResourcePool resourcePool = new ResourcePool(5);
        List<Thread> workers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Thread worker = new Thread(new SemaphoreWorker(resourcePool, i));
            worker.start();
            workers.add(worker);
        }

        for (Thread w : workers) {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void runTask5() {
        System.out.println("\nTASK 5: Read-Write Lock Pattern");
        ReadWriteCache cache = new ReadWriteCache();

        // Pre-populate cache with some keys
        for (int i = 1; i <= 5; i++) {
            cache.put("key" + i, "init" + i);
        }

        List<Thread> threads = new ArrayList<>();

        // Start 3 writers
        for (int w = 1; w <= 3; w++) {
            Thread writer = new Thread(new Writer(cache, w), "Writer-" + w);
            threads.add(writer);
            writer.start();
        }

        // Start 7 readers
        for (int r = 1; r <= 7; r++) {
            Thread reader = new Thread(new Reader(cache, r), "Reader-" + r);
            threads.add(reader);
            reader.start();
        }

        // Wait for all to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n=== TASK 5 RESULTS ===");
        System.out.println("All readers and writers completed.");
    }

    // private static void runTask6() { }
    // private static void runTask7() { }
}
