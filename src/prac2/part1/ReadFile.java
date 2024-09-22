package prac2.part1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String filePath = "src/example.txt"; // Путь к файлу

        try {
            // Читаем все строки файла
            Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
