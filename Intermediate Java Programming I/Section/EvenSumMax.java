import java.util.*;

public class EvenSumMax {

   public static void main (String[] args) {
      Scanner console = new Scanner(System.in);
      
      getValues(console);
      
   }
   
   public static void getValues(Scanner console) {
    
      System.out.print("how many integers? ");
      int nums = console.nextInt();
      int sum = 0
      int max = 0
      for (int i = 0; i < nums; i++) {
         System.out.print("next integer?")
         int current = console.nextInt();
         if (current % 2 == 0) {
            sum += current; // sum = sum + current
            max = Math.max(max, current);
         }
         
      }
      System.out.println("even sum = " + sum);
      System.out.println("even max = " + max);
   }
}
            