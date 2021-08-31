// Khoa Tran
// 10/08/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #2

// Outputs an ascii art of a volcano.

public class AsciiArt {
   public static void main(String[] args) {
      top();
      bottom();
   }
   
   // Prints the top part of the volcano.
   public static void top() {
      for (int line = 1; line <= 5; line++) {
         for (int space = 1; space <= -1 * line + 7; space++) {
            System.out.print(" ");
         }
         for (int bar = 1; bar <= 2 * line + 2; bar++) {
            System.out.print("|");
         }
         System.out.println();
      }
   }
   
   // Prints the bottom part of the volcano.
   public static void bottom() {
      for (int line = 1; line <= 2; line++) {
         for (int star = 1; star <= 16; star++) {
            System.out.print("*");
         }
         System.out.println();
      }
   }
}

