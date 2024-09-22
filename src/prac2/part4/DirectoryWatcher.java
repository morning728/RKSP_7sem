package prac2.part4;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DirectoryWatcher {
    private static final String WATCH_DIRECTORY = "src/test_directory"; // Укажите путь к каталогу
    private static Map<Path, List<String>> previousFileContents = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(WATCH_DIRECTORY);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        System.out.println("Начало наблюдения за каталогом: " + WATCH_DIRECTORY);

        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                Path filePath = path.resolve((Path) event.context());

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("Создан новый файл: " + filePath);
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("Изменён файл: " + filePath);
                    printLineChanges(filePath);
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("Удалён файл: " + filePath);
                    printFileSizeAndChecksum(filePath);
                }
            }
            key.reset();
        }
    }

    private static void printLineChanges(Path filePath) {
        try {
            List<String> currentLines = Files.readAllLines(filePath);
            List<String> previousLines = previousFileContents.get(filePath);

            if (previousLines == null) {
                // Если файла не было, просто сохраняем текущее состояние
                previousFileContents.put(filePath, currentLines);
                System.out.println("Содержимое нового файла:");
                currentLines.forEach(System.out::println);
                return;
            }

            // Находим добавленные строки
            List<String> addedLines = new ArrayList<>(currentLines);
            addedLines.removeAll(previousLines);
            if (!addedLines.isEmpty()) {
                System.out.println("Добавленные строки:");
                addedLines.forEach(System.out::println);
            }

            // Находим удалённые строки
            List<String> removedLines = new ArrayList<>(previousLines);
            removedLines.removeAll(currentLines);
            if (!removedLines.isEmpty()) {
                System.out.println("Удалённые строки:");
                removedLines.forEach(System.out::println);
            }

            // Обновляем предыдущее состояние
            previousFileContents.put(filePath, currentLines);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void printFileSizeAndChecksum(Path filePath) {
        try {
            if (Files.exists(filePath)) {
                long size = Files.size(filePath);
                int checksum = calculateChecksum(filePath);
                System.out.println("Размер файла: " + size + " байт");
                System.out.println("Контрольная сумма: " + Integer.toHexString(checksum));
            } else {
                System.out.println("Файл уже удалён или не существует: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при получении размера или контрольной суммы: " + e.getMessage());
        }
    }

    private static int calculateChecksum(Path filePath) throws IOException {
        int checksum = 0;
        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    checksum += Byte.toUnsignedInt(buffer[i]);
                }
            }
        }
        return checksum & 0xFFFF; // Оставляем только 16 младших бит
    }
}
