// Khoa Tran
// 03/01/2019
// CSE143
// TA: Amir Nola
// Assignment #6

/* AnagramSolver takes in a list of dictionary words and finds all possible combinations
of the words from the given dictionary list that has the same anagram as the given text phrase.
Also initially prunes the list of dictionary words for words that can only be anagrams 
of given text and print out a given max number of words, or unlimited if max is 0. 
Prints the results in format of square brackets on the sides and seperated by commas.
*/
import java.util.*;

public class AnagramSolver {
   private Map<String, LetterInventory> inventory;
   private List<String> duplicateDict;
   
   // pre: The given dictionary list is nonempty and contains no duplicates
   // post: Constructs AnagramSolver by computing,
   // and storing all inventories of letters of each dictionary word from the given list.
   // dictionary = a unaltered list of words being compared with the given text for anagrams 
   public AnagramSolver(List<String> dictionary) {
      duplicateDict = dictionary; 
      inventory = new HashMap<String, LetterInventory>();
      for (String word : duplicateDict) {
         inventory.put(word, new LetterInventory(word)); 
      }    
   }
   
   // pre: Max is 0 or more (throw IllegalArgumentException if not)
   // post: With the given text, prunes the list of dictionary words 
   // containing only words that can be anagrams of the given text.
   // Then, finds and print all possible combinations of words 
   // from the pruned dictionary list that are anagrams of the given text. 
   // Prints in the format of square brackets on the sides, seperated by commas,
   // also the prints the given max number of words, or unlimited if max is 0.
   // text = an inputted phrase being compared for anagrams with words from the dictionary
   // max = the maximum words to be printed, prints every possible anagram combination if max is 0
   public void print(String text, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory givenLetters = new LetterInventory(text);
      List<String> pruneList = reduce(givenLetters);
      Stack<String> result = new Stack<String>();
      generateAnagrams(pruneList, givenLetters, result, max);         
   }
   
   // post: With the pruned dictionary list, find and print all possible combinations of words 
   // from the list that are anagrams of the given text. 
   // Prints in the format of square brackets on the sides, seperated by commas,
   // also prints the given max number of words, or all of the possible combinations if max is 0. 
   // pruneList = pruned list containing only words that can be anagrams of the given text
   // givenLetters = inventory of letters of given text or remaining letters to use  
   // result = list containing the results of the anagram combinations 
   // between the given text and the pruned dictionary list 
   // max = the maximum words to be printed, prints every possible anagram combination if max is 0
   private void generateAnagrams(List<String> pruneList, LetterInventory givenLetters,
                                 Stack<String> result, int max) {
      if (givenLetters != null && (result.size() <= max || max == 0)) {
         if (givenLetters.isEmpty()) {
            System.out.println(result);
         } 
         for (String option : pruneList) {
            LetterInventory difference = givenLetters.subtract(inventory.get(option));
            result.push(option);
            generateAnagrams(pruneList,difference,result,max);
            result.pop();          
         }
      }
   }
   
   // post: Returns the pruned list of dictionary words to be checked by 
   // initially checking each word's inventory of letters in the dictionary list 
   // for a possible anagram with the inventory of letters of the given text.
   // givenLetters = the inventory of letters of the given inputted text
   private List<String> reduce(LetterInventory givenLetters) {
      List<String> reduceList = new ArrayList<String>();
      for (String word : duplicateDict) {
         LetterInventory dictLetters = new LetterInventory(word);
         if (givenLetters.subtract(dictLetters) != null) { 
            reduceList.add(word);
         }            
      }
      return reduceList;
   }
}