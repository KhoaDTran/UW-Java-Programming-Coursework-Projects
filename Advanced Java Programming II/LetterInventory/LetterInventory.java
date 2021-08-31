// Khoa Tran
// 01/15/2019
// CSE143
// TA: Amir Nola
// Assignment #1

/* LetterInventory keeps track of the count of each letter in lower case from the given input data. 
Can also combine or deduct with the other LetterInventory, 
with the functionality to use the counts of each letter into multiple methods. 
*/
import java.util.*;

public class LetterInventory {
   // Constant DEFAULT_CAPACITY represents the number characters in the alphabet
   public static final int DEFAULT_CAPACITY = 26;
   private int[] elementData;
   private int size;
   private String word;
   
   // Constructs LetterInventory with a count of each alphabetic letter 
   // from the given string data input, 
   // turning string to lower case and ignores nonalphabetical characters
   public LetterInventory(String data) {
      this.elementData = new int[DEFAULT_CAPACITY];
      word = data.toLowerCase();
      for (int i = 0; i < word.length(); i++) {
         if(word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
            elementData[word.charAt(i) - 'a']++;
            size++;
         }
      }
   }
   
   // pre: the given letter is a valid alphabet letter (throws IllegalArgumentException if not)
   // post: turns the given letter input to lowercase 
   // and returns the count of the letter in the inventory
   public int get(char letter) {
      char ch = isLetter(letter);
      return elementData[ch - 'a'];
   }
   
   // pre: value >= 0, the given letter is a valid alphabet
   // (throws IllegalArgumentException if not)
   // post: from the given letter input, sets the count of the letter from the given value
   public void set(char letter, int value) {
      char ch = isLetter(letter);
      if (value < 0) {
         throw new IllegalArgumentException(); 
      } else {
         size = size - elementData[ch - 'a'] + value;
         elementData[ch - 'a'] = value;
      }
   }
   
   // post: returns the total of the counts in the inventory
   public int size() {
      return size;
   }
   
   // post: if inventory is empty, returns true
   public boolean isEmpty() {
      return size == 0;
   }
   
   // post: returns a lower case string representation of all the letters in the 
   // inventory in sorted order surrounded by square brackets
   public String toString() {
      String str = "[";
      for (int i = 0; i < DEFAULT_CAPACITY ; i++) {
         for (int j = 0; j < elementData[i]; j++) {
            str += (char) (i + 'a');
         }
      }
      str += "]";
      return str;
   }
   
   // post: returns a new LetterInventory that combines 
   // the current inventory and the given other inventory
   public LetterInventory add(LetterInventory other) {
      LetterInventory result = new LetterInventory("");
      for (int i = 0; i < DEFAULT_CAPACITY ; i++) {
         result.elementData[i] = this.elementData[i] + other.elementData[i];
      }
      result.size = this.size + other.size;
      return result;
   }
   
   // post: returns a new LetterInventory that subtracts 
   // the given other inventory from the current inventory.
   // If any count of a letter is negative, returns null
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory result = new LetterInventory("");
      for (int i = 0; i < DEFAULT_CAPACITY; i++) {
         result.elementData[i] = this.elementData[i] - other.elementData[i];
         if (result.elementData[i] < 0) {
            return null;
         }
      }
      result.size = this.size - other.size;
      return result;
   }
   
   // post: turns letter input to lower case and returns the letter 
   // if the given letter input is a valid alphabet letter. 
   // If not, throws IllegalArgumentException.
   private char isLetter(char letter) {
      char ch = Character.toLowerCase(letter);
      if (letter <= 'a' && letter >= 'z') {
         throw new IllegalArgumentException();
      } else {
         return ch;
      }
   }
}
