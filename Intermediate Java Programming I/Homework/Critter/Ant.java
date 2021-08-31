// Khoa Tran
// 12/04/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #9: Critters

/* Ant critter is always red, always eats, and always scratches. 
It's movement is determined by walkSouth as it will either alternate 
between South and East if walkSouth is true, otherwise it alternates between North and East.
It's string representation is a percent sign.
*/
import java.awt.*;

public class Ant extends Critter {
   private boolean walkSouth;
   private int moves;
   
   // Construct Ants with walkSouth and the number of moves
   // boolean walkSouth = true or false to alter movement
   public Ant(boolean walkSouth) {
      this.walkSouth = walkSouth;
      moves = 0;
   
   }
   
   // Returns color Red
   public Color getColor() {
      return Color.RED;
   }
   
   // Returns true for eating
   public boolean eat() {
      return true;
   }
   
   // Returns sratch for attacking always
   public Attack fight(String opponent) {
      return Attack.SCRATCH;
   }
   
   // Returns direction of either alternating between south and east or north and west 
   // depending on if walkSouth is true or not
   public Direction getMove() {
      moves++;
      if(walkSouth == true) {
         if(moves % 2 == 0) {
            return Direction.EAST;
         } else {
            return Direction.SOUTH;
         }
      } else {
         if(moves % 2 == 0) {
            return Direction.EAST;
         } else {
            return Direction.NORTH;
         }
      }
   }
   
   // Returns a percent as the string representation of Ant
   public String toString() {
      return "%";
   }   
}