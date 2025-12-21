package com.example.poc;

public class Counter {
    private int count = 0;


    public synchronized void increment(){
        System.out.println("Thread " + Thread.currentThread().getName() + " " + ++count);
    }

    public synchronized int getCount(){ return count;}

}
