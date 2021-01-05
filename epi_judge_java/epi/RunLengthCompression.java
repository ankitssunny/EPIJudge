package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class RunLengthCompression {

  public static String decoding(String s) {
    StringBuffer newString = new StringBuffer();
    int i = 0;
    for (int j = 0 ; j < s.length(); j++) {

      if (Character.isDigit(s.charAt(j))) {
        continue;
      }
      else {
        Integer currCount = Integer.parseInt(s.substring(i, j));
        for (int k = 1; k <= currCount; k++) {
          newString.append(s.charAt(j));
        }
        j++;
        i = j;
      }
    }
    return newString.toString();
  }

  public static String encoding(String s) {
    StringBuffer newString = new StringBuffer();
    int i = 0, j = 0;
    while (j < s.length()) {
      if (s.charAt(i) == s.charAt(j)) {
        j++;
      }
      else {
        newString.append((j - i) + "" + s.charAt(i));
        i = j;
      }
    }
    // Last character sequence will be added here.
    newString.append((j - i) + "" + s.charAt(i));
    return newString.toString();
  }
  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
