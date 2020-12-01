package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.

    List<Integer> spiral = new ArrayList<>();

    for (int i = 0; i < Math.ceil(0.5 * squareMatrix.size()); i ++ ) {
      spiralHelper(squareMatrix, spiral, i);
    }
    return spiral;
  }


  private static void spiralHelper(List<List<Integer>> squareMatrix, List<Integer> spiral, int offset) {

    // First see if we at the center of a matrix of odd size. If yes, then add the center to the spiral and return.

    if (offset == squareMatrix.size() - 1 - offset) {
      spiral.add(squareMatrix.get(offset).get(offset));
      return;
    }

    // Cant do in the same loop as then the order wont be maintained.

    // first add the first offset row entries and the last offset col entries

    for (int i = offset; i < squareMatrix.size() - 1 - offset; i++) {
      spiral.add(squareMatrix.get(offset).get(i));

    }

    for (int i = offset; i < squareMatrix.size() - 1 - offset; i++) {
      spiral.add(squareMatrix.get(i).get(squareMatrix.size() - 1 - offset));

    }

    // Now add the last row from offset and the first offset col entries

    for (int i = squareMatrix.size() - 1 - offset; i > offset; i--) {
      spiral.add(squareMatrix.get(squareMatrix.size() - 1 - offset).get(i));
    }

    for (int i = squareMatrix.size() - 1 - offset; i > offset; i--) {
      spiral.add(squareMatrix.get(i).get(offset));
    }

    return;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
