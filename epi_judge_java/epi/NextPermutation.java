package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // TODO - you fill in here.

    int startIndex = 0;
    for (int i = perm.size() - 1; i > 0 ; i--) {
      int j = i - 1;
      if (perm.get(i) > perm.get(j)) {
        Collections.swap(perm, i, j);
        startIndex = j;
        break;
      }
    }



    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
