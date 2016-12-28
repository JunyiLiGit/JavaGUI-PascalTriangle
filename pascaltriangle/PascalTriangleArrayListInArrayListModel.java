package pascaltriangle;

import java.util.ArrayList;
public class PascalTriangleArrayListInArrayListModel extends PascalTriangleModel {
//ptArrayList: store Pascal's trangle 
	private final ArrayList<ArrayList<Integer>> ptArrayListInArrayList;
	//date that the controller needs to get for each action
	private final ArrayList<Integer> dataToControllerArrayList;

	public PascalTriangleArrayListInArrayListModel() {
		ptArrayListInArrayList = new ArrayList<>(30);
		dataToControllerArrayList = new ArrayList<>(30);
	}

	@Override
	public ArrayList<Integer> addRow() {
		if (numRows == 0) {
			ArrayList<Integer> p = new ArrayList<>(1);
			p.add(1);
			ptArrayListInArrayList.add(0, p);
			dataToControllerArrayList.add(1);
			numRows = numRows + 1;
		} else if (numRows > 0) {
			dataToControllerArrayList.clear();
			Integer leftOne = 1;
			Integer rightOne = 1;
			ArrayList<Integer> p = new ArrayList<>(numRows + 1);
			p.add(0, leftOne);
			dataToControllerArrayList.add(1);
			for (int i = 0; i < numRows - 1; ++i) {
				int pre = ptArrayListInArrayList.get(numRows - 1).get(i);
				int next = ptArrayListInArrayList.get(numRows - 1).get(i + 1);
				int current = pre + next;
				p.add(i + 1, current);
				dataToControllerArrayList.add(current);
			}
			p.add(numRows, rightOne);
			ptArrayListInArrayList.add(numRows, p);
			dataToControllerArrayList.add(1);
			numRows = numRows + 1;
		}
		return dataToControllerArrayList;
	}

	@Override
	public void removeRow() {
		if (numRows > 0) {
			--numRows;
			ptArrayListInArrayList.remove(ptArrayListInArrayList.size() - 1);
		}
		dataToControllerArrayList.clear();
	}
}
