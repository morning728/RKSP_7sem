package prac1.part3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

// Класс FileGenerator генерирует файлы и помещает их в очередь
class FileGenerator implements Runnable {
    private final BlockingQueue<File> queue;
    private final Random random = new Random();
    private final String[] fileTypes = {"XML", "JSON", "XLS"};

    public FileGenerator(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100 + random.nextInt(900));

                // Генерация рандомного файла
                String type = fileTypes[random.nextInt(3)];
                int size = 1000 + random.nextInt(9100); // Размер от 1000 до 10000

                File file = new File(type, size);
                System.out.println("Сгенерирован файл: " + file);

                // Помещаем файл в очередь
                queue.put(file);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
