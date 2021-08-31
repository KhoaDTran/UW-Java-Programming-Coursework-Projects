import java.util.*;

public class Tester{
   public static void main(String[] args){
      showSplit(1);
      System.out.println();
      showSplit(7);
      System.out.println();
   }
   
   public static void showSplit(int n) {
      if (n < 0) {
         throw new IllegalArgumentException();
      }
      if (n < 2) {
         System.out.print(n);
      } else if (n == 2) {
         System.out.print(2 + " = (1, 1)");
      } else if (n % 2 == 0) {
         System.out.print(n + " = (");
         showSplit(n/2);
         System.out.print(", ");
         showSplit(n/2);
         System.out.print(")");
      } else if (n % 2 != 0) {
         System.out.print(n + " = (");
         showSplit((n/2)+1);
         System.out.print(", ");
         showSplit(n/2);
         System.out.print(")");
      }
   }

      
   
   public static boolean showSplit(Stack<Integer> s) {
    boolean condition = true;
    int size = s.size();
    Queue<Integer> q = new LinkedList<Integer>();
    if (size < 2) {
        condition = true;
    }
    int num = s.pop();
    q.add(num);
    while (!s.isEmpty()) {
        int val = s.pop();
        if (val > num) {
            condition = false;
        }
        q.add(val);
        num = val;
    }
    while(!q.isEmpty()) {
        s.push(q.remove());
    }
    while(!s.isEmpty()) {
        q.add(s.pop());
    }
    while(!q.isEmpty()) {
        s.push(q.remove());
    }
    return condition;
}
}