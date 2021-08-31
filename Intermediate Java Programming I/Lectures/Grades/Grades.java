public class Grades {

   public static void main(String[] args) {
      // 4 hw assignents
      // 2 exams
      // 45% hw, 20% midterm, 35% final exam
      double pct = hwScore(10, 10, 14, 16, 17, 20, 15, 20);
      System.out.println("HW score =" + pct);
   }
   
   public static double hwScore(int score1, int poss1, int score2, int poss2, 
                                  int score3, int poss3, int score4, int poss4) {                                  
      int totalScore = score1 + score2 + score3 + score4;
      int totalPoss = poss1 + poss2 + poss3 + poss4;
      double pct = (double)totalScore/ totalPoss;
            
      return pct;
   }
}