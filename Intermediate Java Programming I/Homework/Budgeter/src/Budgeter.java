// Khoa Tran
// 10/08/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #2: Rocket Ship

// Calculates the net monthly income from the user's input of their income and expenses.

import java.util.*;

public class Budgeter {
   // Days constant sets the number of days in a month.
   public static final int DAYS = 31;
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      
      intro();
      double totalIncome = incomeAmount(console);
      double totalExpenses = expenseAmount(console);
      totalAmount(totalIncome, totalExpenses);
      message(totalIncome, totalExpenses);
      
   }
   
   // Outputs the introduction statement.
   public static void intro () {
      System.out.println("This program asks for your monthly income and \nexpenses,"
      + " then tells you your net monthly income.");
      System.out.println();
   }
   
   // Prompts and calculates the total income of the user.
   public static double incomeAmount(Scanner console) {
      System.out.print("How many categories of income? ");
      int categories = console.nextInt();
      double totalIncome = 0;
      for (int i = 0; i< categories; i++) {
         System.out.print("\tNext income amount? ");
         double income = console.nextDouble();
         totalIncome += income;
      }
      System.out.println();
      return totalIncome;
   }
   
   // Prompts and calculates the total expenses of the user.
   public static double expenseAmount(Scanner console) {
      System.out.print("Enter 1) monthly or 2) daily expenses? ");
      int category = console.nextInt();
      if (category == 1) {
         System.out.print("How many categories of expenses? ");
         int monthly = console.nextInt();
         double totalExpenses = 0;
         for (int i = 0; i< monthly; i++) {
            System.out.print("\tNext expense amount? ");
            double expense = console.nextDouble();
            totalExpenses += expense;
         }
         System.out.println();
         return totalExpenses;
      } else {
         System.out.print("How many categories of expenses? ");
         int daily = console.nextInt();
         double totalExpenses = 0;
         for (int i = 0; i< daily; i++) {
            System.out.print("\tNext expense amount? ");
            double expense = console.nextDouble();
            totalExpenses += expense * 31;
         }
         System.out.println();
         return totalExpenses;
      }
   }
   
   // Outputs the user's total income and expenses.
   public static void totalAmount(double totalIncome, double totalExpenses) {
      double incomeDays = totalIncome/DAYS;
      double expenseDays = totalExpenses/DAYS;
      System.out.printf("Total income = $%.2f ($%.2f/day)\n", totalIncome, incomeDays);
      System.out.printf("Total expenses = $%.2f ($%.2f/day)\n", totalExpenses, expenseDays);
      System.out.println();
   }
   
   // Outputs the user's net monthly income.
   public static void message(double totalIncome, double totalExpenses) {
      double difference = totalIncome - totalExpenses;
      double absolute = Math.abs(difference);
      if (totalIncome >= totalExpenses) {
         System.out.printf("You earned $%.2f more than you spent this month.\n", absolute);
      } else {
         System.out.printf("You spent $%.2f more than you earned this month.\n", absolute);
      }
      if (difference > 250) {
         System.out.println("You're a big saver.");
         System.out.println("Good job, you're saving for the future!");
      } else if (difference > 0 && difference < 250){
         System.out.println("You're a saver.");
         System.out.println("Nice job, keep it up!");
      } else if (difference <= 0 && difference > -250) {
         System.out.println("You're a spender.");
         System.out.println("Don't spend too much, you have to save up!");
      } else {
         System.out.printf("You spent $%.2f more than you earned this month.\n", absolute);
         System.out.println("You're a big spender.");
         System.out.println("This is not good, start spending less and save up more!");
      }
   }
}



