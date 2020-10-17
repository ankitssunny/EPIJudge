package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {

    int i = 0;
    long result = 0;
    while (i < 63) {

      if (((y >> i) & 1) == 1) {
        result = add(result, (x << i));
      }

      i++;
    }

    return result;
  }

  public static long add(long x, long y) {

    long result = 0, i = 0 ;

    while (y != 0)
    {
      // carry now contains common
      // set bits of x and y
      long carry = x & y;

      // Sum of bits of x and
      // y where at least one
      // of the bits is not set
      x = x ^ y;

      // Carry is shifted by
      // one so that adding it
      // to x gives the required sum
      y = carry << 1;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
