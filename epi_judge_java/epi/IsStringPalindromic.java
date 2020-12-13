package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromic {
  @EpiTest(testDataFile = "is_string_palindromic.tsv")

  public static boolean isPalindromic(String s) {

    int i = 0, j = s.length() - 1;

    while (i < j) {
      if(!Character.isLetterOrDigit(s.charAt(i)) && i < j) {
        i++;
      }
      if(!Character.isLetterOrDigit(s.charAt(j)) && i < j) {
        j--;
      }

      if(s.charAt(i) != s.charAt(j)) {
        return false;
      }

      i++;
      j--;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
