package com.example.rksp3.task2;

import io.reactivex.rxjava3.core.Observable;
import java.util.Random;

public class Task2_3_2 {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        Observable<Integer> randomNumbers = Observable
                .range(1, 10)
                .map(i -> random.nextInt(1000))
                .cache(); // чтоб для всех папищеков одни данные были (горячий)

        Observable<Integer> firstFiveNumbers = randomNumbers.take(5);
        firstFiveNumbers.subscribe(integer -> System.out.println("              " + integer));
        Thread.sleep(1000);
        randomNumbers.subscribe(System.out::println);
    }
}

