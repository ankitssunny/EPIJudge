package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    // TODO - you fill in here.
   /* int furthestReachable = 0, thisReachable = 0;

    for (int i = 0 ; i <= furthestReachable; i++) {
      if (furthestReachable < maxAdvanceSteps.size() - 1 ){
        thisReachable = i + maxAdvanceSteps.get(i);
        if (thisReachable > furthestReachable) furthestReachable = thisReachable;
      }
    }
    return furthestReachable >= maxAdvanceSteps.size() - 1;*/


   int furthestReachable = 0;

   for (int i = 0 ; i <= furthestReachable && furthestReachable < maxAdvanceSteps.size() - 1 ; i++) {

     int currReachable = i + maxAdvanceSteps.get(i);

     if(currReachable > furthestReachable) {
       furthestReachable = currReachable;
     }

   }
   return furthestReachable >= maxAdvanceSteps.size() - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
