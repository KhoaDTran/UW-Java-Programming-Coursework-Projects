public LinkedIntList removeAlternating() {
   ListIntList result = new LinkedIntList();
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