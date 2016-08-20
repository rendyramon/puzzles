import java.util.*;

public class RobotInAGrid{
  private Map<Point, Integer> visited;

  public RobotInAGrid(){
    visited = new HashMap<>();
  }

  public static class Plane{
    public final int rows;
    public final int columns;
    public final int[][] matrix;

    public Plane(int rows, int columns, int[][] matrix){
      this.rows = rows;
      this.columns = columns;
      this.matrix = matrix;
    }
  }

  public static class Point{
    public final int steps;

    private final int row;
    private final int column;
    private final Plane plane;
    private final int value;
    private Point parent;

    public Point(int row, int column, Point parent, int steps, Plane plane){
      this.row = row;
      this.column = column;
      this.parent = parent;
      this.steps = steps;
      this.plane = plane;
      value = plane.matrix[row][column];
    }

    @Override
    public boolean equals(Object object){
      if(object == null) return false;
      Point point = (Point) object;
      return point.row == row && point.column == column;
    }

    public String toString(){
      return "("+row+","+column+")";
    }

    public void printPath(){
      if(parent != null){
        parent.printPath();
        System.out.print("-->");
      }
      System.out.print(this);
    }

    public boolean hasRight(){
      return column + 1 < plane.columns;
    }

    public boolean hasDown(){
      return row + 1 < plane.rows;
    }

    public boolean isObstacle(){
      return value == -1;
    }

    public Point right(){
      return new Point(row, column+1, this, steps+1, plane);
    }

    public Point down(){
      return new Point(row+1, column, this, steps+1, plane);
    }
  }

  public void printRobotPath(int[][] matrix, int r, int c){
    Queue<Point> pointQueue = new LinkedList<>();
    Plane plane = new Plane(r, c, matrix);
    Point head = new Point(0, 0, null, 0, plane);
    Point destination = new Point(r-1, c-1, null, 0, plane);

    pointQueue.add(head);

    while(!pointQueue.isEmpty()){
      head = pointQueue.remove();
      if(head.equals(destination)){
        head.printPath();
        return;
      }

      if(head.hasRight()){
        Point right = head.right();
        if(!right.isObstacle()){
          if(visited.containsKey(right)){
            int prevRightSteps = visited.get(right);
            if(prevRightSteps > right.steps){
              pointQueue.add(right);
              visited.put(right, right.steps);
            }
          } else{
            visited.put(right, right.steps);
            pointQueue.add(right);
          }
        }
      }

      if(head.hasDown()){
        Point down = head.down();
        if(!down.isObstacle()){
          if(visited.containsKey(down)){
            int prevDownSteps = visited.get(down);
            if(prevDownSteps > down.steps){
              pointQueue.add(down);
              visited.put(down, down.steps);
            }
          } else{
            visited.put(down, down.steps);
            pointQueue.add(down);
          }
        }
      }
    }
  }
  public static void main(String[] args){
    RobotInAGrid robotGrid = new RobotInAGrid();
    int[][] matrix = new int[][] {
        {1,   2,  3,  -1,  5},
        {6,   -1,  8,  9, 10},
        {11, 12, 13, -1, 15},
        {16, -1, 18, 19, 20}
     };
    robotGrid.printRobotPath(matrix, matrix.length, matrix[0].length);
    System.out.println();
  }
}