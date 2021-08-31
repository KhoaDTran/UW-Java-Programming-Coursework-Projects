// Khoa Tran
// 02/08/2019
// CSE143
// TA: Amir Nola
// Assignment #4

/* HangmanManager manages a game of hangman, but in an evil way
as it delays picking a word until it has been forced to by the gueesses made from the user.
Can also get the words being considered by the game, report the number of guessed left,
and keeps track of all the guessed made. Additionally, it can display the pattern of the word
the user is trying to guess with the guesses being considered.
*/
import java.util.*;

public class HangmanManager {
   private Set<String> wordSet;
   private Set<Character> guessCharacter;
   private int remainingGuess;
   private String guessPattern;
   
   // pre: given target word length is at least 1,
   // and the maximum number of wrong guesses is at least 0
   // (throws IllegalArgumentException if one of the prerequisites above is false)
   // the dictionary passed has to be lowercased and can't contain any nonempty words
   // post: constructs HangmanManager that initialize the game
   // by adding all of the words in the given dictionary that has same the targeted word length
   // to the set of words being considered by the game,
   // deleting any duplicates from the dictionary.
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      wordSet = new TreeSet<String>();
      guessCharacter = new TreeSet<Character>();
      remainingGuess = max;
      guessPattern = "";
      for (int i = 0; i < length; i++) {
         guessPattern += "- "  ;
      }
      for (String word : dictionary) {
         if(word.length() == length) {
            wordSet.add(word);
         }
      }
   }
   
   // post: returns the set of words for the player to guess
   public Set<String> words() {
      return wordSet;
   }
   
   // post: returns the player's number of guesses left
   public int guessesLeft() {
      return remainingGuess;
   }
   
   // post: returns the set of characters that have been guessed by the player
   public Set<Character> guesses() {
      return guessCharacter;
   }
   
   // pre: the set of words can't be empty (throw IllegalStateException if not)
   // post: returns the dashed pattern of the word being guessed
   // with consideration of the characters that have been guessed
   public String pattern() {
      if (wordSet.isEmpty()) {
         throw new IllegalStateException();
      }
      return guessPattern;
   }
   
   // pre: number of guesses left is at least 1, and the set of words can't be empty.
   // If one of prerequisites above is false, throws IllegalStateException.
   // The character can't be previously guessed (throw IllegalArgumentException if not)
   // The given guessed characters has to be lowercase letters.
   // post: decides the set of words to use from the given guessed character,
   // updates the number of guesses left and the pattern accordingly,
   // and returns of the number of occurrences of the guessed character in the pattern
   public int record(char guess) {
      int size = 0;
      checkException(guess);
      guessCharacter.add(guess);
      Map<String, Set<String>> gamePatterns = new TreeMap<String, Set<String>>();
      setMap(guess,gamePatterns);
      String biggestPattern = "";
      for (String newKey : gamePatterns.keySet()) {
         if(gamePatterns.get(newKey).size() > size) {
            size = gamePatterns.get(newKey).size();
            biggestPattern = newKey;
         }
      }
      wordSet = gamePatterns.get(biggestPattern);
      return getOccurrences(guess, biggestPattern);
   }
   
   // pre: the given guessed characters has to be lowercase letters
   // post: if the number of guesses left is less than 1, or if the set of words is be empty,
   // throws IllegalStateException.
   // If the character was be previously guessed, throw IllegalArgumentException.
   private void checkException(char guess) {
      if (remainingGuess < 1 || wordSet.isEmpty()) {
         throw new IllegalStateException();
      }
      if (guessCharacter.contains(guess)) {
         throw new IllegalArgumentException();
      }
   }
   
   // pre: the given guessed characters has to be lowercase letters
   // post: from the given guessed character, makes a pattern for each word in the set of words,
   // and associates the words with same pattern together
   private void setMap(char guess, Map<String, Set<String>> gamePatterns) {
      for (String word : wordSet) {
         String patternMatch = "";
         for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != guess) {
               patternMatch += "- ";
            } else {
               patternMatch += guess + " ";
            }
         }
         String key = patternMatch.trim();
         if (!gamePatterns.containsKey(key)) {
            gamePatterns.put(key, new TreeSet<String>());
         }
         gamePatterns.get(key).add(word);
      }
   }
   
   // pre: the given guessed characters has to be lowercase letters
   // post: updates the number of guesses left and the pattern accordingly,
   // returns of the number of occurrences of the guessed character in the pattern
   private int getOccurrences(char guess, String biggestPattern) {
      int numOccurrences = 0;
      for (int i = 0; i < biggestPattern.length(); i += 2) {
         if (biggestPattern.charAt(i) == guess) {
            guessPattern = guessPattern.substring(0, i) + guess + guessPattern.substring(i + 1);
            numOccurrences++;
         }
      }
      if (numOccurrences == 0) {
         remainingGuess--;
      }
      return numOccurrences;
   }
}