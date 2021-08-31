import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


class LinkedIntList {

   private ListNode front;

   // Type in your solution here!
   //
   // Remember that while LinkedIntLists have methods
   // like .add(int value) or .size(), this was not
   // allowed by the question as it stated that you
   // cannot assume any methods besides the constructor
   // exist for you to use
   public LinkedIntList removeAlternating() {
      LinkedIntList result = new LinkedIntList();
      ListNode current = front;
      ListNode temp = null;
      if (front != null && front.next != null) {
         result.front = front;
         front = front.next;
         current = front.next;
         result.front.next = null;
         temp = result.front;
         while (current != null && current.next != null && current.next.next != null) {
            temp.next = current.next;
            current.next = current.next.next.next;
            temp.next.next.next = null;
            temp = temp.next.next;
            current = current.next.next;
         }
      }
      return result;                                  
   
   }

   private void switchFronts(LinkedIntList other) {
      ListNode temp = this.front;
      this.front = other.front;
      other.front = temp;
   }

   // solution
   public LinkedIntList removeAlternatingSolution() {
      LinkedIntList list = new LinkedIntList();
   
      if (front != null && front.next != null) {
         list.front = front;
         front = front.next;
      
         ListNode curr = front;
         ListNode curr2 = list.front;
         curr2.next = null;
         int count = 1;
      
         while (curr != null && curr.next != null && curr.next.next != null) {
            if (count % 2 == 0) {
               curr2.next = curr.next;
               curr.next = curr.next.next;
            } else {
               curr2.next = curr.next.next;
               curr.next.next = curr.next.next.next;
            }
            count++;
            curr = curr.next;
            curr2 = curr2.next;
            curr2.next = null;
         }
      }
      return list;
   }

   // post: constructs an empty list
   public LinkedIntList() {
      front = null;
   }

   // post: Adds the given value to the end of this list
   public void add(int value) {
      if (front == null) {
         front = new ListNode(value);
      } else {
         ListNode current = front;
         while (current.next != null) {
            current = current.next;
         }
         current.next = new ListNode(value);
      }
   }

   // post: Returns a comma-separated, bracketed version of the list
   public String toString() {
      if (front == null) {
         return "[]";
      } else {
         // Have to fencepost for comma separation, easier to do at end rather than beginning.
         ListNode current = front;
         String result = "[";
         while (current.next != null) {
            result += current.data + ", ";
            current = current.next;
         }
         result += current.data + "]";
         return result;
      }
   }

   public boolean equals(Object o) {
      if (o == null || !(o instanceof LinkedIntList)) {
         return false;
      }
      LinkedIntList list2 = (LinkedIntList) o;
      return this.toString().equals(list2.toString());
   
   }

   private static class ListNode {
      public int data;
      public ListNode next;
   
      public ListNode(int data) {
         this(data, null);
      }
   
      public ListNode(int data, ListNode next) {
         this.data = data;
         this.next = next;
      }
   }
}

public class Test8 {

   public static void main(String[] args) throws Exception {
      System.out.println("=== Length 0 and 1 List Test Cases (not included in external points) ===");
      testCase(0, 1, true);
      testCase(1, 1, true);
   
      System.out.println("=== External Test Cases ===");
      int pointsEarned = 0;
      int pointsPossible = 0;
      for (int i = 2; i <= 11; i++) {
         pointsEarned += testCase(i, 1, i > 4); // ignore result for those below length 4
         pointsPossible++;
      }
      System.out.println("TOTAL EXTERNAL POINTS: " + pointsEarned + " / " + pointsPossible);
   
   }

   public static LinkedIntList range(int max) {
      LinkedIntList list = new LinkedIntList();
      for (int i = 1; i <= max; i++) {
         list.add(i);
      }
      return list;
   }

   public static LinkedIntList list(int... nums) {
      LinkedIntList list = new LinkedIntList();
      for (int n : nums) {
         list.add(n);
      }
      return list;
   }

   public static int testCase(int max, int points, boolean checkResult) throws Exception {
      Callable<Integer> test = new Test(max, points, checkResult);
   
      ExecutorService executor = Executors.newSingleThreadExecutor();
      Future<Integer> future = executor.submit(test);
      int earned = 0;
      try {
         earned = future.get(1000, TimeUnit.MILLISECONDS);
      } catch (TimeoutException e) {
         future.cancel(true);
         System.out.println("    TIMEOUT. Program took too long to complete, no points awarded.");
      }
      executor.shutdownNow();
      return earned;
   }

   private static class Test implements Callable<Integer> {
      public int max;
      public int points;
      public boolean checkResult;
   
   
      public Test(int max, int points, boolean checkResult) {
         this.max = max;
         this.points = points;
         this.checkResult = checkResult;
      }
   
   
      @Override
      public Integer call() {
         LinkedIntList student = range(max);
         LinkedIntList solution = range(max);
         LinkedIntList solutionResult = solution.removeAlternatingSolution();
         System.out.println("Testing " + student);
      
         int earned = points;
         try {
         
            LinkedIntList studentResult = student.removeAlternating();
         
            if (student.equals(solution) && checkResult && solutionResult.equals(studentResult)) {
               System.out.println("Pass! " + earned + " / " + points);
            } else if (student.equals(solution) && !checkResult) {
               System.out.println("Pass (ignore returned list)! " + earned + " / " + points);
            } else {
               System.out.println("    Expected: list=" + solution + ", result=" + solutionResult);
               System.out.println("    Received: list=" + student + ", result=" + studentResult);
               earned = 0;
               System.out.println("Did not match expected results: " + earned + " / " + points);
            }
         } catch (Exception e) {
            System.out.println("    Threw exception: " + e + " @ " + e.getStackTrace()[0]);
            earned = 0;
         }
         System.out.println();
         return earned;
      }
   }
}
