package prac1.part2;

import java.util.concurrent.*;
import java.util.Random;
import java.util.Scanner;

public class SecondTask {
    private final ExecutorService executor = Executors.newFixedThreadPool(2); // Пул из 2 потоков

    // Метод для выполнения возведения в квадрат с задержкой
    public Future<Integer> calculateSquare(int input) {
        return executor.submit(() -> {
            Random random = new Random();
            int delay = 5000;// + random.nextInt(4000); // Сделал задержку в пять сек, чтоб нагляднее видеть, что выполняются параллельно
            Thread.sleep(delay);
            System.out.println("Result: " + input * input);
            return input * input;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SecondTask calculator = new SecondTask();
        Scanner scanner = new Scanner(System.in);
        Future<Integer> futureResult = null;
        Boolean fl = true;

        while (true) {
            System.out.println(fl ? "Введите число для ** (или 'poka' для прощания):" : "");
            //fl=false;
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("poka")) {
                break;
            }

            try {
                //System.out.println(calculator.calculateSquare(Integer.parseInt(userInput)));
                calculator.calculateSquare((Integer.parseInt(userInput)));

            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число.");
            }
        }

        // Завершаем работу с пулом потоков
        calculator.executor.shutdown();
    }
}
