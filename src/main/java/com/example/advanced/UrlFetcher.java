package com.example.advanced;

import java.util.Random;
import java.util.concurrent.Callable;

public class UrlFetcher implements Callable<String> {
    private final String url;
    static final Random random = new Random();

    public UrlFetcher(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        //simulate url fetch laod
        System.out.println("Current Thread: "+ Thread.currentThread().getName());
       int randomSeconds = random.nextInt(1000);
       Thread.sleep(randomSeconds);
       int randomBytes = random.nextInt(20009);
        return Integer.toString(randomBytes);
    }
}
