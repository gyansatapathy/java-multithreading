# Advanced Java Multithreading Assignment

## Overview

This is a comprehensive advanced multithreading assignment designed to deepen your understanding of concurrent programming in Java. It covers enterprise-level concurrency patterns and utilities from the `java.util.concurrent` package.

## Prerequisites

- ✅ Completed the basic multithreading assignment (Tasks 1-4)
- ✅ Understanding of `synchronized`, `wait()`, and `notify()`
- ✅ Basic knowledge of thread creation and lifecycle
- ✅ Familiarity with Java collections

## Java Versions

- **Minimum**: Java 8+
- **Recommended**: Java 11+ (for better concurrency APIs)

## Package Structure

```
com.example.advanced/
├── AdvancedMultithreadingAssignment.java  (Main class with task descriptions)
├── Task1_BankTransfer.java                (Deadlock prevention)
├── Task2_WebCrawler.java                  (Thread pool & Callable)
├── Task3_RelayRace.java                   (Barriers & latches)
├── Task4_ResourcePool.java                (Semaphore pattern)
├── Task5_ReadWriteCache.java              (ReentrantReadWriteLock)
├── Task6_BlockingQueuePC.java             (Advanced producer-consumer)
└── Task7_AtomicBenchmark.java             (Lock-free programming)
```

---

## Task Details

### TASK 1: Bank Account Transfer with Deadlock Prevention

**Difficulty**: ⭐⭐⭐⭐ (Hard - Requires understanding of deadlocks)

**Concepts**:
- Deadlock prevention through ordered locking
- Synchronized blocks
- Resource ordering
- Comparators

**Requirements**:
1. Create an `Account` class with:
   - `accountId` (unique identifier)
   - `balance` (current balance)
   - `transfer(Account to, int amount)` method

2. Create a `Bank` class with:
   - Collection of accounts
   - `transfer(int fromId, int toId, int amount)` method
   - **Prevent deadlocks** by always locking accounts in order (smaller ID first)

3. Test scenario:
   - 10 accounts, each with initial balance 1000
   - 5 threads, each performing 20 random transfers
   - Verify final total = 10,000 (no money lost/created)

**Key Insight**: When two threads try to transfer in opposite directions (A→B and B→A), a deadlock can occur if each locks their source first. Solution: Always lock accounts in the same order.

**Example Output**:
```
Initial total: 10000
Transfer: Account 2 -> Account 7: 150
Transfer: Account 5 -> Account 1: 200
...
Final total: 10000
No deadlock detected!
```

---

### TASK 2: Thread Pool with Callable and Future

**Difficulty**: ⭐⭐⭐ (Medium - Working with ExecutorService)

**Concepts**:
- ExecutorService and thread pools
- Callable interface (vs Runnable)
- Future objects
- Timeout handling

**Requirements**:
1. Create `URLFetcher` Callable class:
   - Takes URL as parameter
   - Returns page size (simulated random value 1KB-10KB)
   - Simulates network delay (100-500ms random)

2. Create test with:
   - ExecutorService with 5 worker threads
   - Submit 20 Callable tasks
   - Use `Future.get(timeout, unit)` to wait for results
   - Handle `TimeoutException`

3. Calculate and print:
   - Total bytes downloaded
   - Average time per request
   - Number of timeouts (if any)

**Key Insight**: `Callable` is better than `Runnable` when you need return values. Use `Future` to get results or wait for completion.

**Example Output**:
```
Fetching 20 URLs with 5 worker threads...
Task 1: Downloaded 5234 bytes in 234ms
Task 2: Downloaded 7821 bytes in 456ms
...
Total: 142,567 bytes
Average: 7,128 bytes/request
Average time: 312ms/request
```

---

### TASK 3: Synchronization Barriers (CountDownLatch & CyclicBarrier)

**Difficulty**: ⭐⭐⭐⭐⭐ (Very Hard - Complex synchronization)

**Concepts**:
- `CountDownLatch` - Wait for N threads to finish
- `CyclicBarrier` - All threads wait at barrier, then proceed together
- Thread coordination without locks
- Reusable barriers (CyclicBarrier vs one-time CountDownLatch)

**Requirements**:
1. Simulate a 4-runner relay race with 3 legs each:
   - Each leg requires different effort (random 50-200ms)
   - Runners must all reach leg checkpoint before any can proceed
   - Use CyclicBarrier for leg synchronization (3 barriers for 3 legs)
   - Use CountDownLatch to signal when race completes

2. Create `Runner` class:
   - Runner ID (1-4)
   - For each leg: simulate effort, wait at barrier, proceed

3. Print:
   - When each runner reaches each checkpoint
   - Total race time
   - Which runner finished first/last each leg

**Key Insight**: 
- `CountDownLatch`: One-time synchronization (countDown() reduces counter)
- `CyclicBarrier`: Reusable barrier (all threads wait, then release together)

**Example Output**:
```
Runner 1 completed leg 1 (123ms)
Runner 2 completed leg 1 (98ms)
Runner 3 completed leg 1 (145ms)
Runner 4 completed leg 1 (167ms)
[All runners reach barrier - SYNC POINT]
Runner 2 completed leg 2 (156ms)
Runner 1 completed leg 2 (178ms)
...
Race complete! Total time: 1245ms
```

---

### TASK 4: Resource Pool with Semaphore

**Difficulty**: ⭐⭐⭐ (Medium - Semaphore concept)

**Concepts**:
- `Semaphore` for resource pooling
- Acquire/release patterns
- Database connection pool simulation
- Preventing over-allocation

**Requirements**:
1. Create `ResourcePool` class:
   - 5 available resources
   - `acquire()` - blocks if no resources available
   - `release()` - returns resource to pool

2. Create `Worker` class:
   - Acquires resource
   - Uses it (sleep 100-300ms)
   - Releases it

3. Test scenario:
   - 10 workers contending for 5 resources
   - Each worker acquires/releases 3 times
   - Track which worker holds which resource

4. Print:
   - When resource acquired/released
   - Current available resources
   - No more than 5 resources in use at once

**Key Insight**: Semaphore is like a counter that can be decremented/incremented. Perfect for limiting concurrent access to N resources.

**Example Output**:
```
Worker 1 acquired resource (4 remaining)
Worker 2 acquired resource (3 remaining)
Worker 3 blocked (waiting for resource)
Worker 1 released resource (4 available)
Worker 3 acquired resource (3 remaining)
```

---

### TASK 5: Read-Write Lock Pattern

**Difficulty**: ⭐⭐⭐⭐ (Hard - Fine-grained locking)

**Concepts**:
- `ReentrantReadWriteLock`
- Multiple readers can access simultaneously
- Writers get exclusive access
- Better than single synchronized lock for read-heavy workloads

**Requirements**:
1. Create `Cache` class with `ReentrantReadWriteLock`:
   - `get(String key)` - uses read lock
   - `put(String key, String value)` - uses write lock

2. Create `Reader` thread:
   - Continuously reads from cache
   - Each read sleeps 50-100ms
   - Print read operations

3. Create `Writer` thread:
   - Periodically updates cache (sleep 300-500ms)
   - Print write operations

4. Test scenario:
   - 7 reader threads (concurrent reads allowed)
   - 3 writer threads (exclusive writes)
   - Run for 10 seconds

5. Measure:
   - Number of reads completed
   - Number of writes completed
   - Demonstrate that multiple reads happen in parallel
   - Writers block readers and vice versa

**Key Insight**: Read locks don't block each other, but write locks block everything. Ideal for scenarios like caches where reads >> writes.

**Example Output**:
```
Reader 1 acquired read lock
Reader 2 acquired read lock
Reader 3 acquired read lock
Writer 1 waiting (blocked by 3 readers)
Reader 4 acquired read lock
Reader 1 released read lock
Writer 1 acquired write lock (exclusive)
Readers 2,3,4 blocked (waiting for write to complete)
Writer 1 released write lock
```

---

### TASK 6: Producer-Consumer with BlockingQueue (Advanced)

**Difficulty**: ⭐⭐⭐⭐ (Hard - Graceful shutdown pattern)

**Concepts**:
- `BlockingQueue` interface
- `LinkedBlockingQueue` implementation
- Poison pill pattern for graceful shutdown
- `put()` and `take()` blocking methods
- Throughput measurement

**Requirements**:
1. Create `AdvancedProducer` class:
   - Generates unique IDs (UUID or sequential)
   - Put items into BlockingQueue
   - Each produces 10 items
   - After producing, put "POISON_PILL" sentinel value

2. Create `AdvancedConsumer` class:
   - Takes items from BlockingQueue
   - Processes them (sleep 50-100ms)
   - Stops when receives poison pill
   - Print consumed items

3. Test scenario:
   - 3 producers (30 items total)
   - 2 consumers
   - BlockingQueue capacity: 5

4. Measure:
   - Total throughput (items/second)
   - Total time
   - Verify all items consumed exactly once

**Key Insight**: Poison pill is a special sentinel value that signals "no more data coming". Consumers stop when they receive it.

**Example Output**:
```
Producer 1 put: ID-001 (Queue size: 1)
Producer 2 put: ID-002 (Queue size: 2)
Consumer 1 took: ID-001 (processing...)
Consumer 2 took: ID-002 (processing...)
Producer 1 blocked (queue full, size: 5)
Consumer 1 finished processing ID-001
Producer 1 resumed, put: ID-003
...
Consumer 1 received POISON_PILL (stopping)
Consumer 2 received POISON_PILL (stopping)
Total items processed: 30
Throughput: 24.5 items/second
```

---

### TASK 7: Atomic Variables & Lock-Free Programming

**Difficulty**: ⭐⭐⭐ (Medium - Performance comparison)

**Concepts**:
- `AtomicInteger` for lock-free incrementing
- Compare with synchronized approach
- Performance measurement
- Lock-free programming benefits

**Requirements**:
1. Create `SynchronizedCounter` class:
   - Uses `synchronized` for increment
   - Method: `synchronized void increment()`

2. Create `AtomicCounter` class:
   - Uses `AtomicInteger`
   - Method: `void increment()` using `incrementAndGet()`

3. Benchmark both:
   - 10 threads
   - Each increments 10,000 times
   - Measure execution time using `System.nanoTime()`

4. Print:
   - Time for synchronized version
   - Time for atomic version
   - Performance difference
   - Final values (both should be 100,000)

**Key Insight**: Atomic variables often perform better than locks for simple operations because they use hardware-level atomic operations instead of OS-level locks.

**Example Output**:
```
Benchmarking SynchronizedCounter with 10 threads, 10000 increments each
Final count: 100000
Time taken: 245.3ms

Benchmarking AtomicCounter with 10 threads, 10000 increments each
Final count: 100000
Time taken: 89.7ms

Performance improvement: AtomicCounter is 2.73x faster
```

---

## Compilation and Execution

### Compile all tasks:
```bash
cd c:\Users\mohap\Downloads\poc
javac src/main/java/com/example/advanced/*.java -d bin/main
```

### Run individual task:
```bash
java -cp bin/main com.example.advanced.AdvancedMultithreadingAssignment
```

### Suggested completion order:
1. Task 1 (Bank Transfer) - Foundation
2. Task 7 (Atomic) - Simple comparison
3. Task 4 (Semaphore) - Simpler synchronization primitive
4. Task 2 (Thread Pool) - ExecutorService basics
5. Task 6 (BlockingQueue) - Easier with BlockingQueue
6. Task 5 (ReadWrite Lock) - More complex than basic locks
7. Task 3 (Barriers) - Most complex, multiple synchronization primitives

---

## Common Pitfalls to Avoid

### Task 1 (Deadlock):
- ❌ Don't lock source first, then destination (causes deadlock in reverse transfers)
- ✅ Always lock lower ID account first, then higher ID

### Task 2 (Callable):
- ❌ Don't forget to shut down ExecutorService
- ✅ Use `executor.shutdown()` and optionally `awaitTermination()`

### Task 3 (Barriers):
- ❌ Don't forget to handle `BrokenBarrierException` from `await()`
- ✅ Wrap in try-catch for robustness

### Task 4 (Semaphore):
- ❌ Don't forget to `release()` in finally block
- ✅ Use try-finally to ensure release happens

### Task 5 (ReadWrite Lock):
- ❌ Don't try to upgrade read lock to write lock (will deadlock)
- ✅ Release read lock, then acquire write lock

### Task 6 (BlockingQueue):
- ❌ Don't forget poison pill pattern
- ✅ Ensure each consumer gets a poison pill

### Task 7 (Atomic):
- ❌ Don't use `AtomicInteger` if you need compound operations
- ✅ Use `synchronized` for complex multi-step operations

---

## Testing Your Implementation

### Task 1: Verify balance integrity
```java
// Final total should always equal 10,000
assert totalBalance == 10000;
```

### Task 2: Verify all tasks completed
```java
// Should not have timeouts if time is sufficient
assert timeoutCount == 0;
```

### Task 3: Verify no runner gets ahead
```java
// All runners should complete each leg roughly together
// (no runner should complete leg 3 while others still on leg 1)
```

### Task 4: Verify no overallocation
```java
// Max concurrent use should never exceed 5
assert maxConcurrentUse <= 5;
```

### Task 5: Verify multiple readers simultaneously
```java
// Should see multiple readers active at once
assert maxConcurrentReaders > 1;
```

### Task 6: Verify all items processed exactly once
```java
// All 30 items should be produced and consumed
assert itemsProduced == itemsConsumed;
assert itemsConsumed == 30;
```

### Task 7: Verify performance difference
```java
// AtomicCounter should be faster than SynchronizedCounter
assert atomicTime < synchronizedTime;
```

---

## Learning Resources

### Official Java Documentation:
- [java.util.concurrent package](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)
- [ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)
- [Locks (ReadWriteLock)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReadWriteLock.html)
- [Atomic variables](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html)

### Recommended Books:
- "Java Concurrency in Practice" by Brian Goetz et al.
- "Effective Java" by Joshua Bloch (Chapter on Concurrency)

### Key Concepts to Master:
1. **Deadlock conditions**: Circular wait, hold and wait, mutual exclusion, no preemption
2. **Lock ordering**: Consistent ordering prevents circular wait
3. **Synchronization primitives**: Different tools for different problems
4. **Thread pools**: Managing thread lifecycle efficiently
5. **Lock-free programming**: Leveraging atomic hardware operations

---

## Submission Checklist

- [ ] All 7 tasks implemented
- [ ] Code compiles without errors
- [ ] No deadlocks detected
- [ ] Proper exception handling
- [ ] Comments explaining complex logic
- [ ] Final balances/counts verified
- [ ] Performance measurements for Task 7
- [ ] Code pushed to GitHub

---

## Tips for Success

1. **Start simple**: Implement basic functionality first, optimize later
2. **Test thoroughly**: Run each task multiple times to catch race conditions
3. **Read output carefully**: Thread output will be interleaved; this is normal
4. **Use meaningful names**: Thread names like "Producer-1", "Consumer-2" help debugging
5. **Print timestamps**: Use `System.currentTimeMillis()` to trace execution order
6. **Handle exceptions**: Don't just catch and ignore; log or handle appropriately
7. **Verify invariants**: Check that totals, counts remain consistent

---

## Troubleshooting

### Program hangs (likely deadlock in Task 1):
- Check that locks are acquired in consistent order
- Make sure both accounts in transfer are locked
- Verify `synchronized(account1)` then `synchronized(account2)` order

### Semaphore errors in Task 4:
- Ensure `release()` is always called (use try-finally)
- Check that initial permits equals resource count

### Barrier broken in Task 3:
- Handle `BrokenBarrierException`
- Verify all threads call `await()`

### Memory issues with Task 2:
- Shut down executor properly
- Don't create unlimited threads

### Performance not different in Task 7:
- Increase iteration count
- System might optimize synchronization (try both with larger numbers)

---

## Questions? 

If you get stuck on any task:
1. Read the "Key Insight" section again
2. Check the Example Output
3. Review the Common Pitfalls
4. Look at the official Java documentation link provided

Good luck! 🚀
