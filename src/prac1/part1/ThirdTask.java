package prac1.part1;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ThirdTask {
    public static int[] array = new int[10000];
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(Third.forkJoinSum(array));//вывод - чем больше потоков, тем, очевидно, быстрее
    }
    public static class Third extends RecursiveTask<Long> {
        private final int[] array;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 1000; //максимум для разбиения

        //public static int[] array = new int[10000];

        public Third(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return sum;
            } else {
                int middle = (start + end) / 2;
                Third leftTask = new Third(array, start, middle);// разбиваем на таски
                Third rightTask = new Third(array, middle, end);
                leftTask.fork();//в отдельный поток
                long rightResult = rightTask.compute();//выполняем и дожидаемся левую
                long leftResult = leftTask.join();
                return leftResult + rightResult;
            }
        }

        public static long forkJoinSum(int[] array) {
            ForkJoinPool pool = new ForkJoinPool();
            return pool.invoke(new Third(array, 0, array.length));
        }
    }
}
