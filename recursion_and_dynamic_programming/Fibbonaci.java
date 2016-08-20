import java.util.*;

public class Fibbonaci{
  private final Map<Integer, Integer> fibMap;
  
  public Fibbonaci(){
    fibMap = new HashMap<>();
  }

  public int fibbonaci(int num){
    if(num < 0) return -1;
    if(num == 0) return 0;
    if(num == 1) return 1;
    
    int fib1, fib2;
    fib2 = fibMap.containsKey(num-2)? fibMap.get(num-2) : fibbonaci(num-2);
    fibMap.put(num-2, fib2);
    fib1 = fibMap.containsKey(num-1)? fibMap.get(num-1) : fibbonaci(num-1);
    fibMap.put(num-1, fib1);
    return fib1 + fib2;
  }

  public static void main(String[] args){
    System.out.println(new Fibbonaci().fibbonaci(20));
  }
}