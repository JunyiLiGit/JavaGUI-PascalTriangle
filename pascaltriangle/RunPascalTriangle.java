package pascaltriangle;


public class RunPascalTriangle {
  private final int num_rows;

  public RunPascalTriangle() {
      num_rows = 10;
      //PascalTriangleModel model = new PascalTriangleArrayListModel();
     //PascalTriangleModel model = new PascalTriangleBeapModel();
    PascalTriangleModel model = new PascalTriangleArrayListInArrayListModel();
    PascalTriangleView view = new PascalTriangleView();

   

    PascalTriangleController controller = new PascalTriangleController();
    controller.addModel(model);
    controller.addView(view);
    controller.initView(num_rows);

    view.addController(controller);

  } // RunPascalTriangle

  public static void main(String [] args) {
      RunPascalTriangle runPascalTriangle;
      runPascalTriangle = new RunPascalTriangle();
  } // main
} // RunPascalTriangle
