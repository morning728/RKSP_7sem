package prac1.part3;

import java.util.concurrent.BlockingQueue;

// Класс FileProcessor обрабатывает файлы из очереди
class FileProcessor implements Runnable {
    private final BlockingQueue<File> queue;
    private final String fileType;

    public FileProcessor(BlockingQueue<File> queue, String fileType) {
        this.queue = queue;
        this.fileType = fileType;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Берем файл из очереди, ждем, если пусто
                File file = queue.take();

                // Проверяем, может ли обработчик обработать файл данного типа
                if (file.getType().equals(fileType)) {
                    System.out.println("Обработка файла: " + file);
                    // Обрабатываем файл (размер файла * 7 мс)
                    Thread.sleep(file.getSize() * 7);
                    System.out.println("Файл обработан: " + file);
                } else {
                    // Если файл не того типа, возвращаем его в очередь
                    queue.put(file);
                    Thread.sleep(100); // Задержка перед повторной проверкой
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
