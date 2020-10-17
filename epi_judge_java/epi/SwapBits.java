package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    // TODO - you fill in here.

    // compare if the ith and j'th bits are same or not, if same then no need to swap
    // to compare them we need to right-shift x by the ith bits and jth bits and then mask with 1 as that will return
    // the least significant bit which would now be the ith or the jth bit right-shifted there.

    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
      // the bits at ith and jth position are different. Now we can just try and reverse the values at those positions.
      // Create a mask that contains 1 only at ith position and jth position. The mask needs to be a size of Long

      long mask = (1L << i) | (1L << j);

      // Now we can apply the XOR binary operation which returns 1 only if the bits being compared are different.
      // So it will reverse the ith and jth bits since if either i or j is 1 or 0, it will return a 0 or 1 with ith or jth bit in the mask which is 1 whereas all the other bits
      // in the mask are zero so it will always return whatever the value is of the bit at the position i.e. 0 with either 1 or 0 will return 1 or 0 respectively.
      x ^= mask;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
