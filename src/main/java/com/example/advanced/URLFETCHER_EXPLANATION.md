# URLFetcher - Callable Class Explanation

## What is URLFetcher?

**URLFetcher** is a `Callable` class that simulates fetching a web page from a URL and returning the page size (number of bytes downloaded).

Think of it like this:
- You request a webpage from the internet
- The server responds with the page content
- You measure how many bytes were transferred
- You return that byte count

## Key Concepts

### Callable vs Runnable

| Aspect | Runnable | Callable |
|--------|----------|----------|
| **Return Value** | No (void) | Yes (generic type) |
| **Exceptions** | Can't throw checked exceptions | Can throw checked exceptions |
| **Use Case** | Simple tasks | Tasks that need results |
| **Interface** | `run()` | `call()` |

### Example Comparison

```java
// Runnable - No return value
class SimpleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task completed");
        // Can't return anything
    }
}

// Callable - Returns a value
class TaskWithResult implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Task completed");
        return 42;  // Return a value!
    }
}
```

---

## URLFetcher Class Structure

### What it needs:

```java
public class URLFetcher implements Callable<Integer> {
    // Constructor - takes a URL
    public URLFetcher(String url) { }
    
    // Implement call() method - returns page size
    @Override
    public Integer call() throws Exception { }
}
```

### Inside the call() method:

```
1. Receive URL as input
2. Simulate network delay (random 100-500ms)
3. Generate random page size (1KB-10KB)
4. Return the page size (as Integer)
```

---

## Step-by-Step Implementation Guide

### Step 1: Create the class

```java
public class URLFetcher implements Callable<Integer> {
    private String url;
    
    // Constructor
    public URLFetcher(String url) {
        this.url = url;
    }
    
    // Implement call() method
    @Override
    public Integer call() throws Exception {
        // Implementation goes here
        return 0;
    }
}
```

### Step 2: Simulate network delay

```java
@Override
public Integer call() throws Exception {
    // Simulate network latency (100-500ms)
    Random rand = new Random();
    int delay = 100 + rand.nextInt(400);  // 100-500ms
    
    Thread.sleep(delay);  // Wait to simulate fetching
    
    return 0;
}
```

### Step 3: Generate random page size

```java
@Override
public Integer call() throws Exception {
    // Simulate network latency
    Random rand = new Random();
    int delay = 100 + rand.nextInt(400);
    Thread.sleep(delay);
    
    // Generate random page size (1KB to 10KB)
    // 1KB = 1024 bytes
    int pageSize = 1024 + rand.nextInt(9 * 1024);  // 1KB - 10KB
    
    return pageSize;
}
```

### Step 4: Add optional logging

```java
@Override
public Integer call() throws Exception {
    Random rand = new Random();
    int delay = 100 + rand.nextInt(400);
    int pageSize = 1024 + rand.nextInt(9 * 1024);
    
    System.out.println("Fetching: " + url + " (delay: " + delay + "ms)");
    Thread.sleep(delay);
    
    System.out.println("Downloaded: " + url + " (" + pageSize + " bytes)");
    return pageSize;
}
```

---

## Complete URLFetcher Implementation

```java
import java.util.Random;
import java.util.concurrent.Callable;

public class URLFetcher implements Callable<Integer> {
    private String url;
    
    public URLFetcher(String url) {
        this.url = url;
    }
    
    @Override
    public Integer call() throws Exception {
        Random rand = new Random();
        
        // Simulate network delay (100-500ms)
        int delay = 100 + rand.nextInt(400);
        
        // Generate random page size (1KB - 10KB)
        int pageSize = 1024 + rand.nextInt(9 * 1024);
        
        System.out.println("Fetching: " + url);
        Thread.sleep(delay);  // Simulate network latency
        
        System.out.println("Downloaded: " + url + " (" + pageSize + " bytes)");
        return pageSize;  // Return the size!
    }
}
```

---

## How to Use URLFetcher with ExecutorService

### Step 1: Create ExecutorService with 5 threads

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
```

### Step 2: Submit 20 URLFetcher tasks

```java
List<Future<Integer>> futures = new ArrayList<>();

for (int i = 1; i <= 20; i++) {
    String url = "http://example.com/page" + i;
    Future<Integer> future = executor.submit(new URLFetcher(url));
    futures.add(future);
}
```

### Step 3: Collect results from Futures

```java
int totalBytes = 0;
for (Future<Integer> future : futures) {
    try {
        // get() waits for task to complete and returns the result
        Integer pageSize = future.get(5, TimeUnit.SECONDS);  // 5 second timeout
        totalBytes += pageSize;
    } catch (TimeoutException e) {
        System.out.println("Task timed out!");
    }
}
```

### Step 4: Calculate statistics

```java
System.out.println("Total bytes: " + totalBytes);
System.out.println("Average per page: " + (totalBytes / 20));
```

---

## Visual Flow Diagram

```
Main Thread
    |
    v
ExecutorService (5 worker threads)
    |
    +-- URLFetcher 1 --> Fetch page 1 (234ms) --> Return 3456 bytes
    |
    +-- URLFetcher 2 --> Fetch page 2 (412ms) --> Return 5678 bytes
    |
    +-- URLFetcher 3 --> Fetch page 3 (156ms) --> Return 2345 bytes
    |
    +-- URLFetcher 4 --> Fetch page 4 (389ms) --> Return 7890 bytes
    |
    +-- URLFetcher 5 --> Fetch page 5 (267ms) --> Return 4567 bytes
    |
    +-- ... (repeat for 20 tasks total)
    |
    v
Future<Integer> objects holding results
    |
    v
Main thread collects all results and calculates totals
```

---

## Key Points to Remember

### What makes URLFetcher "Callable":
1. ✅ Implements `Callable<Integer>` interface
2. ✅ Has `call()` method (not `run()`)
3. ✅ Returns an `Integer` (the page size)
4. ✅ Can throw checked exceptions

### Why use Callable instead of Runnable:
- ❌ Runnable: Can't return a value
- ✅ Callable: Returns page size directly

### How results are retrieved:
```java
// Runnable result:
Thread t = new Thread(runnable);
t.start();
// No way to get result!

// Callable result:
Future<Integer> future = executor.submit(callable);
Integer result = future.get();  // Get the result!
```

---

## Common Mistakes to Avoid

❌ **Wrong**: Forgetting to implement the interface
```java
public class URLFetcher {  // Missing "implements Callable<Integer>"
    public Integer call() { }
}
```

✅ **Right**: Properly implement Callable
```java
public class URLFetcher implements Callable<Integer> {
    public Integer call() throws Exception { }
}
```

---

❌ **Wrong**: Returning void
```java
@Override
public void call() {  // Can't return void!
    return;
}
```

✅ **Right**: Return the correct type
```java
@Override
public Integer call() throws Exception {
    return pageSize;  // Return Integer
}
```

---

❌ **Wrong**: Not simulating network delay
```java
@Override
public Integer call() {
    return 5000;  // Too fast, unrealistic
}
```

✅ **Right**: Simulate realistic network latency
```java
@Override
public Integer call() throws Exception {
    Thread.sleep(100 + rand.nextInt(400));  // 100-500ms delay
    return pageSize;
}
```

---

## Task 2 Complete Flow

```
1. Create ExecutorService (5 threads)
   
2. Submit 20 URLFetcher Callable tasks
   
3. Each task:
   - Simulates network delay (100-500ms)
   - Generates random page size (1KB-10KB)
   - Returns the size
   
4. Collect results from Future objects with timeout
   
5. Calculate:
   - Total bytes downloaded
   - Average bytes per request
   - Total time taken
   
6. Handle TimeoutException gracefully
```

---

## Summary

**URLFetcher** is a simple `Callable` class that:
- Takes a URL as input
- Simulates fetching it from the network
- Returns the downloaded page size
- Allows results to be retrieved via `Future.get()`

It demonstrates the power of `Callable` over `Runnable` because we need to get the result (page size) back from each task!

Ready to implement it now? 🚀
