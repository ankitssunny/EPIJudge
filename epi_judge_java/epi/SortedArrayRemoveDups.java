package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.List;
public class SortedArrayRemoveDups {
  // Returns the number of valid entries after deletion.
  public static int deleteDuplicates(List<Integer> A) {
    // TODO - you fill in here.

    if (A == null || A.size() == 0) return 0;
    if (A.size() == 1) return 1;

    int count = 1;

    for (int i = 1 ; i < A.size(); ++i) {
      if (!A.get(count - 1).equals(A.get(i)) ){
        A.set(count++, A.get(i));
      }
    }

    return count;
  }
  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
