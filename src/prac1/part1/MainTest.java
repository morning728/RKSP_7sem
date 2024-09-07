package prac1.part1;

public class MainTest {
    public static void main(String[] args) throws Exception {
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        Runtime runtime = Runtime.getRuntime();

        System.out.println("первый сопособ:");
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long start = System.currentTimeMillis();
        long simpleSum = SimpleSum.sum(array);
        long time = System.currentTimeMillis() - start;
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("результат: " + simpleSum + ", время: " + time + " мс, память: " + (memoryAfter - memoryBefore) + " б");

        System.out.println("2 сопособ:");
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        start = System.currentTimeMillis();
        long parallelSum = Second.secondSum(array,6); //4 потока
        time = System.currentTimeMillis() - start;
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("результат: " + parallelSum + ", время: " + time + " мс, память: " + (memoryAfter - memoryBefore) + " б");

        System.out.println("3 сопособ:");
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        start = System.currentTimeMillis();
        long forkJoinSum = ThirdTask.Third.forkJoinSum(array);
        time = System.currentTimeMillis() - start;
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("результат: " + forkJoinSum + ", время: " + time + " мс, память: " + (memoryAfter - memoryBefore) + " б");
    }
}
