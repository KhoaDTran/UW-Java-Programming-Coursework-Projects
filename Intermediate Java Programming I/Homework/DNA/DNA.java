// Khoa Tran
// 11/20/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #7: DNA

/* Processes a DNA data filen from user input and analyze each line for nucleotides, 
which would be counted up the number of each nucleotide, calculated for the mass percentage,
list out the codons from nucleotides, and determines if it is a protein.
*/
import java.util.*;
import java.io.*;

public class DNA {

   // Constant represents the minimum number of codons for a sequence to be a protein
   public static final int MIN_CODONS = 5;
   // Constant represents the minimum percent of C and G nucleotides to be a protein
   public static final int CG_PERCENT = 30;
   // Constatnt represesnts the number of unique nucleotides
   public static final int UNIQUE_NUCLEOTIDES = 4;
   // Constant represents the number of nucleotides per codon 
   public static final int NUCLEO_PER_CODON = 3;

   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner (System.in);
      intro();
      File input = getFile(console, "Input");
      File output = getFile(console, "Output");
      PrintStream outputFile = new PrintStream(output);
      Scanner scanInput = new Scanner (input);
      while (scanInput.hasNextLine()) {
         String regionName = scanInput.nextLine();
         String nucleotides = scanInput.nextLine().toUpperCase();
         
         int[] nucleotideNumber = getCounts(nucleotides);
         double massTotal = getTotal(nucleotideNumber);
         double []massPercent = massPercent(nucleotideNumber, massTotal);
         String []codon = codonList(nucleotides);
         String protein = isProtein(codon, massPercent);
         fileOutput(regionName, nucleotides, nucleotideNumber, massTotal, massPercent, 
						 codon, protein, outputFile);
      }  
   }
   
   // Outputs introductary statement 
   public static void intro() {
      System.out.println("This program reports information about DNA");
      System.out.println("nucleotide sequences that may encode proteins.");
   }
   
   // Prompts user for input/output file and returns the file
   // Console = prcoesses user input
   // String fileType = passes String through as either input or output 
   public static File getFile(Scanner console, String fileType) {
      System.out.print(fileType + " file name? ");
      String nameFile = console.nextLine();
      File f = new File (nameFile);
      return f;
   }
   
   // Count up the total number of each nucleotide and dashes
   // Nucleotide = nucleotide line from input file
   public static int[] getCounts(String nucleotides) {
      int[] nucleotideNumber = new int[UNIQUE_NUCLEOTIDES + 1]; 
      for (int i = 0; i < nucleotides.length(); i++) {
         char letter = nucleotides.charAt(i);
         if (letter == 'A') {
            nucleotideNumber[0]++;
         }  else if (letter == 'C') {
            nucleotideNumber[1]++;
         }  else if (letter == 'G') {
            nucleotideNumber[2]++;
         }  else if (letter == 'T') {
            nucleotideNumber[3]++;
         }  else {
            nucleotideNumber[4]++;
         }
      }
      return nucleotideNumber;
   }
   
   // Add and round the total mass of nucleotides
   // int [] nucleotideNumber = array of the tottal number of each nucleotide
   public static double getTotal(int[] nucleotideNumber) {
		double totalMass = 0;
		double[] baseMass = {135.128, 111.103, 151.128, 125.107, 100.000};
		for (int i = 0; i < baseMass.length; i++) {
			totalMass += baseMass[i] * nucleotideNumber[i];
      }
      double roundedTotal = Math.round(totalMass * 10.0) / 10.0;
		return roundedTotal;
	}
   
   // Calculates the percent mass of each nucleotide and returns an array of it
   // int [] nucleotideNumber = array of the tottal number of each nucleotide
   // massTotal = total mass of nucleotides
   public static double[] massPercent(int[] nucleotideNumber, double massTotal) {
      double massA = 135.128 * nucleotideNumber[0];
      double massC = 111.103 * nucleotideNumber[1];
      double massG = 151.128 * nucleotideNumber[2];
      double massT = 125.107 * nucleotideNumber[3];
      double massDash = 100.000 * nucleotideNumber[4];
    
      double[] percentMass = {massA, massC, massG, massT};
      for (int i = 0; i < percentMass.length; i++) {
         double percent = 100 * (percentMass[i]/ massTotal);
         double value = Math.round(percent * 10.0) / 10.0;
         percentMass[i] = value;
      }
      
      return percentMass;
   }
   
   // Compiles and returns a list of individual codons
   // Nucleotide = nucleotide line from input file
   public static String[] codonList(String nucleotides) {
		nucleotides = nucleotides.replace("-", "");
		int numCodon = nucleotides.length() / NUCLEO_PER_CODON;
		String[] codons = new String [numCodon];
		for (int i = 0;i <= nucleotides.length() - NUCLEO_PER_CODON; i+= NUCLEO_PER_CODON){
			codons[i/NUCLEO_PER_CODON] = nucleotides.substring(i, i + NUCLEO_PER_CODON);
		}
		return codons;  
	}
   
   // Checks if codon is a protein or not
   // codonList = list of all codons
   // massPercent = array of the mass percent of each nucleotide
   public static String isProtein(String[] codonList, double[] massPercent) {
      double massCG = massPercent[1] + massPercent[2];
      String isProtein = "";
      String last = codonList[codonList.length - 1];
      if (!codonList[0].equals("ATG")) {           
         isProtein = "NO";
      } else if (!(codonList.length >= MIN_CODONS)) {                   
         isProtein = "NO";
      } else if (!(massCG >= CG_PERCENT)) {                    
         isProtein = "NO";
      } else if (!(last.equals("TAA") || last.equals("TAG") || last.equals("TGA"))) {
         isProtein = "NO";
      } else {
         isProtein = "YES";
      }
      return isProtein;
   }
   
   // Outputs information to desired output file
   // regionName = name of the region
   // nucleotide = nucleotide line from input file
   // int [] nucleotideNumber = array of the tottal number of each nucleotide
   // massTotal = total mass of nucleotides
   // massPercent = array of the mass percent of each nucleotide
   // codon = array of each codon
   // protein = string of either it is a protein or not
   // outputFile = command to print to output file
   public static void fileOutput(String regionName, String nucleotides, int[] nucleotideNumber, 
									double massTotal, double[] massPercent, String[] codon, 
									String protein, PrintStream outputFile) {
		outputFile.println("Region Name: " + regionName);
		outputFile.println("Nucleotides: " + nucleotides);
      int[] nucleotideCounts = new int[UNIQUE_NUCLEOTIDES];
      for (int i = 0; i < UNIQUE_NUCLEOTIDES; i++) {
         nucleotideCounts[i] = nucleotideNumber[i];
      }
		outputFile.println("Nuc. Counts: " + Arrays.toString(nucleotideCounts));
		outputFile.println("Total Mass%: " + Arrays.toString(massPercent) + " of " + massTotal);
		outputFile.println("Codons List: " + Arrays.toString(codon));
		outputFile.println("Is Protein?: " + protein);
		outputFile.println();
	}
}
   