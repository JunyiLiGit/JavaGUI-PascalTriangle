package pascaltriangle;

// Abstract class for Pascal Triangle Models.

import java.util.ArrayList;

// It is also derived from Observable so will notify observers
// when the data changes

public abstract class PascalTriangleModel {

  
  private final ArrayList<Integer> dataindexToControllerArrayList; 
  protected int numRows;
  
  abstract public ArrayList<Integer> addRow();
  abstract public void removeRow();
  
  
  public PascalTriangleModel() {
     dataindexToControllerArrayList = new ArrayList<>();
   }
  
  
  public int getRow(){
  
    return numRows;
  }
  
  


  
  
  

} // PascalTriangleModel
