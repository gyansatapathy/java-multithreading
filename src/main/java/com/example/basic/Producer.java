package com.example.basic;

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
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(!buffer.isFull()) {
                    System.out.println("Producer " + threadName + " producing: " + i);
                    buffer.put(i);
                    buffer.notifyAll();
                }
            }
        }
    }
}
