package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    // TODO - you fill in here.
    int result = 0, power = 64;
    int ypower = y << power; // start with a reasonably high value;
    System.out.println("ypower = " + ypower);
    while (x >= y) {

      while (ypower > x) {
        ypower >>>= 1;
        --power;
      }

      result += 1 << power;

      x -= ypower;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
