package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // TODO - you fill in here.
    for (int i = 0 ; i < partialAssignment.size(); i++) {
      if(hasDuplicate(partialAssignment, i, i + 1, 0, partialAssignment.size())) {
        return false;
      }
    }

    for (int i = 0 ; i < partialAssignment.get(0).size(); i++) {
      if(hasDuplicate(partialAssignment, 0, partialAssignment.size(), i, i + 1 )) {
        return false;
      }
    }

    int regionSize = 3;

    for (int i = 0 ; i < regionSize; i++) {
      for (int k = 0; k < regionSize; k++) {
        if(hasDuplicate(partialAssignment, i * regionSize, (i + 1) * regionSize, k * regionSize, (k+1) * regionSize)) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean hasDuplicate(List<List<Integer>> partials, int startRow, int endRow, int startCol, int endCol) {
    List<Boolean> pos = new ArrayList<>(Collections.nCopies(partials.size() + 1, false));

    for (int i = startRow ; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
       if (partials.get(i).get(j) !=0 && pos.get(partials.get(i).get(j))) {
         return true;
       }
       else pos.set(partials.get(i).get(j), true);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
