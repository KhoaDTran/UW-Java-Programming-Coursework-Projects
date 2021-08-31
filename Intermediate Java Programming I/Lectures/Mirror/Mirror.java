public class Mirror {
   public static void main(String[] args) {
      drawBody();
   
   public static void drawBody() {
      for (int i = 1; i <= 8; i++) {
         System.out.print("|");
         System.out.print("<>");
         for (int j = 1; j <= 12; j++) {
            System.out.print(".");
         }
         System.out.print("<>");
         System.out.print("|");
      }
   }
}
   