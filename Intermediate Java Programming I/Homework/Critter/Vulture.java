// Khoa Tran
// 12/04/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #9: Critters

/* Vulture critter will behave similar to Bird as it extends from Bird but 
differ with how it eats, the color, and how it attacks as it does different 
behaviors with different critters.
*/
import java.awt.*;

public class Vulture extends Bird {
   private boolean hunger;
   
   // Constructs Vulture with hunger
   public Vulture() {
      this.hunger = true;
   }
   
   // Returns true if it is hungry and once it does that it no longer is hungry
   public boolean eat() {
      if(hunger == true) {
         hunger = false;
         return true;
      } else {
         return false;
      }   
   }
   
   // Returns roar attack if opponent is an Ant, otherwise it pounces. Once it attacks, it becomes full
   // opponent = the opponent's string representation
   public Attack fight(String opponent) {
      if (opponent.equals("%")) {
         hunger = true;
         return Attack.ROAR;
      } else {
         return Attack.POUNCE;
      }
   }
}