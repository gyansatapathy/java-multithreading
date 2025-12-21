package com.example.advanced;

import java.util.concurrent.*;
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
 * Hint: CountDownLatch for final completion, CyclicBarrier for intermediate sync
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
        // System.out.println("TASK 1: Bank Account Transfer with Deadlock Prevention");
        // runTask1();
        
        
        // TASK 2: Uncomment when ready
        // System.out.println("\nTASK 2: Thread Pool with Callable and Future");
        // runTask2();
        
        
        // TASK 3: Uncomment when ready
        // System.out.println("\nTASK 3: Synchronization Barriers");
        // runTask3();
        
        
        // TASK 4: Uncomment when ready
        // System.out.println("\nTASK 4: Resource Pool with Semaphore");
        // runTask4();
        
        
        // TASK 5: Uncomment when ready
        // System.out.println("\nTASK 5: Read-Write Lock Pattern");
        // runTask5();
        
        
        // TASK 6: Uncomment when ready
        // System.out.println("\nTASK 6: Producer-Consumer with BlockingQueue");
        // runTask6();
        
        
        // TASK 7: Uncomment when ready
        // System.out.println("\nTASK 7: Atomic Variables & Lock-Free Programming");
        // runTask7();
    }
    
    // TODO: Implement these helper methods
    // private static void runTask1() { }
    // private static void runTask2() { }
    // private static void runTask3() { }
    // private static void runTask4() { }
    // private static void runTask5() { }
    // private static void runTask6() { }
    // private static void runTask7() { }
}
