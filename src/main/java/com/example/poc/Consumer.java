package com.example.poc;

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
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!buffer.isEmpty()) {
                    int value = buffer.get();
                    buffer.notifyAll();
                    System.out.println("Consumer " + name + " consumed: " + value);
                }
            }
        }
    }

}
