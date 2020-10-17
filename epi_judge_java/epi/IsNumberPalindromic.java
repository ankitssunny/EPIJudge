package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {

    if ( x < 0) return false;

    int n = (int)(Math.floor(Math.log10(x))) + 1; // TODO: this calculates the number of digits in a number ex. 1234 will return 4
    int msdMask = (int)(Math.pow(10.0, n - 1) );

    for (int i = 0 ; i < ( n /2); ++i ) {
      int ld = x % 10;        // extract lowest digit in x
      int hd = x /msdMask;  // extract highest digit in x

      if (ld == hd) {
        x %= msdMask;
        x /= 10;
        msdMask /= 100;
      }
      else return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
