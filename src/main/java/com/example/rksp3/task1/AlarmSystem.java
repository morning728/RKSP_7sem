package com.example.rksp3.task1;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.core.Observable;

public class AlarmSystem {

    private static final int TEMP_THRESHOLD = 25;
    private static final int CO2_THRESHOLD = 70;

    public void monitor(Observable<Integer> tempStream, Observable<Integer> co2Stream) {
        Observable.combineLatest(tempStream, co2Stream, (temp, co2) -> {
            if (temp > TEMP_THRESHOLD && co2 > CO2_THRESHOLD) {
                return "ALARM!!! Temp: " + temp + " CO2: " + co2;
            } else if (temp > TEMP_THRESHOLD) {
                return "Akkuratnee: temp is high: " + temp;
            } else if (co2 > CO2_THRESHOLD) {
                return "Akkuratnee: CO2 is high: " + co2;
            }
            return "Somnitel'no no okay. Temp: " + temp + " CO2: " + co2;
        })
                .sample(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
    }
}

