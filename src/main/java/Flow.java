/**
 * @author Shafikov Almir
 */
import java.util.Arrays;

public class Flow {
    private static final int SIZE = 10000000;
    private static final int h = SIZE / 2;
    final   float[] arr = new float[SIZE];
    final   float[] arr1 = new float[h];
    final   float[] arr2 = new float[h];

    public void twoFlod() {

        Arrays.fill(arr,1.0f);
       final long tim = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        new Thread() {
            public void run() {
             final float[] arrc = Formula(arr1);
                System.arraycopy(arrc, 0, arr1, 0, arrc.length);
            }
        }.start();

        new Thread() {
            public void run() {
             final float[] arrc2 = Formula(arr2);
                System.arraycopy(arrc2, 0, arr2, 0, arrc2.length);
            }
        }.start();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println("Второй поток завершон на: " + (System.currentTimeMillis() - tim));
    }

    public void oneFlod() {
        final float[] arr = new float[SIZE];
        Arrays.fill(arr,1.0f);
        final long tim = System.currentTimeMillis();
        Formula(arr);
        System.out.println("Первый поток завершон на: " + (System.currentTimeMillis() - tim));
    }

    public float[] Formula(float[] arr) {
        for (int i = 0; i < arr.length; i++)
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        return arr;
    }
    
}
