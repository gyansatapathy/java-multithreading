package com.example.poc;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Buffer {
    private final Queue<Integer> data;

    public Buffer(int size){
        this.data = new LinkedBlockingDeque<>(size);
    }

    public synchronized void put(int value){
        data.add(Integer.valueOf(value));
    }
    public synchronized int get(){
        return data.poll();
    }

    public boolean isFull() { return data.size() >= 3; }
    public boolean isEmpty() { return data.isEmpty();}

    static class BufferIsFullException extends Exception{
        public BufferIsFullException(String message){
            super(message);
        }
    }

    static class BufferisEmptyException extends Exception{
        public BufferisEmptyException(String message){
            super(message);
        }
    }
}
