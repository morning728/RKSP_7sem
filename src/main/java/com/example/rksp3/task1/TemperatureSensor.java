package com.example.rksp3.task1;

import io.reactivex.rxjava3.core.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TemperatureSensor {

    private Random random = new Random();

    public Observable<Integer> getTemperatureStream() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> 15 + random.nextInt(16));
    }
}