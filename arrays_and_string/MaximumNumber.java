import java.util.*;

public class MaximumNumber{

  public static void main(String[] args){
    MaximumNumber maxFinder = new MaximumNumber();
    int[][][] tests = {
      {{3,4,6,5}, {9,1,2,5,8,3}, {5}},
      {{6,7}, {6,0,4}, {5}},
      {{3,9}, {8,9}, {3}}
    };
    for(int[][] test : tests){
      int[] result = maxFinder.createMaxNum(test[0], test[1], test[2][0]);
      for(int res : result){
        System.out.print(res+ " ");
      }
      System.out.println();
    }
  }

  public static class Element implements Comparable<Element>{
    int item;
    int position;
    int arr;

    public Element(int item, int position, int arr){
      this.item = item;
      this.position = position;
      this.arr = arr;
    }

    @Override
    public int compareTo(Element element){
      if(element.item > item) return 1;
      if(element.item < item) return -1;
      else return 0;
    }
  }

  public int[] createMaxNum(int[] nums1, int[] nums2, int k){
    Queue<Element> heap = new PriorityQueue();
    int[] answer = new int[k];
    for(int i = 0; i < nums1.length; i++)
      heap.add(new Element(nums1[i], i, 1));

    for(int i = 0; i < nums2.length; i++)
      heap.add(new Element(nums2[i], i, 2));

    int onePointer = 0;
    int twoPointer = 0;
    int currentTopDigit = 0;
    int skipCredit = nums1.length + nums2.length - k;
    while(currentTopDigit < k && (onePointer < nums1.length || twoPointer < nums2.length)){
      Element top = heap.remove();
      int topOne, topTwo;

      if(top.arr == 1){
        if(top.position > onePointer){
          if(skipCredit >= top.position - onePointer){
            skipCredit -= (top.position - onePointer);
            onePointer = top.position;

            answer[currentTopDigit] = top.item;
            currentTopDigit++;
            onePointer++;
          } else{
            while(onePointer < nums1.length || twoPointer < nums2.length){
              topOne = -1;
              topTwo = -1;
              if(onePointer < nums1.length) topOne= nums1[onePointer];
              if(twoPointer < nums2.length) topTwo = nums2[twoPointer];
              if(topOne ==-1 && topTwo == -1) return answer;
              if(topOne >= topTwo){
                answer[currentTopDigit] = topOne;
                currentTopDigit++;
                onePointer++;
              }else{
                answer[currentTopDigit] = topTwo;
                currentTopDigit++;
                twoPointer++;
              }
            }
          }
        } else if(top.position == onePointer){
          answer[currentTopDigit] = top.item;
          currentTopDigit++;
          onePointer++;
        }
      }

      if(top.arr == 2){
        if(top.position > twoPointer){
          if(skipCredit >= top.position - twoPointer){
            skipCredit -= (top.position - twoPointer);
            twoPointer = top.position;

            answer[currentTopDigit] = top.item;
            currentTopDigit++;
            twoPointer++;
          } else{
            while(onePointer < nums1.length || twoPointer < nums2.length){
              topOne = -1;
              topTwo = -1;
              if(onePointer < nums1.length) topOne= nums1[onePointer];
              if(twoPointer < nums2.length) topTwo = nums2[twoPointer];
              if(topOne ==-1 && topTwo == -1) return answer;
              topTwo = nums2[twoPointer];
              if(topOne > topTwo){
                answer[currentTopDigit] = topOne;
                currentTopDigit++;
                onePointer++;
              } else{
                answer[currentTopDigit] = topTwo;
                currentTopDigit++;
                twoPointer++;
              }
          }
        }
      } else if(top.position == twoPointer){
          answer[currentTopDigit] = top.item;
          currentTopDigit++;
          twoPointer++;
        }
    }
  }
    return answer;
  }
}