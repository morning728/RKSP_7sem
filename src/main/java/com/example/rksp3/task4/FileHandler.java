package com.example.rksp3.task4;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class FileHandler {

    private File.FileType type;

    public FileHandler(File.FileType type) {
        this.type = type;
    }

    public void handleFiles(Flowable<File> fileStream) {
        fileStream
                .filter(file -> file.getType() == type)
                .flatMap(file -> {
                    return processFile(file).toFlowable();
                })
                .subscribe(result -> {
                    FileGenerator.semaphore.release();
                    System.out.println("Processed: " + result);
                });
    }

    // Метод обработки файла
    private io.reactivex.rxjava3.core.Single<String> processFile(File file) {
        int processingTime = file.getSize() * 7;
        return io.reactivex.rxjava3.core.Single
                .just("File of type " + file.getType() + " with size " + file.getSize() + " processed.")
                .delay(processingTime, TimeUnit.MILLISECONDS);
    }
}

