# Basic Java Multithreading Assignment

## Overview

This package contains the foundational multithreading assignment that introduces core concepts of concurrent programming in Java. It progresses from simple thread creation to more complex producer-consumer patterns with proper synchronization.

## Package Structure

```
com.example.basic/
├── ThreadDemoClass.java              (Simple hello world thread)
├── MultithreadingAssignment.java     (Main assignment - Tasks 1-4)
├── Counter.java                       (Task 3 - Synchronized counter)
├── Buffer.java                        (Task 4 - Thread-safe queue)
├── Producer.java                      (Task 4 - Producer implementation)
└── Consumer.java                      (Task 4 - Consumer implementation)
```

## Tasks Completed

### Task 1: Simple Thread that Prints Numbers 1-5

**Objective**: Create a basic thread that demonstrates thread creation and execution.

**Concepts Covered**:
- Thread creation
- Runnable interface
- Thread.start() vs run()
- Basic thread lifecycle

**Implementation**:
```java
Thread task1Thread = new Thread(() -> {
    for (int i = 1; i <= 5; i++) {
        System.out.println(i);
    }
});
task1Thread.start();
```

**Output**:
```
1
2
3
4
5
```

**Key Takeaway**: Using lambda expressions with Runnable for cleaner code.

---

### Task 2: Multiple Concurrent Threads

**Objective**: Demonstrate true concurrency with multiple threads running simultaneously.

**Concepts Covered**:
- Multiple thread instances
- Thread naming
- Concurrent execution
- Thread interleaving
- Scheduling behavior (OS-dependent)

**Implementation**:
```java
Runnable r = () -> {
    for (int i = 1; i <= 10; i++) {
        System.out.println("Thread " + Thread.currentThread().getName() + ": " + i);
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
```

**Expected Behavior**:
- 3 threads run simultaneously
- Output interleaved (not in strict order)
- Each thread prints 1-10
- Demonstrates that threads don't run sequentially

**Sample Output**:
```
Thread 1: 1
Thread 2: 1
Thread 1: 2
Thread 3: 1
Thread 2: 2
...
```

**Key Takeaway**: Reusing the same Runnable across multiple threads, and understanding that execution order is non-deterministic.

---

### Task 3: Thread Synchronization with Counter

**Objective**: Demonstrate synchronized access to shared resources to prevent race conditions.

**Concepts Covered**:
- Race conditions
- Synchronized methods
- Mutual exclusion (mutex)
- Atomic operations
- Data consistency

**Counter.java Implementation**:
```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        System.out.println("Thread " + Thread.currentThread().getName() + " " + ++count);
    }

    public synchronized int getCount() {
        return count;
    }
}
```

**Test Scenario**:
```java
Counter counter = new Counter();
// 5 threads, each incrementing 10 times
for (int i = 0; i < 5; i++) {
    Thread thread = new Thread(() -> {
        for (int j = 0; j < 10; j++) {
            counter.increment();
        }
    });
    thread.setName(Integer.toString(i));
    thread.start();
}

Thread.sleep(1000);
System.out.println("Final Count: " + counter.getCount()); // Should be 50
```

**Expected Output**:
```
Thread 0: 1
Thread 1: 2
Thread 2: 3
Thread 3: 4
Thread 4: 5
Thread 0: 6
...
Final Count: 50
```

**Key Points**:
- **synchronized keyword**: Ensures only one thread can execute method at a time
- **Mutual exclusion**: Prevents two threads from incrementing simultaneously
- **Data integrity**: Final count is always 50 (5 threads × 10 increments)
- **Thread safety**: No lost increments due to race conditions

**Critical Insight**: Without `synchronized`, the final count could be less than 50 due to:
- Thread A reads count (5)
- Thread B reads count (5)
- Thread A increments and writes (6)
- Thread B increments and writes (6)
- Result: Count is 6, not 7 (one increment lost!)

---

### Task 4: Producer-Consumer Pattern with Bounded Buffer

**Objective**: Implement a classic concurrent design pattern using wait/notify synchronization.

**Concepts Covered**:
- Producer-Consumer pattern
- Bounded buffer
- wait() and notify() mechanism
- Thread coordination
- Blocking operations

**Buffer.java Implementation**:
```java
public class Buffer {
    private final Queue<Integer> data;

    public Buffer(int size) {
        this.data = new LinkedBlockingDeque<>(size);
    }

    public synchronized void put(int value) {
        data.add(Integer.valueOf(value));
    }

    public synchronized int get() {
        return data.poll();
    }

    public boolean isFull() { return data.size() >= 3; }
    public boolean isEmpty() { return data.isEmpty(); }
}
```

**Producer.java Implementation**:
```java
public class Producer implements Runnable {
    private Buffer buffer;
    private int max;
    private int threadName;

    public Producer(Buffer buffer, int i, int max) {
        this.buffer = buffer;
        this.threadName = i;
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 1; i <= max; i++) {
            synchronized (buffer) {
                while (buffer.isFull()) {
                    try {
                        buffer.wait();  // Wait if buffer is full
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!buffer.isFull()) {
                    System.out.println("Producer " + threadName + " producing: " + i);
                    buffer.put(i);
                    buffer.notifyAll();  // Notify waiting consumers
                }
            }
        }
    }
}
```

**Consumer.java Implementation**:
```java
public class Consumer implements Runnable {
    private final Buffer buffer;
    private int consumerCounter;
    private final int name;

    public Consumer(Buffer buffer, int i, int j) {
        this.buffer = buffer;
        this.name = i;
        this.consumerCounter = j;
    }

    @Override
    public void run() {
        for (int i = 0; i < consumerCounter; i++) {
            synchronized (buffer) {
                while (buffer.isEmpty()) {
                    try {
                        buffer.wait();  // Wait if buffer is empty
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!buffer.isEmpty()) {
                    int value = buffer.get();
                    buffer.notifyAll();  // Notify waiting producers
                    System.out.println("Consumer " + name + " consumed: " + value);
                }
            }
        }
    }
}
```

**Test Scenario**:
```java
Buffer buffer = new Buffer(3);  // Capacity of 3

// 2 producers
Thread producer1 = new Thread(new Producer(buffer, 1, 5));
Thread producer2 = new Thread(new Producer(buffer, 2, 5));

// 2 consumers
Thread consumer1 = new Thread(new Consumer(buffer, 1, 5));
Thread consumer2 = new Thread(new Consumer(buffer, 2, 5));

producer1.start();
producer2.start();
consumer1.start();
consumer2.start();

producer1.join();
producer2.join();
consumer1.join();
consumer2.join();
```

**Sample Output**:
```
Producer 1 producing: 1
Producer 1 producing: 2
Producer 1 producing: 3
Consumer 2 consumed: 1
Consumer 2 consumed: 2
Consumer 2 consumed: 3
Producer 2 producing: 1
Producer 2 producing: 2
Producer 2 producing: 3
Consumer 2 consumed: 1
Consumer 2 consumed: 2
Consumer 2 consumed: 3
...
All threads completed successfully!
```

**Key Concepts**:

1. **Buffer as Monitor**: Synchronized access point for producers and consumers
2. **wait() and notifyAll()**:
   - `buffer.wait()`: Thread releases lock and waits
   - `buffer.notifyAll()`: Wakes up all waiting threads
3. **Bounded Buffer**: Prevents unlimited memory usage
4. **Producer behavior**:
   - Checks if buffer is full
   - If full, waits
   - If not full, produces item and notifies consumers
5. **Consumer behavior**:
   - Checks if buffer is empty
   - If empty, waits
   - If not empty, consumes item and notifies producers

**Critical Insight**: This prevents two types of problems:
- **Buffer overflow**: Producer waits if buffer is full
- **Buffer underflow**: Consumer waits if buffer is empty

---

## How to Compile and Run

### Compile all classes:
```bash
cd c:\Users\mohap\Downloads\poc
javac src/main/java/com/example/basic/*.java -d bin/main
```

### Run the main assignment:
```bash
java -cp bin/main com.example.basic.MultithreadingAssignment
```

## Execution Output Explanation

When you run the assignment, you'll see output from 4 tasks (Tasks 1-4 are enabled, Task 3 is commented out in the base code):

1. **TASK 1**: Simple count 1-5
2. **TASK 2**: Three threads counting 1-10 (interleaved output)
3. **TASK 3**: Counter with 5 threads incrementing (numbers 1-50)
4. **TASK 4**: Producer-Consumer with 2 producers and 2 consumers

The interleaved output from Tasks 2 and 4 is **expected and normal** - it shows threads running concurrently!

## Key Learning Points

### From Task 1:
- How to create and start threads
- Difference between Thread.start() and Thread.run()

### From Task 2:
- Multiple threads execute concurrently
- Order of execution is non-deterministic
- Same Runnable can be used by multiple threads

### From Task 3:
- Race conditions occur without synchronization
- synchronized keyword provides mutual exclusion
- Only one thread can execute synchronized method at a time

### From Task 4:
- Complex producer-consumer coordination
- wait() and notifyAll() for inter-thread communication
- Bounded buffer prevents resource exhaustion
- Thread safety through proper synchronization

## Common Issues and Solutions

### Issue: "Exception in thread "main" java.lang.NoClassDefFoundError"
**Solution**: Make sure you're running with full package name: `com.example.basic.MultithreadingAssignment`

### Issue: Output is garbled or different each run
**Solution**: This is normal! Threads are scheduled by OS, so execution order varies

### Issue: Program hangs/freezes
**Solution**: Check for deadlocks in synchronized blocks, ensure notifyAll() is called

### Issue: Final count is not 50 in Task 3
**Solution**: Make sure Counter methods use `synchronized` keyword

## Next Steps

After mastering these basic concepts, move to the **Advanced Multithreading Assignment** in `com.example.advanced` which covers:

- ExecutorService and thread pools
- CountDownLatch and CyclicBarrier
- Semaphores
- ReentrantReadWriteLock
- BlockingQueue
- AtomicVariables
- And more!

## Java Version

- **Minimum**: Java 8+
- **Tested with**: GraalVM JDK 17.0.9

## File Statistics

| File | Lines | Purpose |
|------|-------|---------|
| ThreadDemoClass.java | 6 | Simple hello world |
| MultithreadingAssignment.java | 154 | Main assignment orchestrator |
| Counter.java | 14 | Task 3 implementation |
| Buffer.java | 35 | Task 4 implementation |
| Producer.java | 34 | Task 4 implementation |
| Consumer.java | 36 | Task 4 implementation |
| **Total** | **279** | All basic tasks |

## Summary

This basic assignment provides a solid foundation in Java multithreading by:

✅ Teaching thread creation and lifecycle  
✅ Demonstrating concurrent execution  
✅ Introducing synchronization and mutual exclusion  
✅ Implementing classic producer-consumer pattern  
✅ Showing wait/notify mechanism  
✅ Ensuring data consistency in concurrent programs  

Once you understand these concepts deeply, you'll be ready for advanced concurrent patterns and the java.util.concurrent framework!

Happy threading! 🧵
