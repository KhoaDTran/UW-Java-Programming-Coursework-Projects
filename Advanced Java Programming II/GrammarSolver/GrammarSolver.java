// Khoa Tran
// 02/14/2019
// CSE143
// TA: Amir Nola
// Assignment #5

/* GrammarSolver manipulates the grammar from the given file input. 
Can list all of the nonterminal symbols in the grammar, 
and generate sentences of a given nonterminal symbol number of given times.
When generating sentences, randomly choose the rules associated with the given nonterminal symbol 
and the sentence is seperated by one space between each terminal, 
and no leading or trailing spaces.
Also able to check if a given symbol is a nonterminal symbol in the grammar or not. 
Any given symbol is case sensitive. 
*/
import java.util.*;

public class GrammarSolver {
   private SortedMap<String, String[]> grammarRules; 
   
   // pre: the given list of grammar can't be empty, 
   // and there can't be any duplicates of a nonterminal
   // (throws IllegalArgumentException if one of the prerequisites above is false) 
   // post: With the given list of grammar, split the list by the nonterminals
   // and the rules of the nonterminal, keeping the association between the nonterminals,
   // and its rules, while making no changes to the given list. 
   // Each of the symbols in the list are case sensitive.  
   public GrammarSolver(List<String> grammar) {
      if (grammar.isEmpty()) {
         throw new IllegalArgumentException();
      }
      grammarRules = new TreeMap<String,String[]>();
      for (String sequence : grammar) {
         String[] parts = sequence.split("::=");
         String[] terminal = parts[1].trim().split("\\|");
         if (grammarRules.containsKey(parts[0])) {
            throw new IllegalArgumentException();
         }
         grammarRules.put(parts[0], terminal);
      }
   }
   
   // post: returns true if the given symbol is a nonterminal symbol in the grammar
   // if not, returns false. (given input symbol is case sensitive)
   public boolean grammarContains(String symbol) {
      return grammarRules.containsKey(symbol);
   }
   
   // pre: given symbol input has to be a nonterminal symbol in the grammar,
   // and given times input can't be less than 0. (
   // throws IllegalArgumentException if one of the prerequisites above is false) 
   // post: through given symbol and times input, 
   // randomly generate the grammar sentence for the given symbol number of given times.
   // Each of the rule for the given symbol is considered with equal probabilty as it is randomed.
   // Returns all of the generated grammar with one space between each terminal,
   // and no leading or trailing spaces
   // (given input symbol is case sensitive)
   public String[] generate(String symbol, int times) {
      if (!grammarContains(symbol) || times < 0) {
         throw new IllegalArgumentException();
      }
      String[] resultSentence = new String[times];
      for (int i = 0; i < times; i++) {
         resultSentence[i] = createSentences(symbol);
      }
      return resultSentence;
   }
   
   // post: returns all of the nonterminal symbols in the grammar
   // in sorted order, seperated by commas and enclosed in square brackets
   public String getSymbols() {
      return grammarRules.keySet().toString();
   }
   
   // post: randomly create the grammar sentence for the given symbol.
   // Each of the rule for the given symbol is considered with equal probabilty as it is randomed.
   // Returns the generated grammar with one space between each terminal, 
   // and no leading or trailing spaces. If given symbol input is a terminal, returns the symbol.
   // (given input symbol is case sensitive)
   private String createSentences(String symbol) {
      String sentence = "";
      if (!grammarContains(symbol)) {
         return symbol;
      }
      String[] allRules = grammarRules.get(symbol);
      int randNum = (int)(Math.random() * (allRules.length));
      String[] choosenRule = allRules[randNum].split("\\s+"); 
      for (String terminal : choosenRule) {
         sentence += createSentences(terminal) + " ";
      }
      return sentence.trim();
   }
}
