// Khoa Tran
// 01/24/2019
// CSE143
// TA: Amir Nola
// Assignment #2

/* Guitar37 keeps track of 37 guitar strings
and it's frequencies on the chromatic scale from 110HZ to 880Hz
by having a corresponding keyboard representing each of the guitar strings.
Can also play a specfic note, sum up all of the string's sound samples,
and keeps track of the time of each play.
*/

public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
   "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   private GuitarString[] arr;
   private int size;
   private int numTic;
   
   // post: Constructs Guitar37 that represents 37 strings of a guitar
   // and their corresponding frequencies from 110Hz to 880Hz
   public Guitar37() {
      size = KEYBOARD.length();
      arr = new GuitarString[size];
      for (int i = 0; i < size ; i++) {
         arr[i] = new GuitarString(440 * Math.pow(2,((i-24.0)/12.0)));
      }
   }
   
   // post: plays the note of the corresponding given pitch by plucking the guitar string
   // if given pitch can't be play, it will be ignored
   public void playNote(int pitch) {
      int pitchNum = pitch + 24;
      if (pitchNum >= 0 && pitchNum < size) {
         arr[pitchNum].pluck();
      }
   }
   
   // post: returns true if the 37 strings KEYBOARD contains given character input
   // if not, returns false
   public boolean hasString(char string) {
      return (KEYBOARD.indexOf(string) >= 0);
   }
   
   // pre: the given character is a valid key from the 37 strings KEYBOARD 
   // (throws IllegalArgumentException if not)
   // post: plucks the guitar string of the corresponding given character
   public void pluck(char string) {
      if (!hasString(string)) {
         throw new IllegalArgumentException();
      }
      arr[KEYBOARD.indexOf(string)].pluck();
   }
   
   // post: returns the sum of all the string's sound sample
   public double sample() {
      double sum = 0.0;
      for (int i = 0; i < size; i++) {
         sum += (arr[i].sample());
      }
      return sum;
   }
   
   // post: advance the time by having each string tic forward by one
   public void tic() {
      for (int i = 0; i < size; i++) {
         arr[i].tic();
      }
      numTic++;
      
   }
   
   // post: returns the current time
   public int time() {
      return numTic;
   }
}
