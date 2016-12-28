package pascaltriangle;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;

class PascalTriangleView {

	private final Button btnAddRow;
	private final Button btnRemoveRow;
	private final JTextPane textPane;
	private final JComboBox choiceList;

	//store the position of each character in the screen
	private final ArrayList<Integer> position;

	//store the numbers that the view has drawn on the pan
	private final ArrayList<Integer> value;
	//store the numbers that has been colored 
	private final ArrayList<Integer> coloredNumber;
	static int offset;
	int rowNumber;
	int color;

	PascalTriangleView() {
		// Create frame for UI
		JFrame frame = new JFrame("Pascal's Triangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add("North", new Label("Click the buttons below to add or remove rows."));
		String[] choiceStrings = {"Counting Numbers", "Triangular Numbers", "Square Numbers", "Fibonacci Numbers", "Fibonacci Number Pattern", "Clear Color"};

		// Create panel for buttons
		Panel panel = new Panel();
		btnAddRow = new Button("Add Row");
		btnRemoveRow = new Button("Remove Row");
		//btnTriangleNumbers = new Button("Triangular");

		choiceList = new JComboBox(choiceStrings);
		//choiceList.setSelectedIndex(4);

		panel.add(btnAddRow);
		panel.add(btnRemoveRow);
		panel.add(choiceList);
		frame.add("South", panel);

		// Create text pane to display Pascal's Triangle
		textPane = new JTextPane();
		textPane.setEditable(false);
		StyledDocument doc = textPane.getStyledDocument();

		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		frame.add("Center", textPane);

		// Set the size and location of the frame on the screen.  Also make
		// the frame visible
		frame.setSize(600, 400);
		frame.setLocation(100, 100);
		frame.setVisible(true);
		position = new ArrayList<>();
		value = new ArrayList<>();
		coloredNumber = new ArrayList<>();
	} // PascalTriangleView

	public void addController(ActionListener controller) {
		btnAddRow.addActionListener(controller);
		btnRemoveRow.addActionListener(controller);
		choiceList.addActionListener(controller);
	}

	public void setColor(int newColor) {
		color = newColor;
	}

	public void colorNumber(ArrayList<Integer> indexArrayList) throws BadLocationException {
		eraseColor();
		StyleContext context = new StyleContext();
		Style style = context.addStyle("color", null);
		switch (color) {
			case 0:
				StyleConstants.setForeground(style, Color.BLACK);
				break;
			case 1:
				StyleConstants.setForeground(style, Color.RED);
				break;
			case 2:
				StyleConstants.setForeground(style, Color.ORANGE);
				break;
			case 3:
				StyleConstants.setForeground(style, Color.BLUE);
				break;
			case 4:
				StyleConstants.setForeground(style, Color.cyan);
				break;
		}
		if (position.size() > 0) {
			for (int i = 0; i < indexArrayList.size(); ++i) {
				if (indexArrayList.size() > i && position.size() > indexArrayList.get(i)) {
					int colorOffset = position.get(indexArrayList.get(i));
					textPane.getStyledDocument().remove(colorOffset, value.get(indexArrayList.get(i)).toString().length());
					textPane.getStyledDocument().insertString(colorOffset, value.get(indexArrayList.get(i)).toString(), style);
					coloredNumber.add(indexArrayList.get(i));
				}
			}
		}
	}

	public void eraseColor() throws BadLocationException {
		StyleContext context = new StyleContext();
		Style style = context.addStyle("color", null);
		StyleConstants.setForeground(style, Color.BLACK);
		for (int i = 0; i < coloredNumber.size(); ++i) {
			int colorOffset = position.get(coloredNumber.get(i));
			textPane.getStyledDocument().remove(colorOffset, value.get(coloredNumber.get(i)).toString().length());
			textPane.getStyledDocument().insertString(colorOffset, value.get(coloredNumber.get(i)).toString(), style);
		}
		coloredNumber.clear();
	}

	public void drawRow(ArrayList<Integer> rowDataArrayList) {
		StyleContext context = new StyleContext();
		Style style = context.addStyle("color", null);

		for (int i = 0; i < rowDataArrayList.size(); ++i) {
			position.add(offset);
			String s = rowDataArrayList.get(i).toString();
			try {
				textPane.getStyledDocument().insertString(offset, s, style);
			} catch (BadLocationException ex) {
				Logger.getLogger(PascalTriangleView.class.getName()).log(Level.SEVERE, null, ex);
			}
			offset += rowDataArrayList.get(i).toString().length();

			try {
				textPane.getStyledDocument().insertString(offset, "    ", null);
			} catch (BadLocationException ex) {
				Logger.getLogger(PascalTriangleView.class.getName()).log(Level.SEVERE, null, ex);
			}
			offset += 4;
		}
		try {
			textPane.getStyledDocument().insertString(offset, "\n", null);
		} catch (BadLocationException ex) {
			Logger.getLogger(PascalTriangleView.class.getName()).log(Level.SEVERE, null, ex);
		}
		offset += 1;

		++rowNumber;

		for (int j = 0; j < rowDataArrayList.size(); ++j) {
			value.add(rowDataArrayList.get(j));
		}
	}

	public void eraseRow() throws BadLocationException {
		if (rowNumber > 0) {
			int begin = position.size() - rowNumber;
			int end = position.size() - 1;

			int beginOffset = position.get(begin);
			int endOffset = position.get(end);
			int length = endOffset - beginOffset + 1;
			textPane.getDocument().remove(beginOffset, length);
			offset = beginOffset;
			position.subList(begin, end + 1).clear();

			--rowNumber;
		}
	}
} // PascalTriangleView

