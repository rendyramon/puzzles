public class MultiplyWithOperator{
  public int multiply(int numOne, int numTwo){
    int divider = numOne > numTwo? numTwo : numOne;
    int multipler = numOne > numTwo? numOne : numTwo;
    
    int product = 0;
    for(int i = 0; i < divider; i++){
      product += multipler;
    }
    return product;
  }

  public int multiplyBitwise(int numOne, int numTwo){
    int divider = numOne > numTwo? numTwo : numOne;
    int multipler = numOne > numTwo? numOne : numTwo;
    int original = multipler;
    int result = 0;
    
    while(divider != 1){
      if((divider & 01) == 1){
        divider--;
        result += original;
      }
      divider >>= 1;
      multipler <<= 1;
    }
    return result + multipler;
  }

  public static void main(String[] args){
    MultiplyWithOperator withoutOp = new MultiplyWithOperator();
    System.out.println(withoutOp.multiplyBitwise(5,3));
  }
}