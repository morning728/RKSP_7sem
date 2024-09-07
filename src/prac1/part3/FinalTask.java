package prac1.part3;

import java.util.concurrent.*;
public class FinalTask { // Сделал размеры побольше, чтоб обработка подольше была и не сыпало сообщениями

    public static void main(String[] args) {
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(5);

        FileGenerator generator = new FileGenerator(queue);
        Thread generatorThread = new Thread(generator);
        generatorThread.start();

        ExecutorService processorPool = Executors.newFixedThreadPool(3);
        processorPool.execute(new FileProcessor(queue, "XML"));
        processorPool.execute(new FileProcessor(queue, "JSON"));
        processorPool.execute(new FileProcessor(queue, "XLS"));
    }
}

