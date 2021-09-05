package SortingVisualizer.Main;

import SortingVisualizer.Sorting.ManipulateSortingProcess;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        Random r = new Random();
        int[] array = new int[50];
        for (int i = 0; i < 50; i++) {
            array[i] = r.nextInt(51);
        }
        new ManipulateSortingProcess(50, array, false, true, false, 0, 50, -1, -1).init();
    }

}
