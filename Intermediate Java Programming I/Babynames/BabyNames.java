// BabyNames.java
//import File class
import java.io.*;

//import FileNotFoundException class
import java.io.FileNotFoundException;

//import Scanner class
import java.util.Scanner;

//import Graphics class from awt package
import java.awt.*;

//import java.awt.*;
public class BabyNames
{
   public static void main(String[] args) throws FileNotFoundException
   {
       // Declare boolean variable and set its value
       // as false.
       boolean isMathced = true;

       // Declare string variables
       String fileName = "names.txt";
       String babyName;

       // Print the Program Heading
       System.out.println("** Popularity of a baby name since year 1920 **");

       // create a scanner object
       Scanner in = new Scanner(System.in);
      
       // prompt for a baby name
       System.out.print("Name? ");
       babyName = in.next();
      
       // file is passed to scanner class
       Scanner fileData = new Scanner(new File(fileName));

       // Iterate through the file
       while (fileData.hasNextLine() && isMathced)
       {
           String line = fileData.nextLine();
           // calling method to get the data about
           // the child
           isMathced = findBabyData(line, babyName);
       }

       // print message if baby name not found in
       // the file
       if (isMathced)
           System.out.println("Name Not Found");

   }

   // This method displays the data about the baby name’s
   // popularity over the decades, at the same time,
   // display the line graph where the decades are placed
   // on X-axis and popularity values are displayed on
   // on Y-axis and writes the found data to the respective
   // baby's name text file
   private static boolean findBabyData(String line, String name)
   {
       // declare the required variables
       boolean isMathed = false;
       int dimension = 600;
       int year = 1920;
       int count;
       int xAxis = 0;
       int yAxis = dimension - 50;
       int x = 0;
       int y = 30;
       int incrementY = 20;
       int midrange = dimension / 10;      
       int previousX = 0;
       int previousY = 0;
       int pointRadius = 5;

       // initialize the variable j
       int j = 0;
       PrintWriter pw;

       // pass string to the Scanner object
       Scanner data = new Scanner(line);
       // get the baby name
       String babyName;

       try
       {
           // get the baby name from the line
           babyName = data.next();
          
           // if baby name is found in the file
           // read data and display the data of the
           // baby
           if (babyName.equalsIgnoreCase(name))
           {

               // create the DrawingPanel object of
               // size 550x600
               DrawingPanel dp = new DrawingPanel(dimension - 50,
                       dimension);
              
               // Get the Graphics
               Graphics g = dp.getGraphics();

               // draw the string to display the
               // x-axis and y-axis data types
               g.drawString(babyName, dp.getWidth() / 2, 15);

               // to draw the horizontal line after the name
               g.drawLine(x, y, dp.getWidth(), y);

               // draw the bottom horizontal line
               g.drawLine(xAxis, yAxis, yAxis + 100, yAxis);

               // increment the yAxis
               yAxis += incrementY;

               // loop to draw the vertical lines and set the
               // years to the graph
               for (int i = 0; i < 9; i++)
               {
                   // draw the year names
                   g.drawString(year + "", xAxis + 10, yAxis);

                   // draw the vertical lines
                   g.drawLine(x, y, xAxis, dimension);
                   x += midrange;
                   year += 10;
                   xAxis += midrange;
               }

               // initialize the j and xAxis values
               j = 1;
               xAxis = 0;

               // display the heading to the console
               System.out.println("\nStatistics on name" + " \""
                       + babyName + "\"");

               // write set the printWriter object to the baby name text
               // file
               pw = new PrintWriter(babyName + ".txt");

               // write the babayName to the text file
               pw.println(babyName + ",");

               // loop through each value in the line
               while (data.hasNextInt())
               {
                   // get the popularity of the respective baby name for
                   // each
                   // year
                   count = data.nextInt();

                   // display the year and its respective popularity to
                   // the console
                   System.out.println("\t" + year + ": " + count);

                   // format the output of the text file
                   if (j < 9)
                       pw.println(year + ": " + count + ",");
                   else
                       pw.println(year + ": " + count);

                   // set the xCoord and yCoord values
                   int xCoord = xAxis + (midrange / 2);
                   int yCoord = count / 2;

                   // if j != 1 and count != 0, then draw the line joining
                   // the
                   // coordinates
                   if (j != 1 && count != 0)
                   {
                       g.drawLine(previousX, previousY, xCoord, yCoord);
                   }

                   // draw the line if count is zero and j is not 1
                   if (j != 1 && count == 0)
                   {
                       g.drawLine(previousX, previousY, xCoord,
                               yAxis - 20);
                   }

                   // if count is zero, set the point to the xAxis as zero
                   if (count == 0)
                   {
                       // draw the point to the panel
                       g.fillOval(xCoord, yAxis - 20, pointRadius,
                               pointRadius);

                       // draw the string specifying the popularity value
                       g.drawString(count + "", xCoord, yAxis - 20);

                       // set the previousX and previousY values
                       previousX = xCoord;
                       previousY = yAxis - 20;
                   }

                   // if count(popularity) is not zero then set the point
                   // at appropriate position
                   else
                   {
                       // draw the point on to the panel
                       g.fillOval(xCoord, count / 2, pointRadius,
                               pointRadius);

                       // draw the popularity on the panel
                       g.drawString(count + "", xCoord, yCoord);

                       // set the previous coordinate values
                       previousX = xCoord;
                       previousY = yCoord;
                   }

                   // increment the xAxis by midrange value
                   xAxis += midrange;

                   // increment the j value
                   j++;
               }

               // close the print writer object
               pw.close();

               // return false
               return false;
           }

           // return true if name not found
           else
               return true;
       }

       // catch the exception
       catch (Exception e)
       {
           e.printStackTrace();
       }

       // return the boolean variable to the calling
       // method
       return isMathed;
   }
}