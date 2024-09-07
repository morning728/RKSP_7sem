package prac1.part1;

public class SimpleSum {
    public static int[] array = new int[10000];
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(sum(array));
    }
    public static long sum(int[] array) throws InterruptedException {
        long sum = 0;
        for (int num : array) {
            sum += num;
            Thread.sleep(1);
        }
        return sum;
    }

}
