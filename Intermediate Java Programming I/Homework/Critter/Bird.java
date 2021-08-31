// Khoa Tran
// 12/04/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #9: Critters

/* The Bird critter is always blue, never eats, 
roars only against Ants, moves in a clockwise square, 
and has a string representation of different signs depending on the bird's last move. 
*/
import java.awt.*;

public class Bird extends Critter {
   private int moves;
   private Direction direction;
   
   // Constructs Bird with it's number of moves
   public Bird() {
      this.moves = 0;
   }
   
   // Returns false for never eating
   public boolean eat() {
      return false;
   }
   
   // Returns roar attack if opponent is an Ant, otherwise it pounces. Once it attacks, it becomes full
   // opponent = the opponent's string representation
   public Attack fight(String opponent) {
      if (opponent.equals("%")) {
         return Attack.ROAR;
      } else {
         return Attack.POUNCE;
      }
   }
   
   // Returns the color blue
   public Color getColor() {
      return Color.BLUE;
   }
   
   // Returns North for the first 3 moves, then East, South, and West
   public Direction getMove() {
     if (moves <= 2) {
         direction = Direction.NORTH;
      } else if (moves <=5) {
         direction = Direction.EAST;
      } else if (moves <=8) {
         direction = Direction.SOUTH;
      } else if (moves <=11) {
         direction = Direction.WEST;
      }
      moves++;
      if (moves == 12) {
         moves = 0;
      }
      return direction;
   }
   
   // Returns > for last direction of East, V for direction of South, 
   // < for direction of West, ^ for direction of North or no direction
   public String toString() {
      if (direction == Direction.EAST) {
         return ">";
      } else if (direction == Direction.SOUTH) {
         return "V";
      } else if (direction == Direction.WEST) {
         return "<";
      } else {
         return "^";
      }
   }
}