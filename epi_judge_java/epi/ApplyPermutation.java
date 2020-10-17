package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    // TODO - you fill in here.
/*
    // Below code is O(n) in space and time.
    List<Integer> finalList = new ArrayList<>(Collections.nCopies(A.size(), 0));
    Collections.copy(finalList, A);

    for (int i = 0; i < A.size(); i++) {
      int newPos = perm.get(i);

      A.set(newPos, finalList.get(i));
    }
*/

    // Lets try with O(n) time and constant space. Solution from the book

    for (int i = 0 ; i < A.size() ; i++) {

      int next = i;

      while ( perm.get(next) >= 0 ) {

        Collections.swap(A , i, perm.get(next));
        int temp = perm.get(next);
        // Subtracts perm.sizeO from an entry in perm to make it negative ,
        // which indicates the corresponding move has been performed .
        perm.set(next , perm.get(next) - perm.size());
        next = temp;

      }
    }

    return;
  }

  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
