// This programs tests an integer input by the user for various
//   fun properties.
//
// Notice the usage of the different if-else constructs.

import java.util.*;

public class FunWithNumbers {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      
      System.out.print("Enter an integer:");
      int number = console.nextInt();
      
      isPrime(number);
      isEvenOrOdd(number);
      isPosOrNeg(number);
      getFactors(number);
      nextHailstoneNum(number);
   }
   
   // Determines whether a given integer is prime.
   //
   // int num - the number to test
   public static void isPrime(int num) {
      if (numFactors(num) == 2) {
         System.out.println(num + " is prime");
      } 
   }
   
   // Computes and returns the number of factors of the given integer.
   //
   // int num - the integer whose factors are to be computed
   public static int numFactors(int num) {
      int count = 0;
      for (int i = 1; i <= num; i++) {
         if (num % i == 0) {
            count++;
         }
      }
      return count;
   }   
      
   // Determines whether a given integer is even or odd.
   //
   // int num - the number to test      
   public static void isEvenOrOdd(int num) {
      if (num % 2 == 0) {
         System.out.println(num + " is even");
      } else {    // num % 2 != 0
         System.out.println(num + " is odd");
      }
   }

   // Determines whether a given integer is positive or negative.
   //
   // int num - the number to test
   public static void isPosOrNeg(int num) {
      if (num > 0) {
         System.out.println(num + " is positive");
      } else if (num < 0) {
         System.out.println(num + " is negative");
      }
   }
   
   // Determines whether a given integer is a multiple of each single
   //   digit prime number.
   //
   // int num - the number to test   
   public static void getFactors(int num) {
      if (num % 2 == 0) {
         System.out.println(num + " is a multiple of 2");
      }
      if (num % 3 == 0) {
         System.out.println(num + " is a multiple of 3");
      }
      if (num % 5 == 0) {
         System.out.println(num + " is a multiple of 5");
      }
      if (num % 7 == 0) {
         System.out.println(num + " is a multiple of 7");
      }
   }
   
   // Computes the next value after the given value in the 
   ///  hailstone sequence. The hailstone sequences is defined
   //   as follows:
   //      - if the input is even, the next value is half the input (n / 2)
   //      - if the input is odd, the next value is three times the input 
   //        plus one (3n + 1)
   //
   // int num - the number to test   
   public static void nextHailstoneNum(int num) {
      if (num > 0) {
         System.out.print("The next hailstone number is ");
         if (num % 2 == 0) {
            System.out.println(num / 2);
         } else {    // num % 2 == 1
            System.out.println(3 * num + 1);
         }
      }
   }
}