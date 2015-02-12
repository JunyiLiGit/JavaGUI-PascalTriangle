package pascaltriangle;

import java.util.ArrayList;


public final class PascalTriangleArrayListModel extends PascalTriangleModel {
  private final ArrayList<Integer> ptArrayList; 
  private final ArrayList<Integer> rowBeginArrayList;
  private final ArrayList<Integer> dataToControllerArrayList;



  public PascalTriangleArrayListModel() 
  {
      rowBeginArrayList = new ArrayList<>();
      ptArrayList = new ArrayList<>();
      dataToControllerArrayList = new ArrayList<>();

  } // PascalTriangleVectorModel

  
  
    /**
     * Adds a new row to the triangle
     * @return
     */
    @Override
  public ArrayList<Integer> addRow() 
  {
      
    if(numRows == 0){
      ptArrayList.add(0, 1);
      ++numRows;
      dataToControllerArrayList.add(1);
    
    } else if(numRows > 0){
       
        int begin = getRowIndex(numRows-1);
        int end = begin + numRows-1;
        int nbegin = end + 1;
        int nend = nbegin + numRows; 
        ptArrayList.add(nbegin, 1);
        dataToControllerArrayList.clear();
        dataToControllerArrayList.add(0, 1);
        
        int c = 1;
        for (int i = begin; i < end; ++i) {
      
            Integer value = ptArrayList.get(i) + ptArrayList.get(i+1);        
            ptArrayList.add((nbegin+c), value); 
            dataToControllerArrayList.add(value);
            c++;
        }
    
        ptArrayList.add(nend, 1);
        dataToControllerArrayList.add(1);
        ++numRows;
       
        }
    
    return dataToControllerArrayList;

  } 
  
  
  @Override
  public void removeRow() 
  {
    if (numRows > 0) {

       --numRows;
      ptArrayList.subList(getNumNodes(numRows), ptArrayList.size()).clear();
      
    }
    
   dataToControllerArrayList.clear();

  } 
   
  private int getNumNodes(int row) 
  {
    int sum = row;
    for (int i = 0; i < row; ++i) {
      sum += i;
    }
    return sum;
  } // getNumNodes
  
  public void setRowBeginIndexArray()
  {
      
    rowBeginArrayList.clear();
    for(int i =0; i<numRows;++i){
       rowBeginArrayList.add(getRowIndex(i));
    }
  
  }
  
  
  public int getRowIndex(int row) {
    int index = 0;

    for (int i = 0; i <= row; ++i) {
      index += i;
    }
    return index;
  } // getRowIndex
  

   
}

