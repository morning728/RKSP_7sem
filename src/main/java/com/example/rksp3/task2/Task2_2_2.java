package com.example.rksp3.task2;

import io.reactivex.rxjava3.core.Observable;
import java.util.Random;

public class Task2_2_2 {

    public static void main(String[] args) {
        Random random = new Random();

/*        Observable<Integer> stream1 = Observable.range(1, 1000)
                .map(i -> random.nextInt(10));

        Observable<Integer> stream2 = Observable.range(1, 1000)
                .map(i -> random.nextInt(10));

        Observable<Integer> combinedStream = stream1.concatWith(stream2);

        combinedStream.subscribe(System.out::println);*/

        Observable<Integer> stream1 = Observable.range(0, 5);

        Observable<Integer> stream2 = Observable.range(5, 5);

        Observable<Integer> combinedStream = stream1.concatWith(stream2);

        combinedStream.subscribe(System.out::println);
    }
}

