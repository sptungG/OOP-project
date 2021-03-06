/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SortingVisualizer.Sorting;

import SortingVisualizer.Visualizer.ManipulateVisualizer;

/**
 *
 * @author truongnguyen
 */
public class BubbleSort extends SortingAlgorithms {

    private int current = -1;
    private int check = -1;

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

    public BubbleSort(int length, int[] array, boolean isSorting, boolean isPause, boolean isStop, ManipulateVisualizer mv) {
        super(length, array, isSorting, isPause, isStop, mv);
    }

    @Override
    public void sort(int start, int end) {
        //getData().setStop(false);
        setIsStop(false);
        int c = 1;
        boolean noswaps = false;
        while (!noswaps) {
            while (!getManipulateVisualizer().isIsSorting()) {
                getManipulateVisualizer().setSortingProcessMsg(String.format("Bubble sort: Stop"));
                if (getManipulateVisualizer().isIsStop()) {
                    break;
                }
            }
            if (getManipulateVisualizer().isIsStop()) {
                break;
            }
            setCurrent(getLength() - c);
            noswaps = true;
            getManipulateVisualizer().setSortingProcessMsg(
                    String.format("Bubble sort: Start the %d loop, run from 0 to %d. Position %d have been sorted", c, getLength() - c, c));
            for (int i = 0; i < getLength() - c; i++) {
                while (!getManipulateVisualizer().isIsSorting()) {
                    getManipulateVisualizer().setSortingProcessMsg(String.format("Bubble sort: Stop"));
                    if (getManipulateVisualizer().isIsStop()) {
                        break;
                    }
                }
                if (getManipulateVisualizer().isIsStop()) {
                    break;
                }
                if (getArray()[i + 1] < getArray()[i]) {
                    noswaps = false;
                    // update current process message
                    getManipulateVisualizer().setSortingProcessMsg(
                            String.format("Bubble sort: Swap elements at position %d and %d because %d less than %d", i,
                                    i + 1, getArray()[i + 1], getArray()[i]));
                    swap(i, i + 1);
                }
                setCheck(i + 1);
                // update current comparisons and array accessed so far
                getManipulateVisualizer().setCompared(getManipulateVisualizer().getCompared() + 1);
                getManipulateVisualizer().setArrayAccessed(getManipulateVisualizer().getArrayAccessed() + 2);

                getManipulateVisualizer().updateProcess(getLength(), getArray(), getCurrent(), getCheck());
                getManipulateVisualizer().delay();
            }
            c++;
        }
    }
}
