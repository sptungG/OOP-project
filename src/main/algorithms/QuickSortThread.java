package main.algorithms;
import java.util.ArrayList;

import main.view.Colors;
import main.view.MainWindow;
import main.view.SortPanel;


/** Runs a quick sort algorithm with the rightmost element as pivot. */
public class QuickSortThread extends SortThread {

	private final MainWindow mainWindow;

	public QuickSortThread(MainWindow mainWindow, SortPanel sp, long msdelay) {
		super(sp, msdelay);
		this.mainWindow = mainWindow;
		sp.setIndex(-1);
	}

	public int partition(ArrayList<Integer> nums, int a, int b) {
		if (!mainWindow.isStarted())
			return -1;

		int pivot = nums.get(b);
		int greater = a;
		initialSP(a, b, pivot);

		for (int i = a; i < b
				&& (mainWindow.isStarted() || mainWindow.isPaused()); i++) {
			if (mainWindow.isStopped())
				return -1;

			while (mainWindow.isPaused())
				sleepThread(10);

			sp.setIndex(i);

			if (nums.get(i) < pivot) {
				sp.setColor(i, Colors.LOWER);
				sp.swap(i, greater);
				greater++;
				sp.setMessage("Moving elements before/after index " + greater
						+ " if they are < or > " + pivot + ".");
			} else {
				sp.setColor(i, Colors.UPPER);
			}

			repaint();
			sleepThread(msdelay);
		}

		sp.swap(greater, b);
		repaint();
		sleepThread(msdelay);
		return greater;
	}

	public void quickSort(ArrayList<Integer> nums, int a, int b) {
		if (mainWindow.isStopped())
			return;
		sp.setColorRange(0, Colors.INACTIVE);
		repaint();
		if (a < b + 1 && (mainWindow.isStarted() || mainWindow.isPaused())) {
			if (mainWindow.isStopped())
				return;

			while (mainWindow.isPaused())
				sleepThread(10);

			int pivot = partition(nums, a, b);
			quickSort(nums, a, pivot - 1);
			quickSort(nums, pivot + 1, b);
		}
	}

	private void initialSP(int a, int b, int pivot) {
		sp.setLine(pivot);
		sp.setColorRange(a, b, Colors.ACTIVE);
		sp.setColor(b, Colors.TARGET);
		sp.setMessage("Moving elements before/after index " + a
				+ " if they are < or > " + pivot + ".");
	}

	public void finalSP() {
		sp.setLine(0);
		sp.setMessage("Sorted!");
		sp.setColorRange(0, Colors.SORTED);
		sp.setIndex(-1);
	}
	
	public void run() {
		quickSort(list, 0, list.size() - 1);
		if (mainWindow.isStarted()) {
			sorted = true;
			finalSP();
			repaint();
		}

		if (mainWindow.checkAllSorted() && mainWindow.isStarted()) {
			mainWindow.stop();
		}
	}
}