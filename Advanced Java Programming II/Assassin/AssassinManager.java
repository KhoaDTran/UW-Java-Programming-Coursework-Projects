// Khoa Tran
// 01/31/2019
// CSE143
// TA: Amir Nola
// Assignment #3

/* AssassinManager manages a game of assassin by keeping track of each player, 
their stalkers, and who they are stalking. Can also keep track of the history of person 
who got killed and their killer in the most recently killed order. 
Additionally, AssassinManger can print out the people in the kill ring and graveyard.
At the end of the game, prints out the winner of the game.    
*/
import java.util.*;


public class AssassinManager {
   private AssassinNode frontKillRing;
   private AssassinNode frontGraveyard;
   
   // pre: the given list can't be empty (throw IllegalArgumentException if is empty)
   // the given list has no duplicate names and the names can't be empty.
   // post: constructs AssassinManger that adds the names 
   // from the given list into the kill ring in the same order. 
   public AssassinManager(List<String> names) {
      if (names.isEmpty()) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < names.size(); i++) {
         if (frontKillRing == null) {
            frontKillRing = new AssassinNode(names.get(i));
         } else {
            AssassinNode current = frontKillRing;
            while (current.next != null) {
               current = current.next;
            }
            current.next = new AssassinNode(names.get(i));
         }
      }
   }
   
   // post: print the names of the people in the kill ring and their stalkers,
   // line by line and indented by four spaces
   public void printKillRing() {
      AssassinNode current = frontKillRing;
      while (current != null) {
         if (current.next != null) {
            System.out.println("    " + current.name + " is stalking " + current.next.name);
         } else {
            System.out.println("    " + current.name + " is stalking " + frontKillRing.name);
         }
         current = current.next;
      }
   }
   
   // post: print the names of the victims and their killers, 
   // in the order of the most recently killed person, line by line and indented by four spaces
   public void printGraveyard() {
      AssassinNode current = frontGraveyard;
      while (current != null) {
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }
   }
   
   // post: if the kill ring contains the given name, returns true
   // if not, returns false. 
   // Ignores casing when checking if the name is contaiend in the kill ring.
   public boolean killRingContains(String name) {
      AssassinNode current = frontKillRing;
      return (isContains(name,current)); 
   }
   
   // post: if the graveyard contains the given name, returns true
   // if not, returns false. 
   // Ignores casing when checking if the name is contaiend in the graveyard.
   public boolean graveyardContains(String name) {
      AssassinNode current = frontGraveyard;
      return (isContains(name,current));
   }
   
   // post: if the game is over, returns true
   // if not, returns false
   public boolean gameOver() {
      return (frontKillRing.next == null);
   }
   
   // post: if game is over, returns the winner of the game
   // if not, returns null
   public String winner() {
      if (!gameOver()) {
         return null;
      }
      return frontKillRing.name;
   }
   
   // pre: game is still in process (throws IllegalStateException if not),
   // kill ring contains the given name (throws IllegalArgumentException if not)
   // post: transfer the killed person with the given name from the kill ring to the graveyard. 
   public void kill(String name) {
      checkException(name);
      AssassinNode current = frontKillRing;     
      if (frontKillRing.name.equalsIgnoreCase(name)) {
         AssassinNode victim = current;
         frontKillRing = current.next;
         while (current.next != null) {
            current = current.next;
         }
         victim.killer = current.name;
         victim.next = frontGraveyard;
         frontGraveyard = victim;
      } else {
         current = frontKillRing;
         while (current.next != null) {
            if (current.next.name.equalsIgnoreCase(name)) {
               AssassinNode victim = current.next;
               victim.killer = current.name;
               current.next = current.next.next;
               if (frontGraveyard != null) {
                  victim.next = frontGraveyard;
               } else {
                  victim.next = null;
               }
               frontGraveyard = victim;
            } else {
               current = current.next;
            }             
         }         
      }
   }
   
   // post: if game is over, throws IllegalStateException,
   // if kill ring doesn't contains the given name, throws IllegalArgumentException
   private void checkException(String name) {
      if (gameOver()) {
         throw new IllegalStateException();
      }
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      }   
   }
   
   // post: returns true if the kill ring or the graveyard contains the given name input,
   // if not, returns false. Ignores casing when checking if the name is contaiend in the list.
   private boolean isContains(String name, AssassinNode type) {
      AssassinNode current = type;
      while (current != null) {
         if (current.name.equalsIgnoreCase(name)) {
            return true;
         }
         current = current.next;
      }
      return false;
   }
}
