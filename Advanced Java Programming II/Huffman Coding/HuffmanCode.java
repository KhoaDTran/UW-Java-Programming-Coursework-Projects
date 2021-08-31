// Khoa Tran
// 03/16/2019
// CSE143
// TA: Amir Nola
// Assignment #8

/* HuffmanCode represents a huffman coding algorithm which is used to compress and decompress data.
Compresses the given legal file by reading the characters and frequencies, 
outputing the shortned binary representation of the file.
Decompressing a file takes in a shortned compressed code file, 
converts and prints the original characters.
Can also make a huffman code of a file, and can do a decompression after compressing the file.
*/
import java.io.*;
import java.util.*;

public class HuffmanCode {
   private HuffmanNode overallRoot;
   
   // post: Constructs HuffmanCode using the huffman algorithm from a given frequency list
   //       with each of the index of the list representing the ASCII character value 
   //       and the frequency at the index representing the frequency of the character.
   //       Combines two of the huffman code inputs by linking both with a zero character
   //       until there's only one combine input. 
   // frequencies = given list of frequencies  
   public HuffmanCode(int[] frequencies) {
      Queue<HuffmanNode> huffmanQ = new PriorityQueue<HuffmanNode>(); 
      for (int i = 0; i < frequencies.length; i++) {
         if(frequencies[i] != 0) {
            char letter = (char)(i);
            HuffmanNode charNode = new HuffmanNode(letter, frequencies[i]);
            huffmanQ.add(charNode);           
         }
      }
      char tempChar = (char) 0;
      HuffmanNode startRoot = null;
      while (huffmanQ.size() > 1) {
         HuffmanNode first = huffmanQ.remove();
         HuffmanNode second = huffmanQ.remove();
         int totalFreq = first.frequency + second.frequency;
         startRoot = new HuffmanNode(tempChar, totalFreq, first, second);
         huffmanQ.add(startRoot);
      }
      overallRoot = startRoot;
      
   }
   
   // pre: Input file has to contains data in the valid format of 
   //      the first line being the ASCII value of the character, 
   //      and the second line being the huffman code of the character.
   // post: Constructs a HuffmanCode by reading a constructed huffman code from the input file.
   // input = process file input
   public HuffmanCode(Scanner input) {
      while(input.hasNextLine()) {
         char letter = (char) Integer.parseInt(input.nextLine());
         String huffCode = input.nextLine();
         overallRoot = fileTree(overallRoot, huffCode, letter);
      }
   }
   
   // post: From the given character and huffman code associated to the character, 
   //       create HuffmanCode object that represents the input of the huffman code of the file. 
   //       Uses the zero character to initialize an empty character 
   //       in between the characters of the file
   // root = inputs of the huffman code
   // huffCode = given huffman code of character
   // letter = given character 
   private HuffmanNode fileTree(HuffmanNode root, String huffCode, char letter) {
      if (huffCode.length() == 0) {
         return new HuffmanNode(letter, 0);
      } else {
         if (root == null) {
            char tempChar = (char) 0;
            root = new HuffmanNode(tempChar, 0);
         }
         if (huffCode.charAt(0) == '1') {
            root.rightOne = fileTree(root.rightOne, huffCode.substring(1), letter);          
         } else {
            root.leftZero = fileTree(root.leftZero, huffCode.substring(1), letter);
         }         
      }
      return root;      
   }
   
   // post: Outputs the current huffman inputs that consists of a pair of lines 
   //       with the first line being the ASCII value of the character, 
   //       and the second line being the huffman code of the character.
   // output = command to print to output file
   public void save(PrintStream output) {
      printTree(output, overallRoot, ""); 
   }
   
   // post: Traverse through the huffman character and code inputs, outputing all of the inputs
   //       consisting of a two lines with the first line being the ASCII value of the character, 
   //       and the second line being the huffman code of the character. 
   // output = command to print to output file
   // root = inputs of the huffman code
   // bitOut = output string that consists of binary huffman code
   private void printTree(PrintStream output, HuffmanNode root, String bitOut) {
      if (root.leftZero == null && root.rightOne == null) {
         output.println((int)root.character);
         output.println(bitOut);
      } else {
         printTree(output, root.leftZero, bitOut + "0");
         printTree(output, root.rightOne, bitOut + "1");
      }
         
   }
   
   // pre: The input stream is in the legal format of the characters in the huffman code 
   // post: Reads the input stream for individual bits of a compressed message and
   //       output the characters that correspond to the bits in a decompressed message.
   // input = process compressed file input
   // output = command to print to output file
   public void translate(BitInputStream input, PrintStream output) {   
      HuffmanNode root = overallRoot;
      while (input.hasNextBit()) {
         int firstBit = input.nextBit();
         if (firstBit == 1) {
            root = root.rightOne;
         } else {
            root = root.leftZero;
         }
         if (root.leftZero == null && root.rightOne == null) {
            output.write(root.character);
            root = overallRoot;           
         }
      }
   }  
   
   // HuffmanNode class representing a single input for each huffman code, 
   // also can be compared with another huffman node, 
   // ordering it by the frequencies as the lowest frequency first.  
   private static class HuffmanNode implements Comparable<HuffmanNode> {
      public char character; // character data stored at this input
      public int frequency; // frequency data of character stored at this input
      public HuffmanNode leftZero; // reference to the left zero input
      public HuffmanNode rightOne; // reference to the right one input
      
      // post: Constructs HuffmanNode with a single input
      //       with the given character and frequency data.
      // character = given character data
      // frequency = given frequency data of character
      public HuffmanNode(char character, int frequency) {
         this(character, frequency, null, null);
      }
      
      // post: Constructs HuffmanNode with a single input or branched inputs 
      //       with the given character and frequency data and links.
      // character = given character data
      // frequency = given frequency data of character
      // leftZero = reference to the left zero input
      // rightOne = reference to the right one input
      public HuffmanNode(char character, int frequency, 
                         HuffmanNode leftZero, HuffmanNode rightOne) {
         this.character = character;
         this.frequency = frequency;
         this.leftZero = leftZero;
         this.rightOne = rightOne;        
      }
      
      // post: Returns a difference between two huffman node frequency or zero 
      // to order the inputs in the order of the lowest frequency first. 
      public int compareTo(HuffmanNode other) {
         if (this.frequency != other.frequency) {
            return this.frequency - other.frequency;
         } else {
            return 0;
         }
      }     
   }  
}
