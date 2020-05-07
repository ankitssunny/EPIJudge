package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    int carryOver = 0;

    /*

    for (int i = A.size() - 1 ; i >= 0 ; i--) {

      // Make sure you add one to the last digit only
      if (i == A.size() - 1) {
        if (A.size() == 1 && A.get(0) == 9) {
          A.forEach(element -> element = 0);
          A.set(0, 1);
          A.add(0);
          return A;
        }
        else {
          if (A.get(i) + 1 > 9) {
            carryOver = 1;
            A.set(i, 0);
          }
          else {
            A.set(i, A.get(i) + 1);
            carryOver = 0;
          }
        }
      }

      else {
        if ((A.get(i) + carryOver) > 9) {
          if (i == 0) {
            A.forEach(element -> element = 0);
            A.set(0, 1);
            A.add(0);
            return A;
          }
          else {
            carryOver = 1;
            A.set(i, 0);
          }
        }

        else if ((A.get(i) + carryOver) <= 9) {
          A.set(i, A.get(i) + carryOver);
          carryOver = 0;
        }
      }
    }

    */

    // Simpler and faster by twice than above, solved myself after reading up a solution a day before
    // so a lil muscle memory but lots of paper solving, Be good to yourself
    int lastIndex = A.size() - 1;
    A.set(lastIndex, A.get(lastIndex) + 1 );

    for (int i = lastIndex - 1; i >= 0 ; i--) {
      if (A.get(i+1) == 10) {
        A.set(i, A.get(i) + 1);
        A.set(i+1, 0);
      }
      else return A;
    }

    if (A.get(0) == 10){
      A.add(0);
      A.set(0, 1);
    }

    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
