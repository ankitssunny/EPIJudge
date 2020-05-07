package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  // Page 62

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    // TODO - you fill in here.

    int startIndex = 0, lastIndex = A.size() - 1;
    Color pivotColor = A.get(pivotIndex);

    for (int i = 0; i < A.size() ; i++) {
      if(A.get(i).ordinal() < pivotColor.ordinal()) {
          Color temp = A.get(startIndex);
          A.set(startIndex, A.get(i));
          A.set(i, temp);
          startIndex++;
      }
    }

    for (int i = A.size() - 1; i >= 0 ; i--) {
      if(A.get(i).ordinal() > pivotColor.ordinal()) {
        Color temp = A.get(lastIndex);
        A.set(lastIndex, A.get(i));
        A.set(i, temp);
        lastIndex--;
      }
    }

    /*
    // One pass one loop solution
    int less = 0, equal = 0, more = A.size();

    while(equal < more) {
      if (A.get(equal).ordinal() < pivotColor.ordinal()) {
        Collections.swap(A, less++, equal++);
      }
      else if(A.get(equal).ordinal() == pivotColor.ordinal()) {
        ++equal;
      }
      else {
        Collections.swap(A, equal, --more);
      }
    }
*/
    return;
  }
  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
