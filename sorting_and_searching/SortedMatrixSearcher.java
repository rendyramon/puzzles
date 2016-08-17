public class SortedMatrixSearcher{
  public static class Plane{
    public final int columns;
    public final int rows;
    public Plane(int rows, int columns){
      this.columns = columns;
      this.rows = rows;
    }
  }

  public static class Coordinate implements Comparable<Coordinate>{
    public int x;
    public int y;
    private Plane plane;

    public Coordinate(Plane plane, int x, int y){
      this.x = x;
      this.y = y;
      this.plane = plane;
    }

    public Coordinate increment(){
      if( x== plane.rows-1 && y == plane.columns-1){}
      if(y == plane.rows-1){
        y = 0;
        x++;
      }
      else{
        y++;
      }
      return this;
    }

    public Coordinate decrement(){
      if( x== 0 && y == 0){}
      else if(y == 0){
        x--;
        y = plane.columns-1;
      }
      else{
        y--;
      }
      return this;
    }

    public Coordinate getMiddle(Coordinate coord){
      int middleRow = (this.x + coord.x)/2;
      int middleColumn = (this.y + coord.y)/2;
      return new Coordinate(plane, middleRow, middleColumn);
    }

    @Override
    public int compareTo(Coordinate coord){
      if(x > coord.x || (x == coord.x && y > coord.y)) return 1;
      if(x < coord.x || (x == coord.x && y < coord.y)) return -1;
      return 0;
    }

    @Override
    public String toString(){
      return "(" + x + ", " + y + ")";
    }
  }

  public Coordinate searchMatrix(int[][] matrix, int elem){
    Plane plane = new Plane(matrix[0].length, matrix.length);

    Coordinate startCoord = new Coordinate(plane, 0, 0);
    Coordinate endCoord = new Coordinate( plane, plane.rows-1, plane.columns-1);

    while(startCoord.compareTo(endCoord) <= 0){
      Coordinate middle = startCoord.getMiddle(endCoord);
      if(matrix[middle.x][middle.y] > elem){
        endCoord = middle.decrement();
      } else if(matrix[middle.x][middle.y] < elem){
        startCoord = middle.increment();
      } else{
        return middle;
      }
    }
    return null;
  }

  public static void main(String[] args){
    SortedMatrixSearcher matrixSearcher = new SortedMatrixSearcher();
    int[][] matrix = new int[][] {
          {1,   2,  3,  4,  5},
          {6,   7,  8,  9, 10},
          {11, 12, 13, 14, 15},
          {16, 17, 18, 19, 20}
       };
    System.out.println(matrixSearcher.searchMatrix(matrix, 19));
  }
}