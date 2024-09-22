package prac2.part2;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FileUtils;

public class FileCopyBenchmark {
    private static final String SOURCE_PATH = "src/100mb-examplefile-com.txt"; // Путь к исходному файлу
    private static final String DEST_PATH = "src/copy.txt"; // Путь к целевому файлу

    public static void main(String[] args) throws IOException {
        // 1. FileInputStream/FileOutputStream
        long start = System.currentTimeMillis();
        copyUsingStreams();
        long end = System.currentTimeMillis();
        System.out.println("FileInputStream/FileOutputStream: " + (end - start) + " ms");

        // 2. FileChannel
        start = System.currentTimeMillis();
        copyUsingChannel();
        end = System.currentTimeMillis();
        System.out.println("FileChannel: " + (end - start) + " ms");

        // 3. Apache Commons IO
        start = System.currentTimeMillis();
        copyUsingCommonsIO();
        end = System.currentTimeMillis();
        System.out.println("Apache Commons IO: " + (end - start) + " ms");

        // 4. Files class
        start = System.currentTimeMillis();
        copyUsingFilesClass();
        end = System.currentTimeMillis();
        System.out.println("Files class: " + (end - start) + " ms");
    }

    private static void copyUsingStreams() throws IOException {
        try (FileInputStream fis = new FileInputStream(SOURCE_PATH);
             FileOutputStream fos = new FileOutputStream(DEST_PATH+"1")) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

    private static void copyUsingChannel() throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(SOURCE_PATH).getChannel();
             FileChannel destChannel = new FileOutputStream(DEST_PATH+"2").getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
    }

    private static void copyUsingCommonsIO() throws IOException {
        FileUtils.copyFile(new File(SOURCE_PATH), new File(DEST_PATH+"3"));
    }

    private static void copyUsingFilesClass() throws IOException {
        Files.copy(Paths.get(SOURCE_PATH), Paths.get(DEST_PATH+"4"), StandardCopyOption.REPLACE_EXISTING);
    }
}
