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
public class QuickSort extends SortingAlgorithms {

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

    public QuickSort(int length, int[] array, boolean isSorting, boolean isPause, boolean isStop, ManipulateVisualizer mv) {
        super(length, array, isSorting, isPause, isStop, mv);
    }

    @Override
    public void sort(int lo, int hi) {
        while (!getManipulateVisualizer().isIsSorting()) {
            getManipulateVisualizer().setSortingProcessMsg(String.format("Quicksort: Stop"));
            if (getManipulateVisualizer().isIsStop()) {
                break;
            }
        }
        if (getManipulateVisualizer().isIsStop()) {
            return;
        }
        setCurrent(hi); // choose pivot as last position
        if (lo < hi) {
            int p = partition(lo, hi);
            if (getManipulateVisualizer().isIsStop()) {
                return;
            }
            this.sort(lo, p - 1);
            if (getManipulateVisualizer().isIsStop()) {
                return;
            }
            this.sort(p + 1, hi);
            if (getManipulateVisualizer().isIsStop()) {
                return;
            }
        }
    }

    public int partition(int lo, int hi) {
        int pivot = getArray()[hi];
        getManipulateVisualizer().setArrayAccessed(getManipulateVisualizer().getArrayAccessed() + 1);
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            setCheck(j);
            while (!getManipulateVisualizer().isIsSorting()) {
                getManipulateVisualizer().setSortingProcessMsg(String.format("Bubble sort: Stop"));
                if (getManipulateVisualizer().isIsStop()) {
                    break;
                }
            }
            if (getManipulateVisualizer().isIsStop()) {
                break;
            }
            if (getArray()[j] < pivot) {
                i++;
                // update current process message
                getManipulateVisualizer().setSortingProcessMsg(String.format(
                        "Quick sort: At position j %d < %d (arr[j] < pivot) then increase i by 1 and swap them",
                        getArray()[j], pivot));
                swap(i, j);
            }

            // update current comparisons and array accessed so far
            getManipulateVisualizer().setCompared(getManipulateVisualizer().getCompared() + 1);
            getManipulateVisualizer().setArrayAccessed(getManipulateVisualizer().getArrayAccessed() + 1);

            getManipulateVisualizer().updateProcess(getLength(), getArray(), getCurrent(), getCheck());
            getManipulateVisualizer().delay();
        }
        while (!getManipulateVisualizer().isIsSorting()) {
            getManipulateVisualizer().setSortingProcessMsg(String.format("Quicksort: Stop"));
            if (getManipulateVisualizer().isIsStop()) {
                break;
            }
        }
        // update current process message
        getManipulateVisualizer().setSortingProcessMsg(String.format(
                "Quick sort: Swap pivot = %d into middle with smaller numbers in left and larger numbers in right",
                pivot));
        swap(i + 1, hi);
        getManipulateVisualizer().updateProcess(getLength(), getArray(), getCurrent(), getCheck());
        getManipulateVisualizer().delay();
        return i + 1;
    }
}
