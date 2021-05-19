package main.view;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class SortPanel extends JPanel {
	private ArrayList<Integer> list;
	private ArrayList<Colors> colorList;
	private int hPad;
	private int hRatio = 10; // ratio between width of bars and padding
	private int width;
	private int vPad = 5;
	private int index;
	private int line;
	private float vScale;
	private String name, message;

	public SortPanel(String name) {
		super();
		list = new ArrayList<Integer>();
		colorList = new ArrayList<Colors>();
		this.name = name;
		message = "";
		index = 0;
		line = 0;
	}
	// Used to determine the vertical scale
	public int getMaxIndex() {
		int max = 0;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) > list.get(max)) {
				max = i;
			}
		}
		return max;
	}
	// Returns the length of the list
	public int getListSize() {
		return list.size();
	}
	// Returns the element at the given index
	public int get(int index) {
		return list.get(index);
	}
	// Used by SortThread
	public ArrayList<Integer> getList() {
		return list;
	}
	// Returns the Color at a given index
	public Colors getColor(int index) {
		return colorList.get(index);
	}
	// Used in initializing the list
	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}
	// Sets the index to display on the SortPanel
	public void setIndex(int index) {
		this.index = index;
	}
	// Sets the message to explain the step occurring in sorting
	public void setMessage(String message){
		this.message = message;
	}
	// Assigns a value to the list
	public void setNum(int num, int index) {
		list.set(index, num);
	}
	// Assigns a color to a range of values
	public void setColorRange(int a, int b, Colors c) {
		for (int i = a; i < b; i++) {
			if (i >= colorList.size()) {
				colorList.add(c);
			} else {
				colorList.set(i, c);
			}
		}
	}
	// Calls the above method with the end index inserted
	public void setColorRange(int a, Colors c) {
		setColorRange(a, colorList.size(), c);
	}
	// Assigns a color to a particular index
	public void setColor(int a, Colors c) {
		colorList.set(a, c);
	}
	// Assigns a value for the horizontal line
	public void setLine(int a) {
		if (a >= 0)
			line = a;
	}
	// Swaps two list values and their colors
	public void swap(int i1, int i2) {
		int temp = list.get(i1);
		list.set(i1, list.get(i2));
		list.set(i2, temp);
		Colors tempColor = colorList.get(i1);
		colorList.set(i1, colorList.get(i2));
		colorList.set(i2, tempColor);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2d.drawString(name, 5, this.getHeight() - 30);
		g2d.drawString(message,5,this.getHeight() - 15);
		if (this.getListSize() > 0) {
			hPad = this.getWidth() / ((hRatio + 1) * list.size() + 1);
			vScale = (this.getHeight() - 2 * vPad - g2d.getFont().getSize() - 50)
					/ (float) list.get(this.getMaxIndex());
			g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
			width = hRatio * hPad;
			int y = vPad + 20;
			g2d.setColor(Colors.TARGET.get());
			g2d.drawLine(0, y+Math.round(line*vScale), this.getWidth(), y+Math.round(line*vScale));
			for (int i = 0; i < list.size(); i++) {
				int x = hPad * ((hRatio + 1) * i + 1);
				g2d.setColor(colorList.get(i).get());
				g2d.fillRect(x, y, width, Math.round(list.get(i) * vScale));
				if (i == index) { // index marker
					g2d.setColor(Color.RED);
					g2d.fillOval((2 * x + width) / 2 - 5, 5, 10, 10);
				}
			}
		}

	}
}
