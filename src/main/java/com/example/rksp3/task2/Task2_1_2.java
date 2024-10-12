package com.example.rksp3.task2;

import io.reactivex.rxjava3.core.Observable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2_1_2 {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Random random = new Random();

        Observable<Integer> randomNumbers = Observable
                .range(1, 1000)  // Генерация 1000 чисел
                .map(i -> random.nextInt(1001))  // Преобразование каждого в случайное число от 0 до 1000
                .filter(num -> num > 500);  // Фильтрация чисел, больше 500

        randomNumbers.subscribe(integer -> {
            System.out.println(integer);
            counter.getAndIncrement();
        });
        Thread.sleep(2000);
        System.out.println("Vsego: " + counter);
    }
}

