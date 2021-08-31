// Khoa Tran
// 10/15/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #3: Cafe Wall

// Outputs a drawing of a TV with an image in the middle.

import java.awt.*;

public class Doodle {
   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(200, 200);
      panel.setBackground(Color.CYAN);
      Graphics brush = panel.getGraphics();
      
      drawTV(brush);
      drawPicture(brush);
   }
   
   // Draw a TV.
   public static void drawTV (Graphics brush) {
         
      // Draw outter box.
      brush.setColor(Color.BLACK);
      brush.fillRect(60, 40, 80, 50);
      
      // Draw inner box.
      brush.setColor(Color.LIGHT_GRAY);
      brush.fillRect(65, 45, 70, 40);
      
      // Draw TV stand.
      brush.setColor(Color.DARK_GRAY);
      brush.fillRect(80,90,40,10);
   }
      
  public static void drawPicture (Graphics brush) {
         
      // Draw triangle.
      brush.setColor(Color.BLACK);
      brush.drawLine(85, 78, 98, 53);
      brush.drawLine(98, 53, 112, 77);
      brush.drawLine(85, 78, 112, 77);
      
      // Draw circle.
      brush.setColor(Color.GREEN);
      brush.fillOval(94,65,10,10);

   }
}
      
