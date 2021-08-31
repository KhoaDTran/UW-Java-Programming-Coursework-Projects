// Khoa Tran
// 03/08/2019
// CSE143
// TA: Amir Nola
// Assignment #7

/* QuestionGame represents a game similar to 20 questions as it keeps track of inputs
that represents questions and answers, playing a game by asking a series of yes or no questions
until it reaches an answer object to guess. If it is right, it will prints a message saying so. 
If not, updates the inputs of game by asking the user the object they are thinking of,
and a question and answer to differentitate it. 
Can also replace the current inputs of the game to be the ones from the file, 
and prints the current inputs of the game to a file
starting at the first input to the yes compliment and then the no comnpliment.
*/
import java.io.*;
import java.util.*;

public class QuestionsGame {
   private Scanner console;
   private QuestionNode overallRoot;
    
   // post: Constructs QuestionGame by initializing the object computer 
   //       to be the first input of the game
   public QuestionsGame() {
      console = new Scanner(System.in);
      overallRoot = new QuestionNode("computer");
   }
   
   // pre: File is standard format and legal.
   // post: Replace the current inputs for the game to be the ones from the given input file
   //       starting at the first input to the yes compliment and then the no comnpliment. 
   // input = process file input
   public void read(Scanner input) {
      while (input.hasNext()) {
         overallRoot = generateRead(input);
      }
   }
   
   // pre: File is standard format and legal.
   // post: Reads the file for questions and answers, and returns the inputs for the game
   //       by placing each question starting at the first input to the yes compliment
   //       and then the no comnpliment into the correct inputs. 
   // input = process file input
   private QuestionNode generateRead(Scanner input) {
      String type = input.nextLine();
      String givenInput = input.nextLine(); 
      QuestionNode root = new QuestionNode(givenInput);
      if (type.equals("Q:")) {
         root.leftYes = generateRead(input);
         root.rightNo = generateRead(input);
      }
      return root;      
   }
   
   // post: Stores the current inputs for the game into an output file,
   //       starting at the first input to the yes compliment and then the no comnpliment.
   //       Can be recalled to play another game with the same inputs from the file.
   // output = command to print to output file      
   public void write(PrintStream output) {
      generateWrite(output, overallRoot);
   }
   
   // post: Reads and prints the inputs from the game into an output file, 
   //       starting at the first input to the yes compliment and then the no comnpliment
   // output = command to print to output file
   // root = inputs of the game
   private void generateWrite(PrintStream output, QuestionNode root) {
      if (root.leftYes == null && root.rightNo == null) {
         output.println("A:");
         output.println(root.input);        
      } else {        
         output.println("Q:");
         output.println(root.input);
         generateWrite(output, root.leftYes);
         generateWrite(output, root.rightNo);         
      }
   }
     
   // post: Play one comeplete guessing game using the current inputs of the game,
   //       starting at the first input asking the user yes or no questions 
   //       until reaching an answer object to guess with the user.
   //       If the game guess the correct answer, prints a phrase declaring the victory.
   //       If not, ask the user the object they were thinking of, 
   //       a question to differentiate between the guess and the user's object, 
   //       and an yes or no answer to the question.
   public void askQuestions() {
      overallRoot = generateQuestions(overallRoot);
   }
   
   // post: Using the current inputs of the game, play one comeplete guessing game 
   //       starting at the first input asking the user yes or no questions 
   //       until reaching an answer object to guess with the user.
   //       If the game guess the correct answer, prints a phrase declaring the victory.
   //       If not, ask the user the object they were thinking of, 
   //       a question to differentiate between the guess and the user's object, 
   //       and an yes or no answer to the question. Depending on the answer, 
   //       place the question and answer of the user into the correct placements.
   //       Returns all of the inputs of the game.
   // root = inputs of the game
   private QuestionNode generateQuestions(QuestionNode root) {
      if (root.leftYes != null && root.rightNo != null) {
         if (yesTo(root.input)) {
            root.leftYes = generateQuestions(root.leftYes);
         } else {
            root.rightNo = generateQuestions(root.rightNo);
         }
      } else {
         String prompt = "Would your object happen to be " + root.input + "?";
         if (yesTo(prompt)) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            QuestionNode newAnswer = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String inputQuestion = console.nextLine();
            prompt = "And what is the answer for your object?";
            if (yesTo(prompt)) {
               root = new QuestionNode(inputQuestion, newAnswer, root);
            } else {
               root = new QuestionNode(inputQuestion, root, newAnswer);
            }
         }         
      }  
      return root;   
   }

   // post: Asks the user a question from the given prompt, forcing an answer of "y" or "n". 
   //       Returns true it was "y", false if otherwise.
   // prompt = question to ask the user
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }    
   
   // QuestionNode class representing a single input for the game 
   private static class QuestionNode {
      public String input; // data stored at this input
      public QuestionNode leftYes; // reference to the left yes input
      public QuestionNode rightNo; // reference to the right no input
      
      // post: Constructs QuestionNode with a single input with the given input data.
      // input = given input data
      public QuestionNode(String input) {
         this(input, null, null);
      }
      
      // post: Constructs QuestionNode with a single input or branched inputs 
      //       with the given input data and links.
      // input = given input data
      // leftYes = reference to the left yes input
      // rightNo = reference to the right no input
      public QuestionNode(String input, QuestionNode leftYes, QuestionNode rightNo) {
         this.input = input;
         this.leftYes = leftYes;
         this.rightNo = rightNo;        
      }
   }  
}
