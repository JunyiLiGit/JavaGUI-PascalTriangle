package pascaltriangle;

import java.util.ArrayList;
public class PascalTriangleBeapModel extends PascalTriangleModel {
	private Node root;
	private final ArrayList<Integer> dataToControllerArrayList;

	private static class Node {

		private Node leftParent;
		private Node rightParent;
		private Node leftChild;
		private Node rightChild;
		private int data;

		Node(int newData) {
			leftParent = null;
			rightParent = null;
			leftChild = null;
			rightChild = null;
			data = newData;
		}

		public void setData(int newData) {
			data = newData;
		}

		public int getData() {
			return data;
		}
	}

	public PascalTriangleBeapModel() {
		dataToControllerArrayList = new ArrayList<>();
	}

	//add row to the beap
	@Override
	public ArrayList<Integer> addRow() {
		if (numRows == 0) {
			root = new Node(1);
			dataToControllerArrayList.add(root.data);
			numRows = numRows + 1;
		} else {
			Node n = root;
			Node m = root;
			while (n.leftChild != null) {
				n = n.leftChild;
			}
			while (m.rightChild != null) {
				m = m.rightChild;
			}
			Node leftMost = new Node(1);
			Node rightMost = new Node(1);
			n.leftChild = leftMost;
			leftMost.rightParent = n;
			m.rightChild = rightMost;
			rightMost.leftParent = m;
			Node start = leftMost;
			Node end = rightMost;
			if (numRows == 2) {
				int newData = m.data + n.data;
				Node newNode = new Node(newData);
				m.leftChild = newNode;
				n.rightChild = newNode;
				newNode.leftParent = n;
				newNode.rightParent = m;
			}
			for (int i = 0; i < Math.ceil((float) (numRows - 2) / 2.0); ++i) {
				Node p = n;
				Node q = m;

				n = n.rightParent.rightChild;
				m = m.leftParent.leftChild;

				if (n.rightParent == m.leftParent && numRows > 2) {
					int newDataL = n.data + p.data;
					Node newNodeL = new Node(newDataL);
					n.leftChild = newNodeL;
					p.rightChild = newNodeL;
					newNodeL.leftParent = p;
					newNodeL.rightParent = n;

					int newDataM = n.data + m.data;
					Node newNodeM = new Node(newDataM);
					n.rightChild = newNodeM;
					m.leftChild = newNodeM;
					newNodeM.leftParent = n;
					newNodeM.rightParent = m;

					int newDataR = m.data + q.data;
					Node newNodeR = new Node(newDataR);
					m.rightChild = newNodeR;
					q.leftChild = newNodeR;
					newNodeR.leftParent = m;
					newNodeR.rightParent = q;
				} else if (n == m) {
					int newData1 = p.data + n.data;
					Node newNode1 = new Node(newData1);
					p.rightChild = newNode1;
					n.leftChild = newNode1;
					newNode1.leftParent = p;
					newNode1.rightParent = n;

					int newData2 = q.data + m.data;
					Node newNode2 = new Node(newData2);
					q.leftChild = newNode2;
					m.rightChild = newNode2;
					newNode2.leftParent = m;
					newNode2.rightParent = q;
				} else {
					int newLeftData = p.data + n.data;
					Node newLefNode = new Node(newLeftData);
					p.rightChild = newLefNode;
					n.leftChild = newLefNode;
					newLefNode.leftParent = p;
					newLefNode.rightParent = n;

					int newRightData = q.data + m.data;
					Node newRightNode = new Node(newRightData);
					m.rightChild = newRightNode;
					q.leftChild = newRightNode;
					newRightNode.leftParent = m;
					newRightNode.rightParent = q;
				}
			}
			numRows = numRows + 1;
			dataToControllerArrayList.clear();
			StoreNodeIntoArrayList(start, end);
		}
		return dataToControllerArrayList;
	}

	public ArrayList<Integer> StoreNodeIntoArrayList(Node start, Node end) {
		while (start != end) {
			dataToControllerArrayList.add(start.data);
			start = start.rightParent.rightChild;
		}
		dataToControllerArrayList.add(end.data);
		return dataToControllerArrayList;
	}

	@Override
	public void removeRow() {
		if (numRows > 0) {
			--numRows;
			Node p = root;
			Node q = root;
			while (p.leftChild != null) {
				p = p.leftChild;
			}
			while (p != null) {
				p.setData(0);

				if (p.rightParent != null) {
					p = p.rightParent.rightChild;
				} else {
					p = null;
				}
			}
			while (q.leftChild != null) {
				q = q.leftChild;
			}
			if (q != null) {
				Node m = q.rightParent;
				dataToControllerArrayList.clear();
				while (m != null) {
					dataToControllerArrayList.add(m.getData());
					m.leftChild = null;
					m.rightChild = null;
					if (m.rightParent != null) {
						m = m.rightParent.rightChild;
					} else {
						m = null;
					}
				}
			}
		}
	}
}
