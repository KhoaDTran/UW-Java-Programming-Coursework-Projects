// Henry Learner
// 08/02/2020
// CSE142
// TA: 
// Assignment #6: YazInterpreter

/* Implements a class that interprets commands from the programming
language YazLang. This class is able to interpret an input file
and output the results in the desired output, or view a given file.
With interpretting, this program is able to convert the temperature
from the given file, output the range of numbers, and repeat
given phrases a certain number of times, which is given from the file. 
*/
import java.util.*;
import java.io.*;   

public class YazInterpreter {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        intro();
        boolean tempQ = true;
        while (tempQ) {
            String userInput = getUser(console);
            if (userInput.equalsIgnoreCase("I")) {
                File input = getInput(console);
                File output = getOutput(console);
                PrintStream outputFile = new PrintStream(output);
                Scanner scanInput = new Scanner(input);
                interpreter(scanInput, outputFile);
                System.out.println("YazLang interpreted and output to a file!");
                System.out.println();
            } else if (userInput.equalsIgnoreCase("V")) {
                File input = getInput(console);
                Scanner scanInput = new Scanner(input);
                System.out.println();
                view(scanInput);
                System.out.println();
                scanInput.close();
            } else if (userInput.equalsIgnoreCase("Q")) {
                tempQ = false;
            } else {
                tempQ = true;
            }
        }
    }

    // Outputs introductary statement
    public static void intro() {
        System.out.println("Welcome to YazInterpreter!");
        System.out.println("You may interpret a YazLang program and output");
        System.out.println("the results to a file or view a previously");
        System.out.println("interpreted YazLang program.");
        System.out.println();
    }

    // Returns the user input from the option of I, V, or Q question
    // Console = prcoesses user input
    public static String getUser(Scanner console) {
        System.out.print("(I)nterpret .yzy program, (V)iew .yzy output, (Q)uit? ");
        String input = console.nextLine();
        return input;
    }

    // Returns the given input file
    // Special Case: If file not found,
    // ask again until file is found.
    // Console = prcoesses user input
    public static File getInput(Scanner console) {
        System.out.print("Input file name: ");
        String nameFile = console.nextLine();
        File nfile = new File(nameFile);
        boolean exist = nfile.exists();
        while (!exist) {
            System.out.print("File not found. Try again: ");
            String otherFile = console.nextLine();
            nfile = new File(otherFile);
            exist = nfile.exists();
        }
        return nfile;
    }

    // Returns the given output file
    // Console = prcoesses user input
    public static File getOutput(Scanner console) {
        System.out.print("Output file name: ");
        String nameFile = console.nextLine();
        File f = new File(nameFile);
        return f;
    }

    // Given scanInput of a scanner from input file,
    // outputs the contents of the input file
    // scanInput = processes input file
    public static void view(Scanner scanInput) {
        while (scanInput.hasNextLine()) {
            String fileContent = scanInput.nextLine();                    
            System.out.println(fileContent);
        }
    }

    // From given scanner and printsream, reads the file input for
    // either commands of convert, range, or repeat and outputs
    // the desired output functions of the commands.
    // scanInput = processes input file
    // outputFile = process output file
    public static void interpreter(Scanner scanInput, PrintStream outputFile) {
        boolean other = scanInput.hasNext();
        String start = scanInput.next();
        while (other) { 
            if (start.equals("CONVERT")) {
                convertm(scanInput, outputFile);
                if (scanInput.hasNext()) {
                    other = true;
                    start = scanInput.next();
                } else {
                    other = false;
                }
            } else if (start.equals("RANGE")) {
                rangem(scanInput, outputFile);
                if (scanInput.hasNext()) {
                    other = true;
                    start = scanInput.next();
                } else {
                    other = false;
                }
                outputFile.println();
            } else if (start.equals("REPEAT")) {
                boolean temp = scanInput.hasNext();
                while (temp) {
                    if (scanInput.hasNext()) {
                        String nextStr = scanInput.next();
                        if ((nextStr.equals("CONVERT")) || (nextStr.equals("RANGE")) || (nextStr.equals("REPEAT"))) {
                            start = nextStr;
                            other = true;
                            temp = false;
                        } else {
                            int nextNum = scanInput.nextInt();
                            repeatm(nextStr, nextNum, outputFile);
                            temp = true;
                        }
                    } else {
                        temp = false;
                    }     
                }
                if (!scanInput.hasNext()) {
                    other = false;
                }
                outputFile.println();
            }
        }
        scanInput.close();
    }

    // From given scanInput and outputFile,
    // read the content of the file with the command
    // CONVERT and perform calculations to process the
    // conversion of a given temperature unit from
    // either Celsius to Fahrenheit or vice versa
    // scanInput = processes input file
    // outputFile = process output file
    public static void convertm(Scanner scanInput, PrintStream outputFile) {
        int valT  = scanInput.nextInt();
        String tempType = scanInput.next();
        if (tempType.equalsIgnoreCase("C")) {
            double temp = (valT * 9.0 / 5.0) + 32;
            if (temp > 0) {
                temp = Math.floor(temp);
            } else {
                temp = Math.ceil(temp);
            }
            int result = (int)temp;
            String output = Integer.toString(result);
            outputFile.println(output + "F");
        } else {
            double otemp = Math.floor((valT - 32.0) * 5.0 / 9.0);
            if (otemp > 0) {
                otemp = Math.floor(otemp);
            } else {
                otemp = Math.ceil(otemp);
            }
            int oresult = (int)otemp;
            String coutput = Integer.toString(oresult);
            outputFile.println(coutput + "C");
        }
    }
    
    // From given scanInput and outputFile,
    // process the content of the file with the
    // command RANGE and outputs to output file
    // a sequence of numbers starting from the first number
    // and incrementing by the third number until the output
    // number is greater or equal to the second number is reached
    // scanInput = processes input file
    // outputFile = process output file
    public static void rangem(Scanner scanInput, PrintStream outputFile) {
        int num1 = scanInput.nextInt();
        int num2 = scanInput.nextInt();
        int num3 = scanInput.nextInt();
        if (num1 < num2) {
            String outputNum1 = Integer.toString(num1);
            outputFile.print(outputNum1 + " ");
            int comb = num1 + num3; 
            while (comb < num2) {
                String nextOut = Integer.toString(comb);
                outputFile.print(nextOut + " ");
                comb += num3;
            }
        }
    }

    // From given string, integer, and printsream, 
    // prints to output file the string as many time as the
    // given integer. Also deleteing quotations, and making underscores
    // equal to spaces.
    // s = given string
    // n = given integer
    // outputFile = process output file
    public static void repeatm(String s, int n, PrintStream outputFile) {
        s = s.substring(1, s.length() - 1);
        s = s.replaceAll("_", " ");
        for (int j = 0; j < n; j++) {
            outputFile.print(s);
        }
    }
}

