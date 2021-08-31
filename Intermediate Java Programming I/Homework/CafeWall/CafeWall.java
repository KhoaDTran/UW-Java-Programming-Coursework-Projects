// Khoa Tran
// 10/15/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #3: Cafe Wall

// Outputs a series of rows and grids with
// different sizes, number of pairs, and offsets.

import java.awt.*;

public class CafeWall {
   // Mortar constant sets the gaps between the rows in a grid.
   public static final int MORTAR = 2;
   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(650, 400);
      panel.setBackground(Color.GRAY);
      Graphics brush = panel.getGraphics();
            
      drawRow(brush, 0, 0, 20, 4);
      drawRow(brush, 50, 70, 30, 5);
      drawGrid(brush, 10, 150, 25, 4, 0);
      drawGrid(brush, 250, 200, 25, 3, 10);
      drawGrid(brush, 425, 180, 20, 5, 10);
      drawGrid(brush, 400, 20, 35, 2, 35);
   }

   // Draw a row.
   public static void drawRow(Graphics brush, int x, int y, int size, int pairs) {
      for (int duplicate = 0; duplicate < pairs; duplicate++) {
         // Draw squares.
         brush.setColor(Color.BLACK);
         brush.fillRect(x + (duplicate * (size *2)), y, size, size);
         brush.setColor(Color.WHITE);
         brush.fillRect(x + size + (duplicate * (size * 2)), y, size, size);
         
         // Draw X.
         brush.setColor(Color.BLUE);
         brush.drawLine(x + (duplicate * (size *2)), y, x + (duplicate * (size *2)) + size, y + size);
         brush.drawLine(x + (duplicate * (size *2)), y + size, x + (duplicate * (size *2)) + size, y);         
      }
   }
   
   // Draw a grid.
   public static void drawGrid(Graphics brush, int x, int y, int size, int rowPair, int offset) {
      for (int repeat = 0; repeat < rowPair * 2; repeat++) {
         drawRow(brush, x + (offset * (repeat % 2)), y + repeat * (size + MORTAR), size, rowPair);
      }
   }     
}