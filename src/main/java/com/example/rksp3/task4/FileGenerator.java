package com.example.rksp3.task4;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
public class FileGenerator {
    public static final Semaphore semaphore = new Semaphore(10); // Максимум 5 файлов
    private static final Random random = new Random();

/*    public static Flowable<File> generateFiles() {
        return Flowable.interval(3000, TimeUnit.MILLISECONDS)
                .flatMap(tick -> {
                    if (semaphore.tryAcquire()) { // Если место в очереди есть
                        File file = new File(randomFileType(), random.nextInt(91) + 10); // Генерация файла
                        System.out.println("Generated: " + file);
                        return Flowable.just(file);
                    } else {
                        System.out.println("Queue full, cannot generate more files.");
                        return Flowable.empty(); // Если очередь заполнена, ничего не генерируем
                    }
                });
    }*/
    public static Flowable<File> generateFiles() {
        return Flowable.generate(emitter -> {
            File file = new File(randomFileType(), random.nextInt(91) + 10);
            emitter.onNext(file);
            System.out.println("Generated file: " + file);
            Thread.sleep(5000);
        });
    }

    private static File.FileType randomFileType() {
        File.FileType[] types = File.FileType.values();
        return types[random.nextInt(types.length)];
    }
}

/*public class FileGenerator {

    private static final Random random = new Random();
    private static final Semaphore semaphore = new Semaphore(5); // Максимум 5 файлов


    private static File.FileType randomFileType() {
        File.FileType[] types = File.FileType.values();
        return types[random.nextInt(types.length)];
    }
*//*    public static Flowable<File> generateFiles() {
        return Flowable.generate(emitter -> {
                    File file = new File(randomFileType(), random.nextInt(91) + 10);
                    emitter.onNext(file);
                    System.out.println("Generated file: " + file);
                });
    }*//*

    public static Flowable<File> generateFiles() {
        return Flowable.interval(100, TimeUnit.MILLISECONDS)
                .flatMap(tick -> {
                    if (semaphore.tryAcquire()) { // Если место в очереди есть
                        File file = new File(randomFileType(), random.nextInt(91) + 10); // Генерация файла
                        //System.out.println("Generated file:" + file);
                        return Flowable.just(file)
                                .doOnComplete(() -> semaphore.release()); // Освободить место после обработки
                    } else {
                        return Flowable.empty(); // Если очередь заполнена, ничего не генерируем
                    }
                }); // Генерация в потоке I/O
    }*/





