import java.util.*;

public class Rotator{
  
  public int[][] rotateMatrix(int[][] matrix){
    int n = matrix[0].length;
    for(int column = 0; column < n/2; column++)
      for(int row = column; row < n - 1 - column; row++) {
        // get points
        int topPoint = matrix[row][column];
        int rightPoint = matrix[n-1-column][row];

        int bottomPoint = matrix[n-1-row][n-1-column];
        int leftPoint = matrix[column][n-1-row];
        
        // rotate
        matrix[n-1-column][row] = topPoint;
        matrix[n-1-row][n-1-column] = rightPoint;

        matrix[column][n-1-row] = bottomPoint;
        matrix[column][row] = leftPoint;
      }
    return matrix;
  }

  public static void main(String[] args){
    Rotator rotator = new Rotator();

    int[][] matrix = rotator.rotateMatrix(new int[][] {{1, 2, 3}, {4, 5, 6}, {7,8,9}});
    for(int i = 0; i < matrix[0].length; i++){
      for(int j = 0; j < matrix[0].length; j++)
        System.out.print(matrix[i][j]+ " ");
      System.out.println();
    }
  }
}