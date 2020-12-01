package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    int size  = squareMatrix.size() - 1;
    for (int i = 0 ; i < squareMatrix.size()/2 ; i++) {
      for (int j = i ; j < size - i ; j++) {

        int temp1 = squareMatrix.get(i).get(j);
        int temp2 = squareMatrix.get(i).get(size - j);
        int temp3 = squareMatrix.get(size - i).get(j);
        int temp4 = squareMatrix.get(size - i).get(size - j);

        squareMatrix.get(i).set(size - j, temp1);
        squareMatrix.get(size - i).set(size - j, temp2);
        squareMatrix.get(i).set(j, temp3);
        squareMatrix.get(size - i).set(j, temp4);
      }
    }
    return;
  }
  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
