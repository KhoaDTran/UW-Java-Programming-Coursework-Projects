// Khoa Tran
// 12/04/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #9: Critters

import java.awt.*;

public class Husky extends Critter {
   private int moves;
   private Direction direction;
   
   public Husky() {
      this.moves = 0;
   }
   
   public boolean eat() {
      moves++;
      if (moves%3 == 0) {
         return true;
      } else {
         return false;
      }
   }
   
   public Attack fight(String opponent) {
      if (eat() == false) {
         return Attack.ROAR;
      } else {
         return Attack.FORFEIT;
      }
   }
   
   public Color getColor() {
      if (eat() == false) {
         return Color.WHITE;
      } else {
         return Color.RED;
      }
   }
   
   public Direction getMove() {
      if (getColor() == Color.RED) {
         if (moves <=20) {
            direction = Direction.SOUTH;
         } else {
            direction = Direction.WEST;
         }
      } else {
         if (moves <=20) {
            direction = Direction.NORTH;
         } else {
            direction = Direction.EAST;
         }
      }
      return direction;
   }
   
   public String toString() {
      if (eat() == false) {
         return "I'm eatting";
      } else {
         return "I'm chilling";
      }
   }
}