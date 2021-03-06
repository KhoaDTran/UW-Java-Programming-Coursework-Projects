import java.util.*;
import java.io.*;
public class AssassinTesting {
   public static void main(String[] args) throws FileNotFoundException {
    // prompt for file name
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the CSE143 Assassin Manager");
        System.out.println();
        System.out.print("What name file do you want to use this time? ");
        String fileName = console.nextLine();

        // read names into a list, using a Set to avoid duplicates
        Scanner input = new Scanner(new File(fileName));
        Set<String> names = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        List<String> names2 = new ArrayList<String>();
        while (input.hasNextLine()) {
            String name = input.nextLine().trim();
            if (name.length() > 0 && !names.contains(name)) {
                names.add(name);
                names2.add(name);
            }
        }

     AssassinManager testing = new AssassinManager(names2);
     System.out.println(testing.killRingContains("don knuth"));
     System.out.println(testing.graveyardContains("adff"));
     System.out.println(testing.gameOver());
     System.out.println(testing.winner());



   }
}