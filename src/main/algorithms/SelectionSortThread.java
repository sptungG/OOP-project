package main.algorithms;
import main.view.Colors;
import main.view.MainWindow;
import main.view.SortPanel;

/** Runs a selection sort algorithm. */
public class SelectionSortThread extends SortThread {

	private final MainWindow mainWindow;

	public SelectionSortThread(MainWindow mainWindow, SortPanel sp, long msdelay) {
		super(sp, msdelay);
		this.mainWindow = mainWindow;
	}

	public void run() {
		int i, smallestIndex;
		int listSize = sp.getListSize();

		for (i = 0; i < listSize
				&& (mainWindow.isStarted() || mainWindow.isPaused()); i++) {
			if (mainWindow.isStopped())
				return;

			while (mainWindow.isPaused())
				sleepThread(10);

			smallestIndex = i;
			sp.setLine(sp.get(smallestIndex));
			sp.setMessage("Searching remaining list for smallest element. Smallest found: "
					+ sp.get(smallestIndex));

			for (int j = i; j < listSize
					&& (mainWindow.isStarted() || mainWindow.isPaused()); j++) {
				if (mainWindow.isStopped())
					return;

				while (mainWindow.isPaused())
					sleepThread(10);

				sp.setColor(smallestIndex, Colors.TARGET);
				sp.setIndex(j);

				if (sp.get(j) < sp.get(smallestIndex)) {
					sp.setColor(j, Colors.TARGET);
					sp.setColor(smallestIndex, Colors.ACTIVE);
					smallestIndex = j;
					sp.setLine(sp.get(smallestIndex));
					sp.setMessage("Searching remaining list for smallest element. Smallest found: "
							+ sp.get(smallestIndex) + ".");
				}
				repaint();
				sleepThread(msdelay);
			}
			sp.swap(i, smallestIndex);
			sp.setColor(i, Colors.SORTED);
			repaint();
		}

		if (i == listSize) {
			sorted = true;
			sp.setLine(0);
			sp.setMessage("Sorted!");
		} else {
			sp.setColorRange(i + 1, Colors.ACTIVE);
		}
		repaint();

		if (mainWindow.checkAllSorted() && mainWindow.isStarted()) {
			mainWindow.stop();
		}
	}
}