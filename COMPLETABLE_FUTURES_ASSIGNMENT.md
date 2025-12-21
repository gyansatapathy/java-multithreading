# CompletableFutures Learning Assignment

## Overview
This assignment will help you master **CompletableFutures** in Java - a powerful API for asynchronous programming and non-blocking computations. You'll learn how to create, chain, and combine multiple asynchronous tasks.

---

## Learning Objectives
- Understand the basics of CompletableFutures
- Learn how to create futures and complete them
- Master chaining operations with `thenApply`, `thenAccept`, `thenRun`
- Combine multiple futures using `thenCombine`, `thenCompose`, `anyOf`, `allOf`
- Handle exceptions in asynchronous code
- Work with executors for thread management

---

## Assignment Tasks

### Task 1: Basic CompletableFuture Creation and Completion
**Objective:** Understand how to create and manually complete a CompletableFuture

Create a class `Task1_BasicFutures.java` that:
1. Creates a CompletableFuture<String> using `supplyAsync()` that simulates a long-running operation
2. Creates another CompletableFuture<String> using `completedFuture()` with a pre-set value
3. Prints the results of both futures
4. **Bonus:** Add a simulated delay (Thread.sleep) to demonstrate async behavior

---

### Task 2: Chaining Operations
**Objective:** Learn to chain transformations using `thenApply`, `thenAccept`, and `thenRun`

Create a class `Task2_Chaining.java` that:
1. Create a future that fetches a user name (simulated - return a hardcoded name)
2. Chain `thenApply()` to transform the name to uppercase
3. Chain `thenApply()` again to add a greeting prefix (e.g., "Hello, ")
4. Use `thenAccept()` to print the final result
5. Use `thenRun()` to execute an action after completion (print "Task completed!")

---

### Task 3: Combining Multiple Futures
**Objective:** Learn to work with multiple futures using `thenCombine`

Create a class `Task3_CombiningFutures.java` that:
1. Create two futures:
   - Future 1: Returns a user's first name
   - Future 2: Returns a user's last name
2. Use `thenCombine()` to combine them into a full name
3. Print the combined result
4. **Bonus:** Create a third future for age and combine all three

---

### Task 4: Composing Futures
**Objective:** Learn the difference between `thenCombine` and `thenCompose`

Create a class `Task4_Composing.java` that:
1. Create a future that fetches a user ID
2. Use `thenCompose()` to chain another future that fetches user details based on the ID
3. Print the user details
4. **Note:** This demonstrates dependent futures where the second depends on the first

---

### Task 5: Handling All or Any
**Objective:** Learn `allOf` and `anyOf` for multiple futures

Create a class `Task5_AllOfAnyOf.java` that:
1. Create 3-4 futures that simulate API calls (each with different delays)
2. Use `allOf()` to wait for all to complete, then print "All tasks done!"
3. Create another set of futures and use `anyOf()` to race them (print when first one completes)
4. Experiment with different delays to see the difference

---

### Task 6: Exception Handling
**Objective:** Learn to handle exceptions in async code

Create a class `Task6_ExceptionHandling.java` that:
1. Create a future that throws an exception
2. Use `exceptionally()` to handle the exception and provide a default value
3. Create another future and use `handle()` to handle both success and failure cases
4. Create a third future and use `whenComplete()` to perform cleanup after completion (success or failure)
5. Print the results of all three approaches

---

### Task 7: Custom Executor
**Objective:** Learn to use custom executors for thread management

Create a class `Task7_CustomExecutor.java` that:
1. Create a custom `ExecutorService` (e.g., `Executors.newFixedThreadPool(2)`)
2. Create multiple futures using `supplyAsync()` with your custom executor
3. Observe and print which threads are executing the tasks
4. Shutdown the executor properly
5. **Bonus:** Compare with default executor behavior

---

### Task 8: Real-World Scenario - Weather Service
**Objective:** Apply all concepts in a realistic scenario

Create a class `Task8_WeatherService.java` that simulates:
1. A method `fetchTemperature(city)` - returns CompletableFuture<Double>
2. A method `fetchHumidity(city)` - returns CompletableFuture<Integer>
3. A method `fetchWeatherReport(city)` - combines both into a formatted report
4. Handle multiple cities:
   - Fetch weather for 3 different cities
   - Use `allOf()` to wait for all results
   - Use `anyOf()` to get the fastest result
5. Include error handling for failed requests
6. Print all results in a formatted way

---

### Task 9: Timeout Handling
**Objective:** Learn to handle timeouts with CompletableFutures

Create a class `Task9_Timeout.java` that:
1. Create a future that takes a long time (simulated)
2. Use `completeOnTimeout()` to provide a value if it takes too long
3. Create another future and use `orTimeout()` to throw an exception on timeout
4. Catch and handle the TimeoutException
5. Print the results

---

### Task 10: Challenge - Pipeline Processing
**Objective:** Create a complex processing pipeline

Create a class `Task10_Pipeline.java` that:
1. Simulates a data processing pipeline:
   - Step 1: Fetch raw data (returns a list of numbers)
   - Step 2: Filter the data (remove evens, keep odds)
   - Step 3: Transform the data (multiply each by 2)
   - Step 4: Aggregate the data (sum all values)
2. Each step should be an async CompletableFuture
3. Chain them together
4. Print the final result
5. **Bonus:** Add timing to see how long the entire pipeline takes

---

## Helpful Hints

### Common CompletableFutures Methods
```
supplyAsync(Supplier<T>)           - Create future with result
supplyAsync(Supplier<T>, Executor) - Create with custom executor
completedFuture(T)                 - Create with immediate value
thenApply(Function<T,U>)           - Transform the result
thenAccept(Consumer<T>)            - Consume the result
thenRun(Runnable)                  - Execute after completion
thenCombine()                      - Combine two futures
thenCompose()                      - Chain dependent futures
allOf(...)                         - Wait for all futures
anyOf(...)                         - Race futures
exceptionally()                    - Handle exceptions
handle()                           - Handle success/failure
whenComplete()                     - Cleanup callback
orTimeout()                        - Timeout with exception
completeOnTimeout()                - Timeout with default value
```

### Thread.sleep() for Simulation
```java
try {
    Thread.sleep(1000); // Sleep for 1 second
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

### Executors
```java
ExecutorService executor = Executors.newFixedThreadPool(3);
// Use with supplyAsync
// Don't forget to shutdown: executor.shutdown();
```

---

## Submission Checklist
- [ ] All 10 tasks completed
- [ ] Code is well-commented
- [ ] Exception handling is implemented
- [ ] Thread management is demonstrated
- [ ] Results are printed clearly
- [ ] No blocking operations outside of necessary Thread.sleep()
- [ ] All executors are properly shut down

---

## Testing Your Code
You can run each task independently by adding them to the main() method or running them separately. Consider creating a `Main.java` that calls all tasks sequentially:

```java
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Task 1 ===");
        Task1_BasicFutures.run();
        
        System.out.println("\n=== Task 2 ===");
        Task2_Chaining.run();
        
        // ... and so on
    }
}
```

---

## Resources to Help You
- Java Documentation: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
- Remember: CompletableFutures are part of `java.util.concurrent` package
- Your Java version is 17, so you have all modern CompletableFutures features

Good luck! This is a comprehensive deep-dive into one of Java's most powerful async APIs.

