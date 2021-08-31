// Khoa Tran
// 01/24/2019
// CSE143
// TA: Amir Nola
// Assignment #2

/* GuitarString class keeps track of a ring buffer that represents a guitar string
with certain frequencies. Can also replace values in the ring buffer
along with implementing the Karplus-Strong algorithm,
and getting the first value in the ring buffer.
*/
import java.util.*;

public class GuitarString {
   // Constant DECAY_FACTOR represents the energy decay factor in the Karplus-Strong algorithm
   public static final double DECAY_FACTOR = 0.996;
   private Queue<Double> buffer;
   private int size;
   
   // pre: (frequency > 0, size >= 2) (throws IllegalArgumentException if not)
   // post: Constructs GuitarString that represents a guitar string at rest
   // by creating a ring buffer with a size dependent on the given frequency
   public GuitarString(double frequency) {
      size = (int)(Math.round(StdAudio.SAMPLE_RATE / frequency));
      buffer = new LinkedList<Double>();
      if (frequency <= 0 || size < 2) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < size; i++) {
         buffer.add(0.0);
      }
   }
   
   // pre: (size >= 2) (throws IllegalArgumentException if not)
   // post: Constructs GuitarString by creating a ring buffer
   // with the contents of the given init values
   public GuitarString(double[] init) {
      buffer = new LinkedList<Double>();
      size = init.length;
      if (size < 2) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < size; i++) {
         buffer.add(init[i]);
      }
   }
   
   // post: replace all the values in the ring buffer with a random value between -0.5 and 0.5
   public void pluck() {
      for (int i = 0; i < buffer.size(); i++) {
         double num = (Math.random() - 0.5);
         buffer.add(num);
         buffer.remove();
      }
   }
   
   // post: implements the Karplus-Strong algorithm to the first two values of the ring buffer
   public void tic() {
      double firstNum = buffer.remove();
      double secondNum = buffer.peek();
      buffer.add((DECAY_FACTOR) * 0.5 * (firstNum + secondNum));
   }
   
   // post: return the first value of the ring buffer
   public double sample() {
      return buffer.peek();
   }
}