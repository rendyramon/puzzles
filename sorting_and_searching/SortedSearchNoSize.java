import java.util.*;

public class SortedSearchNoSize{
  public static class Listy{
    private List<Integer> arr;

    public Listy(){
      this.arr = new ArrayList<>();
    }

    public int getAt(int i){
      if(arr.size() <= i) return -1;
      return arr.get(i);
    }

    public void insertAt(int i, int elem){
      arr.add(i, elem);
    }
  }
  public int findListy(int elem, Listy aList){
    int last = Integer.MAX_VALUE;
    int left = 0;

    while(left <= last){
      int middle = (left + last)/2;
      while(aList.getAt(middle) == -1){
        last = middle;
        middle = (left + last)/2;
      }
      int middleElem = aList.getAt(middle);
      if( middleElem > elem){
        last = middle-1;
      } else if(middleElem < elem){
        left = middle+1;
      } else{
        return middle;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    SortedSearchNoSize sortedNoSizeSearcher = new SortedSearchNoSize();
    Random rand = new Random();
    Listy aList = new Listy();
    List<Integer> sortedArr = new ArrayList<>();
    for(int i = 0; i < 1001; i++){
      sortedArr.add(rand.nextInt());
    }
    Collections.sort(sortedArr);
    for(int i = 0; i < 1001; i++){
      aList.insertAt(i, sortedArr.get(i));
    }
    System.out.println(sortedNoSizeSearcher.findListy(aList.getAt(1000), aList));
  }
}