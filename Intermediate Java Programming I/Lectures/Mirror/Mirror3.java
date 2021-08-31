// This program produces an ASCII art drawing of a mirror.
//
// This final version introdues a class constant to allow us to 
//   easily print the mirror in different sizes.
public class Mirror3 {
   public static final int SIZE = 5;

   public static void main(String[] args) {
      drawLine();
      drawBodyTop();
      drawBodyBottom();
      drawLine();
   }
   
   // Prints a line that appears at the top and bottom of the mirror.
   public static void drawLine() {
      System.out.print("#");
      
      for (int line = 1; line <= SIZE * 4; line++) {
         System.out.print("=");
      }
      
      System.out.println("#");
   }
   
   // Prints the top half of the body of the mirror.
   public static void drawBodyTop() {
      for (int line = 1; line <= SIZE; line++) {
         System.out.print("|");
         
         for (int space = 1; space <= (-2 * line) + (2 * SIZE); space++) {
            System.out.print(" ");
         }
         
         System.out.print("<>");
         
         for (int dot = 1; dot <= 4 * line - 4; dot++) {
            System.out.print(".");
         }
         
         System.out.print("<>");
         
         for (int space = 1; space <= (-2 * line) + (2 * SIZE); space++) {
            System.out.print(" ");
         }
                  
         System.out.println("|");
      }
   }
   
   // Prints the bottom half of the body of the mirror.
   public static void drawBodyBottom() {
      for (int line = SIZE; line >= 1; line--) {
         System.out.print("|");
         
         for (int space = 1; space <= (-2 * line) + (2 * SIZE); space++) {
            System.out.print(" ");
         }
         
         System.out.print("<>");
         
         for (int dot = 1; dot <= 4 * line - 4; dot++) {
            System.out.print(".");
         }
         
         System.out.print("<>");
         
         for (int space = 1; space <= (-2 * line) + (2 * SIZE); space++) {
            System.out.print(" ");
         }
                  
         System.out.println("|");
      }
   }   
}