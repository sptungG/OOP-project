package SortingVisualizer.Sorting;

import SortingVisualizer.Visualizer.ManipulateVisualizer;

public class ManipulateSortingProcess {

    private ManipulateVisualizer manipulateVisualizer;
    private int current;
    private int check;
    private int length;
    private int[] array;
    private boolean isSorting;
    private boolean isPause;
    private boolean isStop;

    private int speed;
    private int curAlg; // 0 for bubble, 1 for quick, 2 for insertion

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCurAlg() {
        return curAlg;
    }

    public void setCurAlg(int curAlg) {
        this.curAlg = curAlg;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isIsSorting() {
        return isSorting;
    }

    public void setIsSorting(boolean isSorting) {
        this.isSorting = isSorting;
    }

    public boolean isIsPause() {
        return isPause;
    }

    public void setIsPause(boolean isPause) {
        this.isPause = isPause;
    }

    public boolean isIsStop() {
        return isStop;
    }

    public void setIsStop(boolean isStop) {
        this.isStop = isStop;
    }

    public ManipulateVisualizer getManipulateVisualizer() {
        return manipulateVisualizer;
    }

    public void setManipulateVisualizer(ManipulateVisualizer manipulateVisualizer) {
        this.manipulateVisualizer = manipulateVisualizer;
    }

    public ManipulateSortingProcess(int len, int[] array, boolean isSorting, boolean isPause, boolean isStop, int curAlg, int speed, int current, int check) {
        this.length = len;
        this.array = array;
        this.isSorting = isSorting;
        this.isPause = isPause;
        this.isStop = isStop;
        this.curAlg = curAlg;
        this.speed = speed;
        this.current = current;
        this.check = check;
        manipulateVisualizer = new ManipulateVisualizer(length, array, isSorting, isPause, isStop, current, check);
    }

    public boolean isSorted() {
        for (int i = 0; i < length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void init() {
        manipulateVisualizer.initialize();

        sorting();
    }

    public void sorting() {
        length = manipulateVisualizer.getLength();
        array = manipulateVisualizer.getArray();
        isSorting = manipulateVisualizer.isIsSorting();
        isPause = manipulateVisualizer.isIsPause();
        isStop = manipulateVisualizer.isIsStop();
        if (manipulateVisualizer.isIsSorting()) {
            try {
                switch (curAlg) {
                    case 0: // bubble sort
                        BubbleSort bubbleSort = new BubbleSort(length, array, isSorting, isPause, isStop, manipulateVisualizer);
                        bubbleSort.sort(0, length - 1);
                        break;
                    case 1:  //quick sort
                        //isStop = false;
                        setIsStop(false);
                        QuickSort quickSort = new QuickSort(length, array, isSorting, isPause, isStop, manipulateVisualizer);
                        quickSort.sort(0, length - 1);
                        break;
                    default: // insertion sort
                        InsertionSort insertionSort = new InsertionSort(length, array, isSorting, isPause, isStop, manipulateVisualizer);
                        insertionSort.sort(0, length - 1);
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!manipulateVisualizer.isIsPause()) {
            reset();
        }
        // update when sort done
        if (isSorted()) {
            manipulateVisualizer.updateWhenSortDone();
        }
        pause();
        sorting();
    }

    public void pause() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }

        //sorting();
    }

    public void reset() {
        isSorting = false;
        manipulateVisualizer.setIsSorting(false);
        //SortingAlgorithms algorithm = new SortingAlgorithms(length, array, isSorting, isPause, isStop);
        setCurrent(-1);
        setCheck(-1);
        Update();
    }

    public void delay() {
        try {
            Thread.sleep(speed);
        } catch (Exception e) {
        }
    }

    public void Update() {
        manipulateVisualizer.updateProcess(length, array, current, check);
    }

}
