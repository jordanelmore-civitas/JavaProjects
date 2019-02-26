import java.util.Arrays;
import java.util.Random;

public class ArrayPractice2 {

    public static double[] powArray(double[] a, int power) {

        for (int i = 0; i < a.length; i++){
            a[i] = Math.pow(a[i],power);
        }
        System.out.println(Arrays.toString(a));
        return a;
    }

    public static int[] random(int size) {
        Random random = new Random();
        int [] scores = new int[size];
        for (int i = 0; i < scores.length; i ++) {
            scores[i] = random.nextInt(100);
            //return scores[i];
        }
        System.out.println(Arrays.toString(scores));
        return scores;
    }


    public static int[] histogram(int[] scores) {
        int counters[] = new int[100];
        for (int i = 0; i < scores.length; i ++){
            if (scores[i] > 0 && scores[i] <= 100) {
                int index = scores[i];
                counters[index]++;
            }
        }

        return counters;
    }

    public static void main(String[] args) {

        double [] a = {1,2,3,4};
        int power = 2;
        int size = 100;

        powArray(a, power);
        random(size);
        System.out.println(Arrays.toString(histogram(random(size))));


    }
}
