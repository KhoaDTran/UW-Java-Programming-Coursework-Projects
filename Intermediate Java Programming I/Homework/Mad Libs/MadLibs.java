// Khoa Tran
// 10/23/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #6: Mad Libs

/*Prompts user to either create or view mad-libs 
with the ability to either view user's mad-lib file, 
or create mad-libs with user's input file, replacing placeholders with user's input,
and prints new lines into a output file.
*/
import java.util.*;
import java.io.*;

public class MadLibs {

   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      intro();
      System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
      String answer = console.nextLine();
      while (!answer.equalsIgnoreCase("q")) {
         if (answer.equalsIgnoreCase("c")) {
            File in = create(console);
            Scanner input = new Scanner(in);
            File out = outputFile(console);
            PrintStream output = new PrintStream(out);
            replace(console, input, output);
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            answer = console.nextLine();
         } else if (answer.equalsIgnoreCase("v")) {
            view(console);
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            answer = console.nextLine();
         }  else {
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            answer = console.nextLine();   
         }    
      }    
   }
   
   // Outputs an introductory statement
   public static void intro() {
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();
   }
   
   /* Scans input file for placeholder word, 
   prompts user to input a replacement word, replace the two words, 
   and prints input file with replaement words into a output file  
   */
   // Console = process user input
   // Input = process input file
   // Output = creates output file 
   public static void replace(Scanner console, Scanner input, PrintStream output) {
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineScan = new Scanner(line);
         while (lineScan.hasNext()) {
            String word = lineScan.next();
            if (word.startsWith("<") && word.endsWith(">")) {
               String wholeWord = word;
               String placeholder = word.toLowerCase().substring(1, word.length() - 1);
               String casingWord = word.substring(1, word.length() - 1);
               casingWord = casingWord.replace("-"," ");
               if (placeholder.startsWith("a") || placeholder.startsWith("e") 
                  || placeholder.startsWith("i") || placeholder.startsWith("o") 
                  || placeholder.startsWith("u")) {
                  System.out.print("Please type an " + casingWord + ": ");
               } else {
                  System.out.print("Please type a " + casingWord + ": ");
               }
               String newWord = console.nextLine();
               line = line.replace(wholeWord, newWord);
            }  
         }
         output.println(line);   
      }
      System.out.println("Your mad-lib has been created!");
      System.out.println();        
   }

   // Prompts user for an input file name and returns the input file
   // Console = process user input
   public static File create(Scanner console) {
      System.out.print("Input file name: ");
      String fileInput = console.nextLine();
      File in = new File (fileInput);
      while (!in.exists()) {
         System.out.print("File not found. Try again: ");
         fileInput = console.nextLine();
         in = new File(fileInput);
      }
      return in;      
   }

   // Prompts user for a output file name and returns the output file
   // Console = process user input  
   public static File outputFile(Scanner console) {
      System.out.print("Output file name: ");
      String fileOutput = console.nextLine();
      File out = new File(fileOutput);
      System.out.println();
      return out;
   }

   // Prompts user for the file desired to view and outputs the file
   // Console = process user input
   public static void view(Scanner console) throws FileNotFoundException {
      System.out.print("Input file name: ");
      String fileInput = console.nextLine();
      File viewFile = new File (fileInput);
      while (!viewFile.exists()) {
         System.out.print("File not found. Try again: ");
         fileInput = console.nextLine();
         viewFile = new File(fileInput);
      }
      System.out.println();
      Scanner viewScan = new Scanner(viewFile);
      while (viewScan.hasNextLine()) {
         String viewLine = viewScan.nextLine();
         System.out.println(viewLine);
      }
      System.out.println();
   }
}



   