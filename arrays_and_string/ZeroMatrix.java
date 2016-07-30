import java.util.*;
public class ZeroMatrix{

  private class Point{
    private final int x;
    private final int y;

    public Point(int x, int y){
      this.x = x;
      this.y = y;
    }
  }

  public int[][] setZeroColumn(int[][] matrix, int y, int columnsNum){
    for(int i = 0; i < columnsNum; i++) {
      matrix[y][i] = 0;
    }
    return matrix;
  }
  
  public int[][] setZeroRow(int[][] matrix, int x, int rowsNum){
    for(int i = 0; i < rowsNum; i++) {
      matrix[i][x] = 0;
    }
    return matrix;
  }

  public int[][] setZero(int[][] matrix) {
    List<Point> zeroList = new ArrayList<>();
    int columnsNum = matrix[0].length;
    int rowsNum = matrix.length;

    for(int i = 0; i < rowsNum; i++)
      for(int j = 0; j < columnsNum; j++){
        if(matrix[i][j] == 0){
          zeroList.add(new Point(i, j));
          break;
        }
      }

    for(Point point : zeroList) {
      setZeroRow(matrix, point.y, rowsNum);
      setZeroColumn(matrix, point.x, columnsNum);
    }

    return matrix;
  }

  public static void main(String[] args){
    ZeroMatrix zeroMatrix = new ZeroMatrix();
    int[][] matrix = new int[][] {
          {1,   2,  3,  4,  5},
          {6,   7,  0,  9, 10},
          {11, 12, 13, 14, 15},
          {16, 17, 18, 19, 20}
       };
    matrix = zeroMatrix.setZero(matrix);
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[0].length; j++)
        System.out.print(matrix[i][j]+ "\t");
      System.out.println();
    }
  }
}