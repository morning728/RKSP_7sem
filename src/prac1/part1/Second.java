package prac1.part1;

import java.util.concurrent.*;
/*Создается массив фьючев, в который записываются результаты из executorа,
который запускает потоку, каждому из которых дается равный участок
массива (общий размер \ кол-во потоков)*/
public class Second {
    public static int[] array = new int[10000];
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(secondSum(array, 2));//вывод - чем больше потоков, тем, очевидно, быстрее
    }
    private static class SumInCallable implements Callable<Long> {
        private final int[] array;
        private final int start;
        private final int end;

        public SumInCallable(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
                Thread.sleep(1); // Задержка 1 мс
            }
            return sum;
        }
    }

    public static long secondSum(int[] array, int numThreads) throws InterruptedException, ExecutionException {
        int size = array.length;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Future<Long>[] futures = new Future[numThreads];

        int stepSize = size / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * stepSize;
            int end = (i == numThreads - 1) ? size : (i + 1) * stepSize;
            futures[i] = executor.submit(new SumInCallable(array, start, end));
        }
        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }
        executor.shutdown();
        return totalSum;
    }
}
