package com.example.advanced;

import java.util.concurrent.Semaphore;

public class ResourcePool {
    private final Semaphore semaphore;

    public ResourcePool(int maxResources){
        this.semaphore = new Semaphore(maxResources);
    }

    public void acquire() throws InterruptedException{
        semaphore.acquire();
    }

    public void release(){
        semaphore.release();
    }   
}
