// Khoa Tran
// 12/04/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #9: Critters

/* Hippo critter will behave depending if the hippo how much food the hippo wants to eat
and if it is hungry. If the hippo is hungry, it would be gray instead of white. 
It will eat until it's not hungry anymore. It will also move 5 steps in a random 
direction and repeat that. It will also have the number of food it still wants to eat.  
*/
import java.awt.*;
import java.util.*;

public class Hippo extends Critter {
   private int hunger;
   private int moves;
   private int randomDirection;
   private Direction direction;
   private Random random = new Random();

   // Constructs Hippo with the amount it wants to eat and the moves it takes
   // int hunger = the amount it wants to eat to be full
   public Hippo(int hunger) {
      this.hunger = hunger;
      this.moves = 0;     
      this.randomDirection = 0;
   }
   
   // Returns true if the hippo is still hungry
   public boolean eat() {
      if (hunger != 0) {
         hunger--;
         return true;
      } else {
         return false;
      }   
      
   }
   
   // Returns a sratch attack if eat is true, otherwise it pounces
   public Attack fight(String opponent) {
      if (eat()) {
         return Attack.SCRATCH;
      } else {
         return Attack.POUNCE;  
      }
   }
   
   // Returns color gray if eat is true, otherwise it's white
   public Color getColor() {
      if (eat()) {
         return Color.GRAY;
      } else {
         return Color.WHITE;  
      } 
   }
   
   // Returns the direction it moves five times through randoming a direction
   public Direction getMove() { 
      if (moves%5 == 0) { 
         randomDirection = random.nextInt(4);
         if (randomDirection == 0) {
            direction = Direction.NORTH;
         } else if (randomDirection == 1) {
            direction = Direction.SOUTH;
         } else if (randomDirection == 2) {
            direction = Direction.EAST;
         } else {
            direction = Direction.WEST;
         }
      }
      moves++;
      return direction;    
   }
   
   // Returns the amount it still wants to eat
   public String toString() {
      return ("" + hunger);
   }
}