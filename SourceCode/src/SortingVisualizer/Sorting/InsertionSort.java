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
public class InsertionSort extends SortingAlgorithms {

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

    public InsertionSort(int length, int[] array, boolean isSorting, boolean isPause, boolean isStop, ManipulateVisualizer mv) {
        super(length, array, isSorting, isPause, isStop, mv);
    }

    @Override
    public void sort(int start, int end) {
        //getData().setStop(false);
        setIsStop(false);
        for (int i = start + 1; i <= end; i++) {
            while (!getManipulateVisualizer().isIsSorting()) {
                getManipulateVisualizer().setSortingProcessMsg(String.format("Insertion sort: Stop"));
                if (getManipulateVisualizer().isIsStop()) {
                    break;
                }
            }
            if (getManipulateVisualizer().isIsStop()) {
                break;
            }
            setCurrent(i);
            int j = i;
            getManipulateVisualizer().setSortingProcessMsg(String.format(
                    "Insertion sort: (Current = %d, Check = %d) tìm vị trí phù hợp cho giá trị Array[check] trong dãy đã sắp xếp từ vị trí start đến current-1",
                    i, j));
            while (getArray()[j] < getArray()[j - 1]) {
                while (!getManipulateVisualizer().isIsSorting()) {
                    getManipulateVisualizer().setSortingProcessMsg(String.format("Bubble sort: Stop"));
                    if (getManipulateVisualizer().isIsStop()) {
                        break;
                    }
                }
                if (getManipulateVisualizer().isIsStop()) {
                    break;
                }
                // update current process message
                getManipulateVisualizer().setSortingProcessMsg(String.format(
                        "Insertion sort: (Current = %d, Check = %d) Bởi vì Array[check] < Array[check-1] nên cần đổi chỗ 2 giá trị này => Check = %d",
                        i, j, j - 1));

                swap(j, j - 1);
                setCheck(j);

                // update current comparisons and array accessed so far
                getManipulateVisualizer().setCompared(getManipulateVisualizer().getCompared() + 1);
                getManipulateVisualizer().setArrayAccessed(getManipulateVisualizer().getArrayAccessed() + 2);

                getManipulateVisualizer().updateProcess(getLength(), getArray(), getCurrent(), getCheck());
                getManipulateVisualizer().delay();
                if (j > start + 1) {
                    j--;
                }
            }
        }
    }
}
