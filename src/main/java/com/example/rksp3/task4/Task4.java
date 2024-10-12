package com.example.rksp3.task4;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Task4 {

    public static void main(String[] args) throws InterruptedException {
        Flowable<File> fileStream = FileGenerator.generateFiles()
                .subscribeOn(Schedulers.io());


        Flowable<File> fileQueue = FileQueue.createQueue(fileStream)
                .subscribeOn(Schedulers.io());

        FileHandler xmlHandler = new FileHandler(File.FileType.XML);
        FileHandler jsonHandler = new FileHandler(File.FileType.JSON);
        FileHandler xlsHandler = new FileHandler(File.FileType.XLS);

        // Подписываемся
        xmlHandler.handleFiles(fileQueue);
        jsonHandler.handleFiles(fileQueue);
        xlsHandler.handleFiles(fileQueue);

        Thread.sleep(1000000);
    }
}

