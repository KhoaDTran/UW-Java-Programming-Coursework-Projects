 // Khoa Tran
// 10/08/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #2: Rocket Ship

// Outputs a drawing of the rocket with the ability to change the scale of the rocket to any size desired.

public class DrawRocket {
   public static final int SIZE = 6;
   
   public static void main(String[] args) {
      top();
      lineBreak();
      topDiamond();
      bottomDiamond();
      lineBreak();
      bottomDiamond();
      topDiamond();
      lineBreak();
      top();
   }
   
   // Prints the top nose drawing of the rocket.
   public static void top() {
      for (int line = 1; line <= SIZE + SIZE - 1; line++) {
         for (int space = 1; space <= -1 * line + (SIZE * 2); space++) {
            System.out.print(" ");
         }
         for (int fSlash = 1; fSlash <= 1 * line; fSlash++) {
            System.out.print("/");
         }
         System.out.print("**");
         for (int bSlash = 1; bSlash <= 1 * line; bSlash++) {
            System.out.print("\\");
         }
         System.out.println();
      }
   }
   
   // Prints the line break in between the body and the nose of the rocket.
   public static void lineBreak() {
      System.out.print("+");
      for (int equalStar = 1; equalStar <= SIZE *2; equalStar++) {
         System.out.print("=*");
      }
      System.out.print("+");
      System.out.println();
   }
   
   // Prints the top half of the diamond.
   public static void topDiamond() {
      for (int line = 1; line <= SIZE; line++) {
         System.out.print("|");
         for (int dots = 1; dots <= -1 * line + SIZE; dots++) {
            System.out.print(".");
         }
         for (int cone = 1; cone <= 1 * line; cone++) {
            System.out.print("/\\");
         }
         for (int dots = 1; dots <= -2 * line + (SIZE * 2); dots++) {
            System.out.print(".");
         }
         for (int cone = 1; cone <= 1 * line; cone++) {
            System.out.print("/\\");
         }
         for (int dots = 1; dots <= -1 * line + SIZE; dots++) {
            System.out.print(".");
         }
         System.out.print("|");
         System.out.println();
      }
   }
   
   // Prints the bottom half of the diamond.
   public static void bottomDiamond() {
      for (int line = 1; line <= SIZE; line++) {
         System.out.print("|");
         for (int dots = 1; dots <= 1 * line - 1; dots++) {
            System.out.print(".");
         }
         for (int cone = 1; cone <= -1 * line + (SIZE + 1); cone++) {
            System.out.print("\\/");
         }
         for (int dots = 1; dots <= 2 * line - 2; dots++) {
            System.out.print(".");
         }
         for (int cone = 1; cone <= -1 * line + (SIZE + 1); cone++) {
            System.out.print("\\/");
         }
         for (int dots = 1; dots <= 1 * line -1; dots++) {
            System.out.print(".");
         }
         System.out.print("|");
         System.out.println();
      }
   }
}