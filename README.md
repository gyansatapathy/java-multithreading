# Java Multithreading Learning Repository

A comprehensive, progressive learning resource for mastering Java multithreading concepts - from basic thread creation to advanced concurrent patterns using `java.util.concurrent` framework.

## 📚 Repository Overview

This repository is organized into two main learning tracks:

1. **Basic Multithreading** (`com.example.basic`) - Foundation concepts
2. **Advanced Multithreading** (`com.example.advanced`) - Enterprise patterns

Both packages include fully worked examples, detailed README files, and runnable code.

---

## 🗂️ Repository Structure

```
java-multithreading/
├── README.md                           (This file - Overview and guide)
├── build.gradle                        (Gradle configuration)
├── settings.gradle                     (Gradle settings)
├── gradlew / gradlew.bat              (Gradle wrapper scripts)
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── basic/             ⭐ BASIC TRACK (Start here!)
│   │   │       │   ├── README.md
│   │   │       │   ├── ThreadDemoClass.java
│   │   │       │   ├── MultithreadingAssignment.java
│   │   │       │   ├── Counter.java
│   │   │       │   ├── Buffer.java
│   │   │       │   ├── Producer.java
│   │   │       │   └── Consumer.java
│   │   │       │
│   │   │       └── advanced/          ⭐ ADVANCED TRACK (After basic)
│   │   │           ├── README.md
│   │   │           └── AdvancedMultithreadingAssignment.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/example/poc/
│               └── PocApplicationTests.java
│
├── bin/                                (Compiled classes)
├── build/                              (Build output)
└── gradle/                             (Gradle configuration)
```

---

## 🚀 Quick Start

### Prerequisites
- **Java 8+** (Recommended: Java 11+)
- **GraalVM JDK 17** or any JDK 17+
- Basic knowledge of Java syntax
- Understanding of basic thread concepts

### Compilation

Compile all code:
```bash
cd path/to/java-multithreading
javac src/main/java/com/example/basic/*.java -d bin/main
javac src/main/java/com/example/advanced/*.java -d bin/main
```

Or compile specific packages:
```bash
# Basic package only
javac src/main/java/com/example/basic/*.java -d bin/main

# Advanced package only
javac src/main/java/com/example/advanced/*.java -d bin/main
```

### Run Basic Assignment
```bash
java -cp bin/main com.example.basic.MultithreadingAssignment
```

### Run Advanced Assignment
```bash
java -cp bin/main com.example.advanced.AdvancedMultithreadingAssignment
```

---

## 📖 Learning Path

### Phase 1: Basic Multithreading (Start Here!)

**Location**: `src/main/java/com/example/basic/`  
**README**: `src/main/java/com/example/basic/README.md`

**What You'll Learn**:
- ✅ Thread creation and lifecycle
- ✅ Runnable interface and lambda expressions
- ✅ Concurrent execution and thread scheduling
- ✅ Synchronization and race conditions
- ✅ wait() and notify() mechanisms
- ✅ Producer-Consumer pattern

**Tasks**:
1. Simple thread printing 1-5
2. Multiple concurrent threads (3 threads, count 1-10 each)
3. Synchronized counter (5 threads, 50 total increments)
4. Producer-Consumer with bounded buffer (2 producers, 2 consumers)

**Time to Complete**: 2-4 hours  
**Difficulty**: ⭐⭐ (Beginner)

**Key Files**:
- `MultithreadingAssignment.java` - Main assignment with all 4 tasks
- `Counter.java` - Demonstrates synchronized methods
- `Buffer.java` - Thread-safe queue implementation
- `Producer.java` - Producer thread implementation
- `Consumer.java` - Consumer thread implementation

---

### Phase 2: Advanced Multithreading (After mastering basics)

**Location**: `src/main/java/com/example/advanced/`  
**README**: `src/main/java/com/example/advanced/README.md`

**What You'll Learn**:
- ✅ ExecutorService and thread pools
- ✅ Callable interface and Future objects
- ✅ CountDownLatch and CyclicBarrier
- ✅ Semaphores for resource pooling
- ✅ ReentrantReadWriteLock for fine-grained control
- ✅ BlockingQueue for thread-safe collections
- ✅ AtomicVariables for lock-free programming
- ✅ Deadlock prevention and detection
- ✅ Performance tuning and benchmarking

**Tasks** (7 comprehensive tasks):
1. Bank Account Transfer with Deadlock Prevention
2. Thread Pool with Callable and Future
3. Synchronization Barriers (CountDownLatch & CyclicBarrier)
4. Resource Pool with Semaphore
5. Read-Write Lock Pattern
6. Producer-Consumer with BlockingQueue (Advanced)
7. Atomic Variables & Lock-Free Programming Benchmark

**Time to Complete**: 6-10 hours  
**Difficulty**: ⭐⭐⭐⭐⭐ (Advanced)

**Key Concepts**:
- java.util.concurrent.* utilities
- Thread coordination without direct synchronization
- Performance optimization
- Real-world enterprise patterns

---

## 💡 How to Use This Repository

### For Absolute Beginners
1. Read `src/main/java/com/example/basic/README.md` completely
2. Study each task (1-4) in `MultithreadingAssignment.java`
3. Understand the implementation in `Counter.java`, `Buffer.java`, `Producer.java`, `Consumer.java`
4. Run the code and analyze the output
5. Try modifying the code and predicting the results
6. Only move to advanced when confident

### For Intermediate Learners
1. Skim the basic README to refresh concepts
2. Study the advanced README carefully
3. Implement each advanced task one by one
4. Test thoroughly and understand edge cases
5. Compare your implementation with best practices

### For Self-Study
- Read the concept section in each README
- Study the code implementations
- Run the code multiple times
- Modify parameters and observe behavior
- Try implementing variations yourself

### For Teaching Others
- Use basic package for intro to threading
- Use advanced package for professional development
- Show the README files first for context
- Walk through code implementations step-by-step
- Have students modify and experiment

---

## 📋 Task Summary

### Basic Package Tasks

| Task | Concept | Files | Difficulty |
|------|---------|-------|------------|
| Task 1 | Simple Thread Creation | MultithreadingAssignment.java | ⭐ |
| Task 2 | Concurrent Threads | MultithreadingAssignment.java | ⭐⭐ |
| Task 3 | Synchronized Counter | Counter.java, MultithreadingAssignment.java | ⭐⭐⭐ |
| Task 4 | Producer-Consumer | Buffer.java, Producer.java, Consumer.java | ⭐⭐⭐⭐ |

### Advanced Package Tasks

| Task | Concept | Difficulty |
|------|---------|------------|
| Task 1 | Deadlock Prevention | ⭐⭐⭐⭐ |
| Task 2 | ExecutorService & Callable | ⭐⭐⭐ |
| Task 3 | Synchronization Barriers | ⭐⭐⭐⭐⭐ |
| Task 4 | Semaphores | ⭐⭐⭐ |
| Task 5 | Read-Write Locks | ⭐⭐⭐⭐ |
| Task 6 | Advanced Producer-Consumer | ⭐⭐⭐⭐ |
| Task 7 | Atomic & Lock-Free | ⭐⭐⭐ |

---

## 🎯 Key Learning Outcomes

### After Basic Package
- [ ] Understand thread creation and lifecycle
- [ ] Know the difference between Thread.start() and Thread.run()
- [ ] Understand race conditions and how they occur
- [ ] Know when and how to use synchronized
- [ ] Understand wait(), notify(), and notifyAll()
- [ ] Can implement producer-consumer pattern
- [ ] Can identify and fix common threading bugs

### After Advanced Package
- [ ] Can use ExecutorService effectively
- [ ] Understand Callable vs Runnable
- [ ] Know CountDownLatch vs CyclicBarrier usage
- [ ] Can implement resource pools with Semaphore
- [ ] Understand read-write lock patterns
- [ ] Can use BlockingQueue for thread-safe communication
- [ ] Know lock-free programming benefits and limitations
- [ ] Can prevent and detect deadlocks
- [ ] Can benchmark concurrent code

---

## 🔍 Common Pitfalls (Learn from mistakes!)

### Thread Creation
❌ **Wrong**: `new Thread(runnable).run()`  
✅ **Right**: `new Thread(runnable).start()`

### Synchronization
❌ **Wrong**: Only synchronizing getters, not setters  
✅ **Right**: Synchronize all methods accessing shared state

### Wait/Notify
❌ **Wrong**: Calling notify() without being in synchronized block  
✅ **Right**: Always call within synchronized block

### Resource Management
❌ **Wrong**: Not shutting down ExecutorService  
✅ **Right**: Call shutdown() and awaitTermination()

### Deadlock
❌ **Wrong**: Lock A then B, elsewhere lock B then A  
✅ **Right**: Always lock in consistent order (e.g., smaller ID first)

---

## 🧪 Testing Your Understanding

### Basic Package Verification
Run the code and check:
- Task 1: Should print 1-5
- Task 2: Should show interleaved output from 3 threads
- Task 3: Final count must be exactly 50
- Task 4: All 10 items produced and consumed, all threads complete

### Advanced Package Verification
Implement and verify:
- Task 1: No deadlock, final balance = initial balance
- Task 2: All 20 URLs fetched, no timeouts
- Task 3: All runners synchronize at each barrier
- Task 4: No more than 5 resources allocated simultaneously
- Task 5: Multiple concurrent readers, exclusive writers
- Task 6: All items produced = all items consumed
- Task 7: AtomicInteger faster than synchronized

---

## 📚 Recommended Resources

### Official Documentation
- [Java Thread API](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
- [java.util.concurrent Package](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)
- [Synchronization Documentation](https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html)

### Books
- **"Java Concurrency in Practice"** by Brian Goetz - The definitive guide
- **"Effective Java"** by Joshua Bloch - Chapter 80-82 on concurrency

### Online Resources
- Oracle's Concurrency Tutorial
- Java Concurrency on Medium
- Baeldung's Java Concurrency Articles

---

## 💻 System Requirements

| Requirement | Details |
|-------------|---------|
| **OS** | Windows, macOS, Linux |
| **Java Version** | 8+ (Recommended: 11+) |
| **RAM** | 4GB+ |
| **Disk Space** | 100MB+ |
| **Build Tool** | Gradle (included with gradlew) |

---

## 🔧 Troubleshooting

### Issue: "Class not found"
```bash
# Make sure you're in project root
cd c:\Users\mohap\Downloads\poc

# And using full package name
java -cp bin/main com.example.basic.MultithreadingAssignment
```

### Issue: "Compilation error"
```bash
# Recompile from scratch
javac src/main/java/com/example/basic/*.java -d bin/main
```

### Issue: "Program hangs"
- Likely deadlock in Task 1 of advanced package
- Check lock ordering is consistent
- Ensure all synchronized blocks are balanced

### Issue: "Different output each run"
- This is **normal** for concurrent programs!
- Threads are scheduled by OS, order varies
- Try running multiple times to see patterns

---

## 📝 Git Workflow

### Clone the repository
```bash
git clone https://github.com/gyansatapathy/java-multithreading.git
cd java-multithreading
```

### Track your progress
```bash
# Create your own branch
git checkout -b my-implementation

# Make changes and commit
git add .
git commit -m "Implement Task 1"

# Push to your fork
git push origin my-implementation
```

---

## 🎓 Learning Tips

1. **Run the code first** - Don't just read, execute and observe
2. **Modify the code** - Change values, add prints, experiment
3. **Read the output carefully** - Thread output is interleaved
4. **Add timestamps** - Use System.currentTimeMillis() to trace execution
5. **Add thread names** - Makes output easier to follow
6. **Run multiple times** - See non-deterministic behavior
7. **Use debugger** - Step through and watch thread interactions
8. **Read the READMEs** - They contain crucial insights
9. **Don't memorize** - Understand the concepts
10. **Write notes** - Reinforce learning by explaining to yourself

---

## 📊 Project Statistics

| Metric | Value |
|--------|-------|
| Total Java Files | 9 |
| Total Lines of Code | 500+ |
| Basic Package Tasks | 4 |
| Advanced Package Tasks | 7 |
| README Files | 3 |
| Total Learning Hours | 8-14 |
| Difficulty Range | ⭐ to ⭐⭐⭐⭐⭐ |

---

## 🤝 Contributing

Found an issue or want to improve?

1. Fork the repository
2. Create a feature branch
3. Make your improvements
4. Submit a pull request

---

## 📞 Support

### For Issues
- Check the README in the specific package
- Look at "Troubleshooting" section
- Review the code comments
- Run with `-XX:+ShowCodeDetailsInExceptionMessages` flag

### For Questions
- Study the detailed explanations in READMEs
- Review similar patterns in the code
- Check the recommended resources section

---

## 📄 License

This is an educational repository. Feel free to use, modify, and learn from it.

---

## 🎯 Next Steps

### If you're starting fresh:
1. Read this README completely
2. Go to `src/main/java/com/example/basic/README.md`
3. Study and implement Tasks 1-4
4. Run the code and analyze output

### If you've completed basic:
1. Read `src/main/java/com/example/advanced/README.md`
2. Understand all 7 advanced tasks
3. Implement them one by one
4. Compare your solutions with best practices

### If you're here to teach others:
1. Start with the basic package
2. Walk through code with students
3. Have them modify and predict output
4. Move to advanced when ready
5. Use READMEs as reference material

---

## 🚀 Happy Learning!

Mastering multithreading takes time and practice. Start with the basics, understand deeply, and progressively tackle more advanced patterns. The code in this repository is production-quality and follows best practices.

**Remember**: The best way to learn threading is to write it, run it, and see it fail in interesting ways!

Good luck on your multithreading journey! 🧵✨

---

**Last Updated**: December 21, 2025  
**Repository**: [java-multithreading](https://github.com/gyansatapathy/java-multithreading)  
**Owner**: gyansatapathy
