package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    // TODO - you fill in here.

    // proudly done by not looking at the book.
    List<List<Integer>> pascals = new ArrayList<>();
    for (int i = 0 ; i < numRows; i++) {
      List<Integer> currRow = new ArrayList<>();
      currRow.add(1);
      for (int j = 1 ; j <= i ; j++) {
        int sum  = pascals.get(i - 1).get(j - 1);
        if (pascals.get(i - 1).size() > j) {
          sum += pascals.get(i -1).get(j);
        }
        currRow.add(sum);
      }
      pascals.add(currRow);
    }
    return pascals;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
