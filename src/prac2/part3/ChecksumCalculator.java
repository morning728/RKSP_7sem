package prac2.part3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ChecksumCalculator {
    public static void main(String[] args) {
        String filePath = "src/100mb-examplefile-com.txt"; // Путь к файлу
        try {
            int checksum = calculateChecksum(filePath);
            System.out.println("Контрольная сумма: " + Integer.toHexString(checksum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateChecksum(String filePath) throws IOException {
        int checksum = 0;
        try (FileInputStream fis = new FileInputStream(filePath)) {
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
