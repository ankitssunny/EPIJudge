package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    // TODO - you fill in here.

    // Convert from string to base 10 value first
    // Then convert that string into base b2 string.

    int start = 0;
    int numBaseTen = 0;
    if(numAsString.charAt(0) == '-' || numAsString.charAt(0) == '+') {
      start = 1;
    }

    for (int i = start; i < numAsString.length(); i++) {
      numBaseTen *= b1;
      numBaseTen += Character.isDigit(numAsString.charAt(i)) ?
              (numAsString.charAt(i) - '0') :
              (numAsString.charAt(i) - 'A' + 10);
    }

    return (numAsString.charAt(0) == '-'? "-" : "") +
            (numBaseTen == 0 ? "0": helper(numBaseTen, b2));
  }

  public static String helper(int num, int b2) {
    if (num == 0)
      return "";

    else {
      return helper(num / b2, b2) + (char)(num % b2 >= 10 ?  (num % b2) - 10 + 'A' : (num % b2) + '0' );
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
