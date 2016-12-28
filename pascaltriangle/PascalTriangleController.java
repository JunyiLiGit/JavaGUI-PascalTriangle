package pascaltriangle;

import java.awt.event.ActionEvent;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.text.BadLocationException;

class PascalTriangleController implements java.awt.event.ActionListener {

	private PascalTriangleModel model;
	private PascalTriangleView view;

	//indexArrayList: data that needs to be sent to the view
	private ArrayList<Integer> indexArrayList;

	//0:counting number, 1:Triangular Number, 2:fanonacci Nummber, 3:Square Number,3:fanonacci Nummber Pattern  
	//use this to keep track the color status, when the user choose to see the square numbers, then the user click add row
	// by default the user will still see all square numbers in pascal's triangle
	private final boolean[] colorStatus = new boolean[5];
	private final int maxRow;

	PascalTriangleController() {
		indexArrayList = new ArrayList<>();
		maxRow = 23;
	} // PascalTriangleController

	// Register the model with the controller
	public void addModel(PascalTriangleModel m) {
		this.model = m;
	} // addModel

	// Register the view with the controller
	public void addView(PascalTriangleView v) {
		this.view = v;
	} //addView

	public void initView(int x) {
		for (int i = 0; i < x; ++i) {
			ArrayList<Integer> tmp;
			tmp = model.addRow();

			view.drawRow(tmp);
		}
	}

	// Handles UI buttons from the view
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (null != e.getActionCommand()) {
				switch (e.getActionCommand()) {
					case "Add Row":
						if (model.getRow() < maxRow) {
							ArrayList<Integer> rowValue = new ArrayList<>();
							rowValue = model.addRow();

							view.drawRow(rowValue);
							{
								try {
									view.eraseColor();
								} catch (BadLocationException ex) {
									Logger.getLogger(PascalTriangleController.class.getName()).log(Level.SEVERE, null, ex);
								}
							}
							if (colorStatus[0]) {
								indexArrayList = countingNumber();
							} else if (colorStatus[1]) {
								indexArrayList = triangularNumber();
							} else if (colorStatus[2]) {
								indexArrayList = squareNumner();
							} else if (colorStatus[3]) {
								indexArrayList = fibonacciNumber();
							} else if (colorStatus[4]) {
								indexArrayList = fibonacciPattern();
							}

							view.colorNumber(indexArrayList);
						} else {
							System.out.println("not enough space");

						}

						break;

					case "Remove Row":

						model.removeRow();
						view.eraseColor();
						view.eraseRow();

						if (colorStatus[0]) {
							indexArrayList = countingNumber();
						} else if (colorStatus[1]) {
							indexArrayList = triangularNumber();
						} else if (colorStatus[2]) {
							indexArrayList = squareNumner();
						} else if (colorStatus[3]) {
							indexArrayList = fibonacciNumber();
						} else if (colorStatus[4]) {
							indexArrayList = fibonacciPattern();
						} else {
							view.eraseColor();
						}
						view.colorNumber(indexArrayList);

						break;

				}
			}

			JComboBox cb;
			Object src = e.getSource();
			if (src instanceof JComboBox) {
				try {
					cb = (JComboBox) src;
					String sequenceName = (String) cb.getSelectedItem();
					switch (sequenceName) {
						case "Counting Numbers":
							indexArrayList = countingNumber();
							 {
								// 0:counting number, 1:Triangular Number, 2:Square Number, 3:fanonacci Nummber, 4:fanonacci Nummber Pattern
								colorStatus[0] = true;
								colorStatus[1] = false;
								colorStatus[2] = false;
								colorStatus[3] = false;
								colorStatus[4] = false;

								view.setColor(2);
								updateColor(indexArrayList);

							}
							break;
						case "Triangular Numbers":
							indexArrayList = triangularNumber();
							 {
								colorStatus[0] = false;
								colorStatus[1] = true;
								colorStatus[2] = false;
								colorStatus[3] = false;
								colorStatus[4] = false;
								view.setColor(4);
								updateColor(indexArrayList);

							}
							break;
						case "Square Numbers":
							indexArrayList = squareNumner();

							 {
								colorStatus[0] = false;
								colorStatus[1] = false;
								colorStatus[2] = true;
								colorStatus[3] = false;
								colorStatus[4] = false;
								view.setColor(3);
								updateColor(indexArrayList);

							}

							break;
						case "Fibonacci Numbers":
							colorStatus[0] = false;
							colorStatus[1] = false;
							colorStatus[2] = false;
							colorStatus[3] = true;
							colorStatus[4] = false;
							indexArrayList = fibonacciNumber();
							view.setColor(3);
							updateColor(indexArrayList);
							break;
						case "Fibonacci Number Pattern":
							colorStatus[0] = false;
							colorStatus[1] = false;
							colorStatus[2] = false;
							colorStatus[3] = false;
							colorStatus[4] = true;
							indexArrayList = fibonacciPattern();
							view.setColor(1);
							updateColor(indexArrayList);
							break;
						case "Clear Color":
							colorStatus[0] = false;
							colorStatus[1] = false;
							colorStatus[2] = false;
							colorStatus[3] = false;
							colorStatus[4] = false;
							view.eraseColor();
							view.setColor(0);
							break;
					}
				} catch (BadLocationException ex) {
					Logger.getLogger(PascalTriangleController.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		} // actionPerformed
		catch (BadLocationException ex) {
			Logger.getLogger(PascalTriangleController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void updateColor(ArrayList<Integer> index) throws BadLocationException {
		view.colorNumber(index);
	}

	public void clearColor() throws BadLocationException {
		view.eraseColor();
	}

	public ArrayList<Integer> countingNumber() {
		indexArrayList.clear();
		for (int j = 1; j < model.getRow(); ++j) {
			int indexLeft = j * (j + 1) / 2 + 1;
			int indexRight = j * (j + 1) / 2 + j - 1;
			indexArrayList.add(indexLeft);
			indexArrayList.add(indexRight);
		}
		return indexArrayList;
	}

	public ArrayList<Integer> triangularNumber() {
		indexArrayList.clear();
		for (int j = 2; j < model.getRow(); ++j) {
			int indexLeft = j * (j + 1) / 2 + (j - 2);
			int indexRight = j * (j + 1) / 2 + 2;
			indexArrayList.add(indexLeft);
			indexArrayList.add(indexRight);
		}
		return indexArrayList;
	}

	public ArrayList<Integer> squareNumner() {
		indexArrayList.clear();
		for (int j = 1; j < model.getRow(); ++j) {
			int sqrt = (int) Math.sqrt(j);
			if (sqrt * sqrt == j) {
				int indexLeft = j * (j + 1) / 2 + 1;
				int indexRight = j * (j + 1) / 2 + j - 1;
				indexArrayList.add(indexLeft);
				indexArrayList.add(indexRight);
			}
		}
		return indexArrayList;
	}

	public ArrayList<Integer> fibonacciNumber() {
		indexArrayList.clear();
		indexArrayList.add(0);
		for (int i = 1; i < model.getRow(); ++i) {
			if (checkFibonacci(i)) {

				indexArrayList.add(getRowIndex(i) + 1);
				indexArrayList.add(getRowIndex(i + 1) - 2);
			}
		}
		return indexArrayList;
	}

	public ArrayList<Integer> fibonacciPattern() {
		indexArrayList.clear();
		ArrayList<Integer> tmpArrayList = new ArrayList<>();
		tmpArrayList.add(0);
		indexArrayList.add(0);
		for (int i = 1; i < model.getRow(); ++i) {
			int first = tmpArrayList.get(0) + i;
			indexArrayList.add(first);
			tmpArrayList.set(0, first);
			for (int j = 1; j < i / 2 + 1; ++j) {
				int next = tmpArrayList.get(j - 1) - (i - j);
				tmpArrayList.add(j, next);
				indexArrayList.add(next);
			}
		}
		return indexArrayList;
	}

	private boolean isPerfectSquare(int x) {
		int s = (int) sqrt(x);
		return (s * s == x);
	}

	private boolean checkFibonacci(int n) {
		return isPerfectSquare(5 * n * n + 4)
				|| isPerfectSquare(5 * n * n - 4);
	}

	public int getRowIndex(int row) {
		return row * (row + 1) / 2;
	} // getRowIndex

} // PascalTriangleController
