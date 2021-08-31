import java.awt.*;

public class Squares {

   public static void main(String[] args) {
      DrawingPanel p = new DrawingPanel (300, 200);
      p.setBackground(Color.CYAN);
      Graphics g = p.getGraphics();
      drawFigure(g);
      
   }
   
   public static void drawFigure(Graphics g) {
      g.setColor(Color.RED);
      for (int i = 0; i < 5; i++) {
         g.drawRect(50, 50, 20*i+ 20, 20*i +20);
      }
      
      g.setColor(Color.BLACK);
      g.drawLine(50, 50, 150, 150);
      
   }
}