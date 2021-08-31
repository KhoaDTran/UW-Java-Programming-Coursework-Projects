import java.awt.*;

public class MickeyBox {
   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(220, 150);
      panel.setBackground(Color.YELLOW);
      Graphics brush = panel.getGraphics();
      
      drawMickey(brush);
   }

   public static void drawMickey (Graphics pen) {
         
      // draw Ovals
      pen.setColor(Color.BLUE);
      pen.fillOval(50, 25, 40, 40);
      pen.fillOval(130, 25, 40 , 40);
      
      // draw body
      pen.setColor(Color.RED);
      pen.fillRect(70, 45, 80, 80);
      
      // draw line
      pen.setColor(Color.BLACK);
      pen.drawLine(70,85,150,85);
   }
}
 











