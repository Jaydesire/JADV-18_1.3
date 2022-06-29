import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        long startTime;

        int[] array = getInitArray(100_000_000);
        for (int i = 0; i < 5; i++) {
        ValueSumCounter counter = new ValueSumCounter(array);

        startTime = System.currentTimeMillis();
        System.out.println("Сумма всех значений массива: " + sum(array));
        System.out.println(">>> Однопоточное суммирование: " + (System.currentTimeMillis() - startTime) + "мс.");

        startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(counter);
        System.out.println(">>> Мультипоточное суммирование: " + (System.currentTimeMillis() - startTime) + "мс.");
        }
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = (int) Math.pow(i, i % 5) - (int) Math.pow(i, i % 4) + (int) Math.pow(i, i % 3);
        }
        return array;
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int value :
                array) {
            sum += value;
        }
        return sum;
    }
}


