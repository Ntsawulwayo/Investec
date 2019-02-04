package za.co.psybergate.sifiso.highestCommonFactor;

/**
 *
 * @author SIFISO
 */
public class HighestCommonFactorOfNumbers {

    public int findHighestCommonFactor(int[] numbers){ 
      int arraySize = numbers.length;
      int result = numbers[0];
      int i = 1;
    
      while(i<arraySize)
      {
          if(isDivisible(result,numbers[i])) {
            i++;
          }
          else {
            result = numbers[i]%result;
            i++;
          }
      }
      return result;
    }
    
    private boolean isDivisible(int numerator, int denominator) {
        return denominator%numerator==0;
    }
    
    //Test highest common factor
    public static void main(String args[]) {
        
        HighestCommonFactorOfNumbers hfc = new HighestCommonFactorOfNumbers();
        int[] numbers = {5, 25, 50, 125, 625};   
     
        System.out.println("Highest Common Factor is: "+hfc.findHighestCommonFactor(numbers));
   }
}
