package main.algorithms;
import java.util.ArrayList;

import main.view.Colors;
import main.view.MainWindow;
import main.view.SortPanel;


/** Runs a merge sort algorithm. */
public class MergeSortThread extends SortThread {

	private final MainWindow mainWindow;

	public MergeSortThread(MainWindow mainWindow, SortPanel sp, long msdelay) {
		super(sp, msdelay);
		this.mainWindow = mainWindow;
		sp.setIndex(-1);
	}
	
	/** Division and joining */
	public void mergeSort(ArrayList<Integer> nums, int a, int b) {
		if (mainWindow.isStopped())
			return;

		while (mainWindow.isPaused())
			sleepThread(10);

		if (b > a + 1) {
			initialSP(a, (a + b) / 2, b, "Dividing list from index " + a
					+ " to " + (b - 1) + " in half.");
			sleepThread(msdelay);

			mergeSort(nums, a, (a + b) / 2);
			mergeSort(nums, (a + b) / 2, b);
			merge(nums, a, (a + b) / 2, b);
		}
	}

	/** Merging */
	public void merge(ArrayList<Integer> nums, int a, int mid, int b) {
		if (mainWindow.isStopped())
			return;

		while (mainWindow.isPaused())
			sleepThread(10);

		int[] lower = new int[mid - a];
		int[] upper = new int[b - mid];
		
		int index = a;
		int i, j;
		for (i = 0; index < mid; i++, index++)
			lower[i] = nums.get(index);
		for (j = 0; index < b; j++, index++)
			upper[j] = nums.get(index);


		initialSP(a, mid, b);
		sleepThread(msdelay);

		i = j = 0;
		index = a;
		while (i < lower.length && j < upper.length && (mainWindow.isStarted() || mainWindow.isPaused())) {
			while (mainWindow.isPaused())
				sleepThread(10);

			if (lower[i] < upper[j]) {
				nums.set(index, lower[i]);
				changeSP(lower[i], index, Colors.LOWER);
				i++;
			} else {
				nums.set(index, upper[j]);
				changeSP(upper[i], index, Colors.UPPER);
				j++;
			}
			index++;
			sleepThread(msdelay);
		}

		while (i < lower.length && (mainWindow.isStarted() || mainWindow.isPaused())) {
			while (mainWindow.isPaused())
				sleepThread(10);

			nums.set(index, lower[i]);
			changeSP(lower[i], index, Colors.LOWER);
			i++; index++;
			sleepThread(msdelay);
		}

		while (j < upper.length && (mainWindow.isStarted() || mainWindow.isPaused())) {
			while (mainWindow.isPaused())
				sleepThread(10);

			nums.set(index, upper[j]);
			changeSP(upper[j], index, Colors.UPPER);
			j++; index++;
			sleepThread(msdelay);
		}
	}

	private void initialSP(int a, int mid, int b) {
		sp.setColorRange(0, Colors.INACTIVE);
		sp.setColorRange(a, mid, Colors.LOWER);
		sp.setColorRange(mid, b, Colors.UPPER);
		sp.setMessage("Merging values from index " + a + " to "
					+ (b - 1) + " in order.");
		repaint();
	}
	
	private void initialSP(int a, int mid, int b, String message) {
		sp.setColorRange(0, Colors.INACTIVE);
		sp.setColorRange(a, mid, Colors.LOWER);
		sp.setColorRange(mid, b, Colors.UPPER);
		sp.setMessage(message);
		repaint();
	}
	
	public void changeSP(int line, int index, Colors color) {
		sp.setLine(line);
		sp.setColor(index, color);
		repaint();
	}
	
	public void finalSP() {
		sp.setLine(0);
		sp.setMessage("Sorted!");
		sp.setColorRange(0, Colors.SORTED);
		repaint();
	}
	
	public void run() {
		mergeSort(list, 0, list.size());
		if (mainWindow.isStarted()) {
			sorted = true;
			finalSP();
		}

		if (mainWindow.checkAllSorted() && mainWindow.isStarted()) {
			mainWindow.stop();
		}
	}

}