package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.

    boolean isNegative = false;
    if ( x < 0) {
      isNegative = true;
    }
    StringBuilder str = new StringBuilder();

    do {
      str.append((char) ('0' + Math.abs(x % 10)));
      x /= 10;
    } while (x != 0);

    if (isNegative) {
      str.append('-');
    }

    str = str.reverse();

    return str.toString();
  }

  public static int stringToInt(String s) {
    // TODO - you fill in here.
    int result = 0;
    int start = 0 ;
    if(s.charAt(0) == '-' || s.charAt(0) == '+') {
      start = 1;
    }
    for (int i = start; i < s.length(); ++i) {
      final int digit = s.charAt(i) - '0';
      result = result * 10 + digit;
    }
      return s.charAt(0) == '-'? -result : result;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
