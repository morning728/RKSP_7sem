package com.example.rksp3.task4;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class FileQueue {

    public static Flowable<File> createQueue(Flowable<File> fileStream) {
        return fileStream
                .buffer(5) // Ограничиваем до 5 файлов в буфере
                .flatMap(files -> {return Flowable.fromIterable(files);})
                .share();
    }
}

