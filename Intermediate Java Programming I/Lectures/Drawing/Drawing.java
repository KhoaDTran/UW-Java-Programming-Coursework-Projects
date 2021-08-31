// This program draws a rudimentary van using DrawingPanel.
// Note the import statement and the use of the Color and Graphics classes.
//
// You must download DrawingPanel.java from the course website and save it
//   in the same folder as this file to run this program.
import java.awt.*;

public class Drawing {
   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(400, 200);
      panel.setBackground(Color.LIGHT_GRAY);
      Graphics brush = panel.getGraphics();
      
      drawVan(brush, 10, 30);
      drawVan(brush, 10, 130);
   }

   // draw a single van
   //
   // Graphics pen - the Graphics object to use when drawing
   
   public static void drawVan(Graphics pen, int x, int y) {
      // draw body
      pen.setColor(Color.BLACK);
      pen.fillRect(x, y, 100, 50);
      
      // draw wheels
      pen.setColor(Color.RED);
      pen.fillOval(x + 10 , x+ 40, 20, 20);
      pen.fillOval(x + 70, x + 40, 20, 20);
      
      // draw windshield
      pen.setColor(Color.CYAN);
      pen.fillRect(x + 70, x + 10, 30, 20);
   }
}