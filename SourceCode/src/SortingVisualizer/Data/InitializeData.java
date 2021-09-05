package SortingVisualizer.Data;

import SortingVisualizer.Visualizer.ManipulateVisualizer;
import java.util.Random;

public class InitializeData {

    private int length;
    private int[] array;
    private boolean isSorting;
    private boolean isPause;
    private boolean isStop;

    private Random r = new Random();

    public InitializeData(int len, boolean isSorting, boolean isPause, boolean isStop) {
        this.length = len;
        this.isSorting = isSorting;
        this.isPause = isPause;
        this.isStop = isStop;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int _length) {
        this.length = _length;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] _array) {
        this.array = _array;
    }

    public boolean isSorting() {
        return isSorting;
    }

    public void setSorting(boolean _isSorting) {
        this.isSorting = _isSorting;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean _isPause) {
        this.isPause = _isPause;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean _isStop) {
        this.isStop = _isStop;
    }

    public boolean isSorted() {
        for (int i = 0; i < length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void intializeArray() {
        array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i + 1;
        }
    }

    public void swap(int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    public void generateRandomArray() {
        intializeArray();
        for (int a = 0; a < 500; a++) {
            for (int i = 0; i < length; i++) {
                int rand = r.nextInt(length);
                swap(rand, i);
            }
        }
        isSorting = false;
        new ManipulateVisualizer(length, array, isSorting, isPause, isStop, -1, -1).initialize();
    }
}
