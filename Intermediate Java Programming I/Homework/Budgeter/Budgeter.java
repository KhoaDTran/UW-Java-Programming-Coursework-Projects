// Khoa Tran
// 10/23/2018
// CSE142
// TA: Elizabeth McKinnie
// Assignment #4: Budgeter

// Calculates the net monthly income from the user's input of their income and expenses.

import java.util.*;

public class Budgeter {
   // Days constant sets the number of days in a month.
   public static final int DAYS = 31;
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      
      intro();
      double total_income = incomeAmount(console);
      double total_expenses = expenseAmount(console);
      totalAmount(total_income, total_expenses);
      message(total_income, total_expenses);
      
   }
   
   // Outputs the introduction statement.
   public static void intro () {
      System.out.println("This program asks for your monthly income and \nexpenses,"
      + " then tells you your net monthly income.");
      System.out.println();
   }
   
   
   // Prompts and calculates the total income of the user.
   // Parameter type sends either income or expenses.
   public static double getAmounts(Scanner console, String type) {
      System.out.print("How many categories of " + type + "? ");
      int categories = console.nextInt();
      double amount = 0;
      double total_amount = 0;
      for (int i = 0; i< categories; i++) {
         System.out.print("    Next " + type + " amount? $");
         amount = console.nextDouble();
         total_amount += amount;
      }
      return total_amount;
   }
   
   // Outputs the total income amount from the user.
   public static double incomeAmount(Scanner console) {
      double total_income = getAmounts(console, "income");
      
      System.out.println();
      
      return total_income;
      
  }     
         
   // Prompts and calculates the total expenses of the user.
   public static double expenseAmount(Scanner console) {
      System.out.print("Enter 1) monthly or 2) daily expenses? ");
      int category = console.nextInt();
      double total_expense = getAmounts(console, "expense");
      
      System.out.println();
      
      if (category == 1) {
         return total_expense;
         
      } else {
         return total_expense * DAYS;
   
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
         System.out.println("You're a big spender.");
         System.out.println("This is not good, start spending less and save up more!");
      }
   }
}



