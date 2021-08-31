public class Figure {

   public static void main(String[] args} {
      drawFigure();
   }
   
   public static void drawFigure();
   
   //outer loop controls line
   for (int line =1; line <=5; line++) {
   
      //print forward slashes
      for (int i = 1; i <= -4*line + 20; i++) {
         System.out.print("/");
      }
      
      //print stars
      for (int i = 1; i <= 8*line -8; i++) {
         System.out.print("*");
      }
      
      //print backslahes
      for (int i = 1; i <= -4*line + 20; i++) {
         System.out.print("\");
      }
      System.out.println();
   }
   }
}