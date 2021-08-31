// Khoa Tran
// 10/03/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #1

// This program will output the song "There Was an Old Woman Who Swallowed a Fly" in an clear and effective manner using methods.

public class Song {
   public static void main(String[] args) {
      first();
      space();
      
      second();
      space();
      
      third();
      space();
      
      fourth();
      space();
      
      fifth();
      space();
      
      sixth();
      space();
      
      seventh();
   }
   // Method will produce space
   public static void space() {
      System.out.println();
   }
   
   // Method will output the seventh verse
   public static void seventh() {
      System.out.println("There was an old woman who swallowed a horse,");
      System.out.println("She died of course.");
   }
   
   // Method will output the sixth verse
   public static void sixth() {
      System.out.println("There was an old woman who swallowed a bear,");
      System.out.println("Terribly unfair to swallow a bear.");
      dog();
   }
   
   // Method will output the fifth verse
   public static void fifth() {
      System.out.println("There was an old woman who swallowed a dog,");
      System.out.println("What a hog to swallow a dog.");
      cat();
   }
   
   // Method will output the fourth verse
   public static void fourth() {
      System.out.println("There was an old woman who swallowed a cat,");
      System.out.println("Imagine that to swallow a cat.");
      bird();
   }
   
   // Method will output the third verse
   public static void third() {
      System.out.println("There was an old woman who swallowed a bird,");
      System.out.println("How absurd to swallow a bird.");
      spider();
   }
   
   // Method will output the second verse
   public static void second() {
      System.out.println("There was an old woman who swallowed a spider,");
      System.out.println("That wriggled and iggled and jiggled inside her.");
      fly();
   }
   
   // Method will output the first verse
   public static void first() {
      System.out.println("There was an old woman who swallowed a fly.");
      ending();
   }
   
   // Method complies the ending of the sixth verse
   public static void dog() {
      System.out.println("She swallowed the bear to catch the dog,");
      cat();
   }
   
   // Method complies the ending of the fifth verse
   public static void cat() {
      System.out.println("She swallowed the dog to catch the cat,");
      bird();
   }
   
   // Method complies the ending of the fourth verse
   public static void bird() {
      System.out.println("She swallowed the cat to catch the bird,");
      spider();
   }
   
   // Method complies the ending of the third verse
   public static void spider() {
      System.out.println("She swallowed the bird to catch the spider,");
      fly();
   }
   
   // Method compiles the ending of the second verse
   public static void fly() {
      System.out.println("She swallowed the spider to catch the fly,");
      ending();
   }
   
   // Method will output the ending lines of the first verse
   public static void ending() {
      System.out.println("I don't know why she swallowed that fly,");
      System.out.println("Perhaps she'll die.");
   }
}