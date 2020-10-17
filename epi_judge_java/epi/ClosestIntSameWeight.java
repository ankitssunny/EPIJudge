package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    // TODO - you fill in here.
    // Look at SwapBits.java if somethings dont make sense.

    // We need to check for the first instance of adjacent bits which are different. once we do find it, we just swap the bits

    int i =0, j =1;

    // compare adjacent bits, if they are different swap them otherwise increase the iterator
    while (j < 63) {
      if ( ((x >>> i) & 1) != ((x >>> j) & 1) ) {
        long mask = (1L << i) | (1L << j);
        x ^= mask;
        break;
      }
      else {
        i++;
        j++;
      }
    }

    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
