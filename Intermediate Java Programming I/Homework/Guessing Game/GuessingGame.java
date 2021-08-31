// Khoa Tran
// 10/30/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #5: Guessing Game

/* A guessing game, randomizing a number between 1 and the maximum number input,
 allowing the user to guess the number, continue on if desired, 
 and displays the overall results of the games. 
*/
import java.util.*;

public class GuessingGame {

   // Max constant sets the value of the maximum number for the game.
   public static final int MAX = 100;

   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
                  
      intro();
      char answer = 'y';
      int totalGame = 0(int;
      int bestGame = Integer.MAX_VALUE;
      int totalGuess = 0;
      while (answer == 'y' || answer == 'Y') {
         int currGame = game(console);
         totalGuess = currGame;
         totalGuess += currGame;
         totalGame++;
         bestGame = Math.min(bestGame, currGame);
         System.out.print("Do you want to play again? ");
         answer = console.next().charAt(0);
      }
      results(totalGame, totalGuess, bestGame);
   }
   
   // Outputs a haiku introductory statement.
   public static void intro() {
      System.out.println("I'm more than a game");
      System.out.println("I always mess with your brain");
      System.out.println("Play, you'll feel the pain.");
   }
   
   // Outputs the guessing part of the game and returns the number of guesses
   // Console = processes user input 
   public static int game(Scanner console) {
      Random r = new Random();
      int num = r.nextInt(MAX) + 1;
      System.out.println();
      System.out.println("I'm thinking of a number between 1 and " + MAX + "...");
      System.out.print("Your guess? ");
      int guess = console.nextInt();
      int numGuess = 1;
            
      while (guess != num) {
         if (guess > num) {
            System.out.println("It's lower.");    
         } else {    // guess < num
            System.out.println("It's higher.");
         }         
         numGuess++;
         System.out.print("Your guess? ");
         guess = console.nextInt();
      }
      if (numGuess == 1) {
         System.out.println("You got it right in 1 guess!");
      } else {
         System.out.println("You got it right in " + numGuess + " guesses!");
      }
            
      return numGuess;   
   }
   
   // int totalGame = total games played.
   // int totalGuesses = total guesses of all the games.
   // int bestGame = the number of guesses of the lowest guess game.
   // Outputs the results of all played games in categories pertaining total guesses and games.
   public static void results(int totalGame, int totalGuesses, int bestGame) {
      System.out.println();
      System.out.println("Overall results:");
      System.out.println("Total games   = " + totalGame);
      System.out.println("Total guesses = " + totalGuesses);
      double perGame = (double)(totalGuesses)/totalGame;
      System.out.printf("Guesses/game  = %.1f\n", perGame);
      System.out.println("Best game     = " + bestGame);
   }      
 }