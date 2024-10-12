package com.example.rksp3.task1;

import io.reactivex.rxjava3.core.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CO2Sensor {

    private Random random = new Random();

    public Observable<Integer> getCO2Stream() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> 30 + random.nextInt(71));
    }
}

