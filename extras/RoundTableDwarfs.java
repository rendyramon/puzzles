import java.util.*;

public class RoundTableDwarfs{
  public static class Cake{
    private int currentPickers;
    private double weight;

    public Cake(double weight){
      this.weight = weight;
      currentPickers = 0;
    }

    public Double getPotentialCakeWeight(){
      return weight/(currentPickers + 1);
    }

    public void pick(){
      currentPickers++;
    }
  }

  public void printPick(List<Cake> cakes){
    if(cakes == null) return;
    for(int idx = 0; idx < cakes.size(); idx++){
      // set left and right indices
      int leftCakeIdx = idx == 0? cakes.size()-1 : idx-1;
      int rightCakeIdx = idx;

      // make pick
      int comparision = cakes.get(leftCakeIdx).getPotentialCakeWeight.compareTo(cakes.get(rightCakeIdx));
      switch(comparision){
        case -1:
          cakes.get(rightCakeIdx).pick();
          System.out.println("right ");
        break;
        case 0:
          
        break;
        case 1:
          cakes.get(leftCakeIdx).pick();
          System.out.println("Left ");
        break;
      }
    }
  }

  public static void main(String[] args){
    String weightString = new Scanner(System.in).nextLine();
    List<Cake> cakes = Arrays.asList(weightString.split(" ")).stream()
                        .map(weight -> new Cake(Double.parseDouble(weight)))
                        .collect(Colletors.asList());
    
    printPick(cakes);
  }
}